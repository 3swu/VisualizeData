package com.wulei.Service;

import com.wulei.Beans.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wulei on 2017/10/22
 * Upload EXCEL file Service interface
 */
public interface FileService {
    public String uploadFile(MultipartFile uploadFile, HttpSession session);

    public List<File> getUserFileList(HttpSession session);
}
