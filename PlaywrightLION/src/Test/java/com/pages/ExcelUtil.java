package com.pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
            Sheet sheet = workbook.getSheetAt(0);
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
                    rowData.put(header, value);
                }
                rows.add(rowData);
            }
        }
        return rows;
    }

}