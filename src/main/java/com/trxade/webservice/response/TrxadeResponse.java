package com.trxade.webservice.response;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trxade.webservice.DO.TrxadeProductResponsePojo;

@ComponentScan
@JsonSerialize
@Configuration
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrxadeResponse {
	
	/*@JsonIgnoreProperties
	private TrxadeProductResponsePojo data;*/
	
	private String type;
	
	private String title;
	
	private String status;
	
	private String detail;

	/*public TrxadeProductResponsePojo getData() {
		return data;
	}

	public void setData(TrxadeProductResponsePojo data) {
		this.data = data;
	}*/

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
