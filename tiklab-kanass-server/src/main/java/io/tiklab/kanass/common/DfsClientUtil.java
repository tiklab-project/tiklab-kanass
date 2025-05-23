package io.tiklab.kanass.common;

import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.model.object.DfsObjectMeta;
import io.tiklab.dfs.common.model.object.FindRequest;
import io.tiklab.dfs.common.model.object.PutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class DfsClientUtil {
    @Autowired
    private DfsClient dfsClient;

    /**
     * 上传
     * @param inputStream
     * @param fileName
     * @param filetype
     * @return
     */
    public String put(InputStream inputStream, String fileName, String filetype){
        PutRequest putRequest = new PutRequest();
        putRequest.setInputStream(inputStream);
        putRequest.setFileName(fileName);
        putRequest.setFileType(filetype);

        return dfsClient.put(putRequest);
    }

//    /**
//     * 下载
//     * @param objectId
//     * @return
//     */
//    public DfsObjectMeta find(String objectId, FileOutputStream fileOutputStream){
//        FindRequest findRequest = new FindRequest(objectId, fileOutputStream);
//        dfsClient.findMeta(findRequest);
//    }
}
