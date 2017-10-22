package com.wulei.DAO;

import com.wulei.Beans.File;

import java.util.List;

/**
 * Created by wulei on 2017/10/22
 * DAO interface:FileMapper->FileMapper.xml
 */
public interface FileMapper {
    public int insertFile(File file);

    public int deleteFile(File file);

    public File getFileByFileId(int fileid);

    //参数file中必须封装FileName和UserId
    public File getFileByFileNameAndUserId(File file);

    public List<File> getFileListByUserId(int userid);
}
