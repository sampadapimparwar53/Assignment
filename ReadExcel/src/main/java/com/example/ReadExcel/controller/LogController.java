package com.example.ReadExcel.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReadExcel.entity.LogRecord;
import com.example.ReadExcel.service.LogService;

@RestController
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/logs/{logType}/recent")
    public ResponseEntity<List<LogRecord>> getRecentLogs(@PathVariable String logType) {
        try {
            List<LogRecord> records = logService.getRecentRecords(logType);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs/last-error")
    public ResponseEntity<String> getLastErrorMessage() {
        try {
            String message = logService.getLastErrorMessage();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs/matching-records")
    public ResponseEntity<List<LogRecord>> getMatchingRecords(@RequestParam String userInput) {
        try {
            List<LogRecord> records = logService.getMatchingRecords(userInput);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
