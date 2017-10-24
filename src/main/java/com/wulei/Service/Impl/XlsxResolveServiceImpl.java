package com.wulei.Service.Impl;

import com.wulei.Service.XlsxResolveService;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @since 2017/10/24
 * Resolve excel file
 */
@Service
public class XlsxResolveServiceImpl implements XlsxResolveService {

    private List<String> sheetList = null;
    private XSSFWorkbook workbook = null;

    /**
     * Init private objects
     * @param filePath
     */
    public void initFile(String filePath) {
        this.workbook = getFile(filePath);
        this.sheetList = new ArrayList<String>();

        for(int sheetNum = workbook.getNumberOfSheets(), i = 0;i<sheetNum;++i){
            this.sheetList.add(workbook.getSheetName(i));
        }
    }

    /**
     *
     * @param filePath
     * @return workbook
     */
    private static XSSFWorkbook getFile(String filePath){
        XSSFWorkbook workbook = null;
        try{
            workbook = new XSSFWorkbook(
                    new FileInputStream(filePath)
            );
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     *
     * @param sheetName
     * @return return type:List<Map<String,Object>>
     */
    public List<Object> getContentBySheetName(String sheetName) {
        List<Object> sheetContent = new ArrayList<Object>();

        XSSFSheet sheet = this.workbook.getSheet(sheetName);
        List<String> columnList = new ArrayList<String>();
        int lastCellNum = sheet.getRow(0).getLastCellNum();

        for(int i = 0;i <= sheet.getLastRowNum(); ++i){
            XSSFRow row = sheet.getRow(i);
            Map<String, Object> rowData = new HashMap<String, Object>();

            for(int j = row.getFirstCellNum(); j<lastCellNum; ++j){
                if(i == 0){
                    columnList.add(row.getCell(j).toString());
                    continue;
                }

                String cellData;
                try{
                    cellData = row.getCell(j).toString();
                }catch(NullPointerException e){
                    continue;
                }
                
                if(cellData != null){
                    try{
                        int numbericCellData = Integer.parseInt(cellData);
                        rowData.put(columnList.get(j),numbericCellData);
                    }catch(NumberFormatException e){
                        rowData.put(columnList.get(j),cellData);
                    }
                }
            }
            if(!rowData.isEmpty()){
                sheetContent.add(rowData);
            }
        }
        return sheetContent;
    }

    /**
     *
     * @return a list of all sheet's name
     */
    public List<String> getSheetList() {
        return this.sheetList;
    }
}
