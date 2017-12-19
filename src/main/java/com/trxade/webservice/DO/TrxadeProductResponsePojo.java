package com.trxade.webservice.DO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties("Type")
public class TrxadeProductResponsePojo extends TrxadeProduct{
	
	public TrxadeProductResponsePojo() {
		
	}

	public TrxadeProductResponsePojo(String type, String sku, String ndc, String tradeName, String manufacturer,
			String description, String strength, String packageSize, String packagePerItem, BigDecimal quantityForSale,
			String endDate, String itemExpirationDate, String lotNumber, String repack, String authorizedDistibutor,
			BigDecimal allocation, String brnadOrGeneric, String errorCode, String valueSubmitted) {
		super(type, sku, ndc, tradeName, manufacturer, description, strength, packageSize, packagePerItem,
				quantityForSale, endDate, itemExpirationDate, lotNumber, repack, authorizedDistibutor, allocation,
				brnadOrGeneric);
		this.errorCode = errorCode;
		this.valueSubmitted = valueSubmitted;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**/
	
	@JsonProperty("ERROR_CODE")
	private String errorCode;
	
	@JsonProperty("ValueSubmitted")
	private String valueSubmitted;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getValueSubmitted() {
		return valueSubmitted;
	}

	public void setValueSubmitted(String valueSubmitted) {
		this.valueSubmitted = valueSubmitted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
