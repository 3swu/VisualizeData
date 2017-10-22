package com.wulei.Service.Impl;

import com.wulei.Beans.File;
import com.wulei.Beans.User;
import com.wulei.DAO.FileMapper;
import com.wulei.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wulei
 * 2017/10/22
 * FileService implements
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    //文件保存的天数
    private static int fileSaveDays = 7;

    /**
     * Upload file service, first to check whether if the file is existed in database;
     * @param uploadFile
     * @param session
     * @return 上传结果信息
     */
    public String uploadFile(MultipartFile uploadFile, HttpSession session) {
        if(!uploadFile.isEmpty()){
            User user = (User)session.getAttribute("user");
            String fileName = uploadFile.getOriginalFilename();
            File file = new File(user.getId(), fileName);

            if(fileMapper.getFileByFileNameAndUserId(file) != null){
                return "file existed";
            } else {
                try{
                    String fileSavePath = "C:\\Users\\wulei\\Desktop\\UploadFile\\files";
                    java.io.File dir = new java.io.File(fileSavePath + java.io.File.separator);
                    if(!dir.exists())
                        dir.mkdirs();

                    java.io.File saveFile = new java.io.File(dir.getAbsolutePath() + java.io.File.separator + fileName);
                    uploadFile.transferTo(saveFile);

                    file.setFilePath(dir.getAbsolutePath() + java.io.File.separator + fileName);
                    file.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    if(fileMapper.insertFile(file) != 1)
                        return "failed";
                    return "success: " + fileName;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return "failed";
    }

    /**
     * 得到当前用户的文件列表并且删除过期的文件
     * @param session
     * @return
     */
    public List<File> getUserFileList(HttpSession session) {
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            List<File> fileList = fileMapper.getFileListByUserId(user.getId());
            for(File file : fileList){
                if(isFileExpired(file.getUploadTime())){
                    fileList.remove(file);
                    java.io.File expiredFile = new java.io.File(file.getFilePath());
                    if(expiredFile.exists())
                        expiredFile.delete();
                    fileMapper.deleteFile(file);
                }
            }
            return fileList;
        }
        return null;
    }

    /**
     * 判断这个文件是否过期
     * @param fileDateStr
     * @return 如果过期，返回true，反之返回false
     */
    private static boolean isFileExpired(String fileDateStr){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date fileDate = formater.parse(fileDateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fileDate);
            calendar.add(Calendar.DAY_OF_YEAR,fileSaveDays);
            Date expireDate = calendar.getTime();
            Date rightNow = new Date();
            if(expireDate.before(rightNow))
                return true;
            return false;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }
}
