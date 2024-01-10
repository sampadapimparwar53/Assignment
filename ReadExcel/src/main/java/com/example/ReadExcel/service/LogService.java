package com.example.ReadExcel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.ReadExcel.entity.LogRecord;


@Service
public class LogService {

	 private final String FILE_PATH = "path/to/excel/file.xlsx";

	    public List<LogRecord> getRecentRecords(String logType) throws IOException {
	        List<LogRecord> records = new ArrayList<>();
	        FileInputStream file = new FileInputStream(new File(FILE_PATH));
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        for (int i = sheet.getLastRowNum(); i >= 0; i--) {
	            XSSFRow row = sheet.getRow(i);
	            if (row.getCell(0).getStringCellValue().equals(logType)) {
	                LogRecord record = new LogRecord();
	                record.setLogType(logType);
	                record.setTimestamp(LocalDateTime.parse(row.getCell(1).getStringCellValue()));
	                record.setMessage(row.getCell(2).getStringCellValue());
	                records.add(record);
	            }
	        }

	        workbook.close();
	        file.close();

	        return records;
	    }

	    public String getLastErrorMessage() throws IOException {
	        FileInputStream file = new FileInputStream(new File(FILE_PATH));
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        for (int i = sheet.getLastRowNum(); i >= 0; i--) {
	            XSSFRow row = sheet.getRow(i);
	            if (row.getCell(0).getStringCellValue().equals("ERROR")) {
	                workbook.close();
	                file.close();
	                return "Last error occurred at " + row.getCell(1).getStringCellValue() + ": " + row.getCell(2).getStringCellValue();
	            }
	        }

	        workbook.close();
	        file.close();

	        return "No errors found";
	    }

	    public List<LogRecord> getMatchingRecords(String userInput) throws IOException {
	        List<LogRecord> records = new ArrayList<>();
	        FileInputStream file = new FileInputStream(new File(FILE_PATH));
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        for (int i = sheet.getLastRowNum(); i >= 0; i--) {
	            XSSFRow row = sheet.getRow(i);
	            if (row.getCell(2).getStringCellValue().contains(userInput)) {
	                LogRecord record = new LogRecord();
	                record.setLogType(row.getCell(0).getStringCellValue());
	                record.setTimestamp(LocalDateTime.parse(row.getCell(1).getStringCellValue()));
	                record.setMessage(row.getCell(2).getStringCellValue());
	                records.add(record);
	            }
	        }

	        workbook.close();
	        file.close();

	        return records;
	    }
}

