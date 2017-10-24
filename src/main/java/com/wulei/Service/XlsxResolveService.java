package com.wulei.Service;

import java.util.List;

public interface XlsxResolveService {
    public void initFile(String filePath);

    public List<Object> getContentBySheetName(String sheetName);

    public List<String> getSheetList();

}
