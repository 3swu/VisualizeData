package com.wulei.Controller;

import com.wulei.Beans.File;
import com.wulei.Service.FileService;
import com.wulei.Service.XlsxResolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    XlsxResolveService xlsxResolveService;

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file, HttpSession session){
        return fileService.uploadFile(file, session);
    }

    @RequestMapping(value = "/getfilelist", method = RequestMethod.GET)
    public @ResponseBody List<File> getFileList(HttpSession session){
        return fileService.getUserFileList(session);
    }

    @RequestMapping(value = "/getContent/{filename}/{sheetname}", method = RequestMethod.GET)
    public @ResponseBody List<Object> getContent(
            @PathVariable("filename") String fileName,
            @PathVariable("sheetname") String sheetName,
            HttpSession session){
        List<File> fileList = fileService.getUserFileList(session);
        for(File file : fileList){
            if(file.getFileName().equals(fileName)){
                xlsxResolveService.initFile(file.getFilePath());
                if(xlsxResolveService.getSheetList().contains(sheetName)){
                    return xlsxResolveService.getContentBySheetName(sheetName);
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/getFileSheetList/{filename}/tag", method = RequestMethod.GET)
    public @ResponseBody List<String> getFileSheetList(@PathVariable("filename") String fileName, HttpSession session){
        List<File> fileList = fileService.getUserFileList(session);
        for(File file : fileList){
            if(file.getFileName().equals(fileName)){
                xlsxResolveService.initFile(file.getFilePath());
                return xlsxResolveService.getSheetList();
            }
        }
        return null;
    }

}
