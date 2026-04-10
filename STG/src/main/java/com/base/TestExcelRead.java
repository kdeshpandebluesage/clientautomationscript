package com.base;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestExcelRead {
    public static void main(String[] args) throws IOException {
        List<Map<String, String>> data = ExcelReader.readExcel("src/main/resources/data.xlsx");
        for (Map<String, String> row : data) {
            System.out.println("Row: " + row);
        }
    }
}
