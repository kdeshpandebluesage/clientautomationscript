package com.base;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> readExcel(String path) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new LinkedHashMap<>();
                for (int c = 0; c < headerRow.getLastCellNum(); c++) {
                    Cell headerCell = headerRow.getCell(c);
                    Cell valueCell = row.getCell(c);

                    String key = headerCell != null ? headerCell.getStringCellValue() : "Column" + c;
                    String value = valueCell != null ? getCellValueAsString(valueCell) : "";
                    rowData.put(key.trim(), value.trim());
                }
                rows.add(rowData);
            }
        }
        return rows;
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue().toString() : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            case BLANK: return "";
            default: return cell.toString();
        }
    }
}
