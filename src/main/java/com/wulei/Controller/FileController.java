package com.wulei.Controller;

import com.wulei.Beans.File;
import com.wulei.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file, HttpSession session){
        return fileService.uploadFile(file, session);
    }

    @RequestMapping(value = "/getfilelist", method = RequestMethod.GET)
    public @ResponseBody List<File> getFileList(HttpSession session){
        return fileService.getUserFileList(session);
    }

}
