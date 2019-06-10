package com.project.oa.base.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName: ExcelUtil
 * @Author: zhanghongkai
 * @Date: Create in 2019/6/10 9:03
 * @Version: 1.0
 */
public class ExcelUtil {
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static List<List<List<String>>> readExcel(MultipartFile file) throws IOException {
        List<List<List<String>>> sheets = new ArrayList<>();
        List<List<String>> rowList = null;
        List<String> cellList = null;
        String fileName = file.getOriginalFilename();
        if(!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)){
            System.out.println("不是excel文件");
            return null;
        }
        Workbook workbook = null;
        if(fileName.endsWith(XLS)){
            workbook = new HSSFWorkbook(file.getInputStream());
        }else if(fileName.endsWith(XLSX)){
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        if(workbook != null){
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            CellType cellType = null;
            String value = null;
            for (int i=0;i<workbook.getNumberOfSheets();i++){
                sheet = workbook.getSheetAt(i);
                rowList = new ArrayList<>();
                if(sheet != null && sheet.getPhysicalNumberOfRows() > 0){
                    for(int j=0;j<=sheet.getLastRowNum();j++){
                        row  = sheet.getRow(j);
                        cellList = new ArrayList<>();
                        if(row != null && row.getPhysicalNumberOfCells() > 0){
                            for(int k=0;k<row.getLastCellNum();k++){
                                cell = row.getCell(k);
                                if(cell != null){
                                    cellType = cell.getCellTypeEnum();
                                    if(cellType != null){
                                        if(cellType.equals(CellType.BLANK)){
                                            value = "";
                                        }else if(cellType.equals(CellType.BOOLEAN)){
                                            value = String.valueOf(cell.getBooleanCellValue());
                                        }else if(cellType.equals(CellType.ERROR)){
                                            value = String.valueOf(cell.getErrorCellValue());
                                        }else if(cellType.equals(CellType.FORMULA)){
                                            try {
                                                value = String.valueOf(cell.getNumericCellValue());
                                            }catch (Exception e){
                                                value = String.valueOf(cell.getStringCellValue());
                                            }
                                        }else if(cellType.equals(CellType.STRING)){
                                            value = String.valueOf(cell.getStringCellValue());
                                        }else if(cellType.equals(CellType.NUMERIC)){
                                            if(DateUtil.isCellDateFormatted(cell)){
                                                value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
                                            }else{
                                                value = String.valueOf(cell.getNumericCellValue());
                                            }
                                        }else{
                                            value = "";
                                        }
                                    }
                                    cellList.add(value);
                                }else{
                                    cellList.add("");
                                }
                            }
                            rowList.add(cellList);
                        }else{
                            rowList.add(cellList);
                        }
                    }
                    sheets.add(rowList);
                }else{
                    sheets.add(rowList);
                }
            }
        }
        return sheets;
    }
}
