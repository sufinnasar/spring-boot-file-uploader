package com.bloomberg.fx.entity;

public class FileStatus {
	private Double timeTaken;
	private String fileName;
	private String status;

	FileStatus() {

	}

	public FileStatus(Double timeTaken, String fileName, String status) {
		this.timeTaken = timeTaken;
		this.fileName = fileName;
		this.status = status;
	}

	public Double getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Double timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
