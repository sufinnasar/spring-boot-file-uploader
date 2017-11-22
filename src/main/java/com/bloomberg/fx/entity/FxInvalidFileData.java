package com.bloomberg.fx.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="FILE_INVALID_DATA",indexes= {@Index(columnList="FILE_NAME",name="FILE_INVALID_DATA_FILE_NAME_INDEX" )})
public class FxInvalidFileData {
	
	@Id
	@Column(name = "ID")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@Column(name = "DEAL_ID")
	private String dealID;
	
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	
	@Column(name = "FROM_CURRENCY")
	private String fromCurrency;
	
	
	@Column(name = "TO_CURRENCY")
	private String toCurrency;
	
	
	@Column(name = "TIME_STAMP")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp timestampField;
	
	
	@Column(name = "DEAL_AMOUNT")
	private String dealAmount;
	
	
	@Column(name="REASON")
	private String reason;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDealID() {
		return dealID;
	}

	public void setDealID(String dealID) {
		this.dealID = dealID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Timestamp getTimestampField() {
		return timestampField;
	}

	public void setTimestampField(Timestamp timestampField) {
		this.timestampField = timestampField;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
