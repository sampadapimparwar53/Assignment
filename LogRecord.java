package com.example.ReadExcel.entity;

import java.time.LocalDateTime;

public class LogRecord {
    private String logType;
    private LocalDateTime timestamp;
    private String message;
	
    
    public LogRecord(String logType, LocalDateTime timestamp, String message) {
		super();
		this.logType = logType;
		this.timestamp = timestamp;
		this.message = message;
	}


	public LogRecord() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getLogType() {
		return logType;
	}


	public void setLogType(String logType) {
		this.logType = logType;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
    
}
