//package com.pages;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//public class ExcelUtil {
//    public static List<Map<String, String>> readExcel(String path) throws IOException {
//        List<Map<String, String>> rows = new ArrayList<>();
//        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
//            Sheet sheet = workbook.getSheetAt(0);
//            Row header = sheet.getRow(0);
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row == null) continue;
//                Map<String, String> map = new HashMap<>();
//                for (int j = 0; j < header.getLastCellNum(); j++) {
//                    String key = header.getCell(j).getStringCellValue();
//                    System.out.println("Key: "+key);
//                    String value = row.getCell(j).toString();
//                    System.out.println("Value: "+value);
//                    map.put(key, value);
//                }
//                rows.add(map);
//            }
//        }
//        return rows;
//    }
//}

package com.pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.base.ExcelReader.getCellValueAsString;

public class ExcelUtil {

    // Helper method to safely get cell value as a String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Return empty string if cell is null
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Handle date formatting
                }
                return String.valueOf(cell.getNumericCellValue()); // Convert number to string
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula(); // Return formula as string
            case BLANK:
                return ""; // Empty string for blank cells
            default:
                return cell.toString(); // Fallback to default toString for other cell types
        }
    }
    public static List<Map<String, String>> readExcel(String path) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row header = sheet.getRow(0);
            // Create a DataFormatter to handle all cell types gracefully
            DataFormatter formatter = new DataFormatter();

            if (header == null) {
                return rows; // Return empty list if the sheet has no header
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue; // Skip empty rows

                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < header.getLastCellNum(); j++) {
                    Cell headerCell = header.getCell(j);
                    Cell currentCell = row.getCell(j);

                    if (headerCell != null) {
                        // Use the formatter to get the value as a string, which safely handles null cells
                        String key = formatter.formatCellValue(headerCell);
                      //  System.out.println("Key: "+key);
                        String value = formatter.formatCellValue(currentCell);
                     //   System.out.println("Value: "+value);
                        map.put(key, value);
                    }
                }
                rows.add(map);
            }
        }
        return rows;
    }
    public static List<Map<String, String>> readExcelWithTC(String path,String testCaseName) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
//            Sheet sheet = workbook.getSheetAt(0);
            Sheet sheet = workbook.getSheet("SUP_LION_LOP_LOS");
            if (sheet == null) {
                throw new IllegalArgumentException("No sheet found in the Excel file.");
            }
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("Header row is missing in the Excel sheet.");
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) {
                    continue; // Skip empty rows
                }

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String header = getCellValueAsString(headerRow.getCell(j));
                    String value = getCellValueAsString(currentRow.getCell(j));
                    if(j==0 && !testCaseName.equals(value)){
                        System.out.println("Test Case Name is not matching with the excel sheet. Test Case Name in excel sheet is: "+value+" and Test Case Name in test class is: "+testCaseName);
                        System.out.println("Skipping the test case: ");
                        break;
                    }
                    rowData.put(header, value);
                }
                if(rowData.size()>0)
                    rows.add(rowData);
            }
        }
        return rows;
    }
}
