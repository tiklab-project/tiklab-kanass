package io.tiklab.teamwire.support.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.context.AppHomeContext;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.teamwire.common.RepositoryFileUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


@Service
public class BackUpServiceImpl implements BackUpService{

    private static Logger logger = LoggerFactory.getLogger(BackUpServiceImpl.class);


    @Autowired
    BackUpDataService backUpDataService;


    @Value("${jdbc.username}")
    String jdbcUserName;

    @Value("${jdbc.password}")
    String jdbcPassword;

    @Value("${dfs.server.datapath}")
    private String fileAddress;

    @Value("${backup.address}")
    private String backupAddress;


    //数据备份日志
    public static Map<String , String> backupsExecLog = new HashMap<>();

    //数据恢复日志
    public static Map<String , String> recoveryLog = new HashMap<>();

    //执行的文件绝对路径名称
    public static Map<String , String> fileAbUrlMap = new HashMap<>();

    @Override
    public String backupsExec() {
        File file = new File(backupAddress);
        if (!file.exists()){
            file.mkdir();
        }
        
        File repositoryFile = new File(fileAddress);

        String loginId = LoginContext.getLoginId();


        recoveryLog.remove(loginId);
        backupsExecLog.remove(loginId);
        fileAbUrlMap.remove(loginId);

        joinBackupsLog("Dumping PostgreSQL database tiklab_teamwire...");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    boolean isResidue = memSize(file, repositoryFile);
                    //当剩余空间不足删除最先备份的那个文件
                    if(!isResidue){
                        //获取备份文件夹下面备份文件并根据创建时间排序
                        File[] files = file.listFiles();
                        List<File> collected = Arrays.stream(files).sorted(Comparator.comparing(a -> a.lastModified())).collect(Collectors.toList());
                        File firstFile = collected.get(0);
                        //删除文件
                        FileUtils.deleteQuietly(firstFile);
                    }
                    //添加最后一层目录压缩
                    String lastName = backupAddress.substring(backupAddress.lastIndexOf("/"));

                    String backupPath= backupAddress + lastName;

                    //code 下面存放代码数据
                    File backUpsUrlFile = new File(backupPath+"/files");

                    if (!backUpsUrlFile.exists() && !backUpsUrlFile.isDirectory()) {
                        backUpsUrlFile.mkdirs();
                    }

                    //开始dump database备份脚本
                    executeScript(backupPath);
                    //完成dump database备份脚本
                    joinBackupsLog("Dumping PostgreSQL database tiklab_teamwire...[DONE]");

                    // 备份文件
                    String fileUrl = fileAddress;
                    File codeFileUrl = new File(fileUrl);

                    /*
                     * 复制代码源文件到备份文件夹
                     * */
                    String backupsCodePath = backupPath + "/files/";
                    File backupsCodeFilePath = new File(backupsCodePath);


                    //复制代码文件
                    joinBackupsLog("  start backups...");
                    FileUtils.copyDirectory(codeFileUrl,backupsCodeFilePath);
                    joinBackupsLog("  backups success...[DONE]");

                    /*
                    * 压缩备份代码文件夹
                    * */
                    joinBackupsLog(" start compress tar.gz...");
                    String substring = backupPath.substring(0, backupPath.lastIndexOf("/"));
                    LocalDateTime now = LocalDateTime.now();
                    //备份压缩文件名称
                    String backupsName="teamwire_backups_"+ now.getYear()+"_"+now.getMonthValue()+"_" +now.getDayOfMonth()+"_"
                            +now.getHour()+"_"+now.getMinute()+"_"+String.valueOf(System.currentTimeMillis()).substring(0,9)+".tar.gz";

                    String backupsAbsoluteUrl = substring + "/"+backupsName;
                    // 创建tar输出流
                    FileOutputStream fos = new FileOutputStream(backupsAbsoluteUrl);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    GzipCompressorOutputStream gzos = new GzipCompressorOutputStream(bos);
                    TarArchiveOutputStream tos = new TarArchiveOutputStream(gzos);

                    RepositoryFileUtil.compressFolder(backupPath,"",tos);
                    joinBackupsLog(" compress tar.gz success...[DONE]");
                    logger.info("压缩成功");
                    // 关闭流
                    tos.close();
                    gzos.close();
                    bos.close();
                    fos.close();

                     /*
                     *  删除备份文件夹
                     * */
                    FileUtils.deleteDirectory(new File(backupPath));


                    joinBackupsLog(" Backups file success end [DONE]");

                    //修该备份text信息
                    writeFile();
                }catch (Exception e){
                    joinBackupsLog(" Backups file fail end,errorMsg:"+e.getMessage());
                    logger.info("错误信息:"+e.getMessage());
                    //修该备份text信息
                    writeFile();
                    new ApplicationException(e.getMessage());
                }
            }
        });
        return "OK";
    }

    @Override
    public String recoveryData(String fileName) {
        String loginId = LoginContext.getLoginId();
        backupsExecLog.remove(loginId);
        recoveryLog.remove(loginId);
        joinRecoveryLog("start decompression "+fileName+" ...");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    /**
                     *  解压tar.gz包
                     */
                    //压缩包的绝对路径
                    String DecFileUrl = backupAddress + "/" + fileName;
                    //压缩后的文件绝对路径
                    String name = fileName.substring(0, fileName.indexOf(".tar.gz"));
                    String afterDecFileUrl = backupAddress + "/" + name + "/";
                    //解压tar.gz
                    RepositoryFileUtil.decompression(DecFileUrl,afterDecFileUrl);
                    joinRecoveryLog(" decompression "+fileName+" success [DONE]");

                    /**
                     *  恢复postgreSQL 数据
                     */
                    joinRecoveryLog("start Recovery PostgreSQL database tiklab_xcode ...");
                    executeRecoveryScript(afterDecFileUrl);
                    joinRecoveryLog("Recovery PostgreSQL database tiklab_xcode success [DONE]");


                    /**
                     *  删除解压后的文件
                     */
                    FileUtils.deleteDirectory(new File(backupAddress + "/" + name ));

                    joinRecoveryLog("Recovery success end [DONE]");

                }catch (Exception e){
                    joinRecoveryLog("Recovery fail end，errorMsg:"+e.getMessage());
                    throw  new ApplicationException(e.getMessage());
                }
            }
        });
        return "ok";
    }

//    @Override
//    public void updateBackups(Backups backups)  {
//
//        File file = new File(AppHomeContext.getAppHome()+"/file/backups");
//        String fileData = gainFileData(file);
//
//        JSONObject jsonObject = JSONObject.parseObject(fileData);
//
//        if (StringUtils.isNotEmpty(backups.getTaskState())){
//            String taskState = jsonObject.get("task-state").toString();
//             fileData = fileData.replace(taskState, backups.getTaskState());
//        }
//
//        try {
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write(fileData);
//            fileWriter.close();
//        }catch (Exception e){
//            throw new ApplicationException(5000,e.getMessage());
//        }
//    }

//    @Override
//    public Backups findBackups() {
//        Backups backups = new Backups();
//        File file = new File(AppHomeContext.getAppHome()+"/file/backups");
//        String fileData = gainFileData(file);
//        if (StringUtils.isEmpty(fileData)){
//            throw  new ApplicationException(5000,"数据不存在");
//        }
//        JSONObject jsonObject = JSONObject.parseObject(fileData);
//        String taskState = jsonObject.get("task-state").toString();
//        String backupsTime = jsonObject.get("backups-time").toString();
//
//        backups.setBackupsAddress(yamlDataMaService.backupAddress());
//        backups.setTaskState(taskState);
//        backups.setNewBackupsTime(backupsTime);
//        backups.setNewResult("non");
//        String result = backupsExecLog.get(LoginContext.getLoginId());
//        if (StringUtils.isNotEmpty(result)){
//            backups.setNewResult("fail");
//           if (result.contains("Backups file success end")){
//               backups.setNewResult("success");
//           }
//        }
//        return backups;
//    }



    @Override
    public String gainBackupsRes(String type) {

        String loginId = LoginContext.getLoginId();
        if (("backups").equals(type)){
            String backups = backupsExecLog.get(loginId);
            if (StringUtils.isEmpty(backups)){
                return null;
            }
           return backups;
        }
        String recovery = recoveryLog.get(loginId);
        if (StringUtils.isEmpty(recovery)){
            return null;
        }
        return recovery;
    }

    @Override
    public void uploadBackups(InputStream inputStream, String fileName,String userId) {
        try {
            //获取text文件信息
            File file = new File(AppHomeContext.getAppHome() + "/file/backups");
            String fileData = gainFileData(file);

            JSONObject jsonObject = JSONObject.parseObject(fileData);
            String backUpsUrl = jsonObject.get("backups-url").toString();
            String substring = backUpsUrl.substring(0, backUpsUrl.lastIndexOf("/"));

            //如果文件夹不存在就创建文件夹
            File reduceDir = new File(substring);
            if (!reduceDir.exists() && !reduceDir.isDirectory()) {
                reduceDir.mkdirs();
            }

            //上传备份压缩文件的绝对路径
            String reduceUrl = substring + "/" + fileName;
            File reduceFile = new File(reduceUrl);
            //文件已经存在
            if (!reduceFile.exists()) {

                //创建文件
                File backUpsUrlFile = new File(reduceUrl);
                backUpsUrlFile.createNewFile();
                //写入文件
                FileOutputStream outputStream = new FileOutputStream(reduceUrl);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            }

        }catch (Exception e){
            throw  new ApplicationException(e.getMessage());
        }
    }

    /**
     *  dump database 备份脚本
     "*/
    public void executeScript(String backUpsUrl) throws IOException, InterruptedException {

        String[] args = new String[7];
        args[0] = "host="+backUpDataService.host();
        args[1] = "port=5432";
        args[2] = "userName="+jdbcUserName;
        args[3] = "password="+jdbcPassword;
        args[4] = "dbName="+ backUpDataService.dbName();
        args[5] = "schemaName="+ backUpDataService.schemaName();
        args[6] = "backupsUrl="+backUpsUrl;

        String scriptFile = AppHomeContext.getAppHome() + "/bin/backups.sh";
        Process ps = Runtime.getRuntime().exec(scriptFile,args);
        ps.waitFor();
    }


    /**
     *  执行恢复PostgreSQL 数据脚本
     * @param backUpsSqlUrl 备份sql地址
     */
    public void executeRecoveryScript(String backUpsSqlUrl) throws IOException, InterruptedException {
        String[] args = new String[7];
        args[0] = "host="+ backUpDataService.host();
        args[1] = "port=5432";
        args[2] = "userName="+jdbcUserName;
        args[3] = "password="+jdbcPassword;
        args[4] = "dbName="+ backUpDataService.dbName();
        args[5] = "schemaName="+ backUpDataService.schemaName();
        args[6] = "backupsSqlUrl="+backUpsSqlUrl;

        Process ps = Runtime.getRuntime().exec(AppHomeContext.getAppHome()+"/file/recovery.sh",args);
        ps.waitFor();
    }


    /**
     *  读取file 文件
     *  @param file     文件
     * @return
     */
    public String gainFileData(File file){
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StringBuilder result = new StringBuilder();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
            String lineTxt = null;
            while ((lineTxt = bfr.readLine()) != null) {
                String a=lineTxt;
                result.append(lineTxt).append(System.lineSeparator());
            }
            String toString = result.toString();

            inputStream.close();
            return toString;
        }catch (IOException e){
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     *  修改备份记录写入文件
     */
    public void writeFile(){
        File file = new File(AppHomeContext.getAppHome()+"/file/backups");
        if (file.exists()){
            String  fileData = gainFileData(file);

            JSONObject jsonObject = JSONObject.parseObject(fileData);

            String backupsTime = jsonObject.get("backups-time").toString();
            fileData =fileData.replace(backupsTime, RepositoryFileUtil.date(1,new Date()));

            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(fileData);
                fileWriter.close();
            }catch (Exception e){
                throw new ApplicationException(5000,e.getMessage());
            }
        }
    }

    /**
     *  拼接备份日志
     *  @param log 日志
     */
    public void joinBackupsLog(String log){
        LocalDateTime now = LocalDateTime.now();
        // 自定义时间格式
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String customDateTime = now.format(customFormatter);
        String resultLog = customDateTime + "---" + log;
        String loginId = LoginContext.getLoginId();
        String logs = backupsExecLog.get(loginId);
        if (StringUtils.isEmpty(logs)){
            backupsExecLog.put(loginId,resultLog);
        }else {
            backupsExecLog.put(loginId,logs+"\n"+resultLog);
        }
        logger.info("日志:"+log);
    }

    /**
     *  拼接恢复日志
     *  @param log 日志
     */
    public void joinRecoveryLog(String log){
        LocalDateTime now = LocalDateTime.now();
        // 自定义时间格式
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String customDateTime = now.format(customFormatter);
        String resultLog = customDateTime + "---" + log;
        String loginId = LoginContext.getLoginId();
        String logs = recoveryLog.get(loginId);
        if (StringUtils.isEmpty(logs)){
            recoveryLog.put(loginId,resultLog);
        }else {
            recoveryLog.put(loginId,logs+"\n"+resultLog);
        }
        logger.info("日志:"+logs);
    }

    /**
     *  是否有足够空间备份
     *  @param backupFile 备份file
     * @param   repositoryFile 代码库总大小
     */
    public boolean memSize(File backupFile,File repositoryFile){
        //当前文件夹所在磁盘的总大小
        long totalSpace = backupFile.getTotalSpace();
        //备份文件夹占用大小
        long folderSize = FileUtils.sizeOf(backupFile);

        //代码库仓库的大小
        long repositorySize = FileUtils.sizeOf(repositoryFile);
        //留出1G的空间
        long l = repositorySize + 1073741824;


        long residue = totalSpace - folderSize;
        if (residue>l){
            return  true;
        }
        return false;
    }

}
