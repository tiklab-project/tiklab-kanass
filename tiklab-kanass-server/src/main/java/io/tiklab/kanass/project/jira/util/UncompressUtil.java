package io.tiklab.kanass.project.jira.util;

import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 读取压缩文件内容
 */
public class UncompressUtil {

    /**
     * 不解压读取压缩文件内容
     * @param fileInputStream zip源文件
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public BufferedReader readZIP(InputStream fileInputStream) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        while (!ObjectUtils.isEmpty(nextEntry)){
            if (nextEntry.toString().equals("entities.xml")){
                BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream));
                while (reader.readLine()!=null){
                    String readLine = reader.readLine();
                }
                return reader;
            }
            }
        //关闭流
        zipInputStream.closeEntry();
        fileInputStream.close();
        return null;
        }

    /**
     * 解压压缩文件
     * @param InputStream zip源文件
     * @throws RuntimeException 解压失败会抛出运行时异常

     */
    public BufferedReader unZIP(InputStream InputStream,String path) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(InputStream);
        ZipEntry entry=null;
        File file=null;
        while ((entry=zipInputStream.getNextEntry())!=null){
            System.out.println(entry);
            System.out.println(entry.getName());
            if (!entry.isDirectory()){
                file = new File(path, entry.getName());
                if (!file.exists()){
                    new File(file.getParent()).mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int len=-1;
                byte[] bytes = new byte[1024];
                while ((len=zipInputStream.read(bytes))!=-1){
                    bufferedOutputStream.write(bytes,0,len);
                }
                bufferedOutputStream.close();
                fileOutputStream.close();
            }
        }
        return null;
    }
}
