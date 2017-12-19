package com.trxade.webservice.DO;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TrxadeProduct implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrxadeProduct() {
		
	}

	public TrxadeProduct(String type, String sku, String ndc, BigDecimal quantityForSale) {
		super();
		this.type = type;
		this.sku = sku;
		this.ndc = ndc;
		this.quantityForSale = quantityForSale;
	}
	
	public TrxadeProduct(String type, String sku, String ndc, String tradeName, String manufacturer, String description,
			String strength, String packageSize, String pricePerItem, BigDecimal quantityForSale, String endDate,
			String itemExpirationDate, String lotNumber, String repack, String authorizedDistibutor, BigDecimal allocation,
			String brnadOrGeneric) {
		super();
		this.type = type;
		this.sku = sku;
		this.ndc = ndc;
		this.tradeName = tradeName;
		this.manufacturer = manufacturer;
		this.description = description;
		this.strength = strength;
		this.packageSize = packageSize;
		this.pricePerItem = pricePerItem;
		this.quantityForSale = quantityForSale;
		this.endDate = endDate;
		this.itemExpirationDate = itemExpirationDate;
		this.lotNumber = lotNumber;
		this.repack = repack;
		this.authorizedDistibutor = authorizedDistibutor;
		this.allocation = allocation;
		this.brnadOrGeneric = brnadOrGeneric;
	}
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("SKU")
	private String sku;
	
	@JsonProperty("NDC")
	private String ndc;
	
	@JsonProperty("TradeName")
	private String tradeName;
	
	@JsonProperty("Manufacturer")
	private String manufacturer;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Strength")
	private String strength;
	
	@JsonProperty("PackageSize")
	private String packageSize;
	
	@JsonProperty("PricePerItem")
	private String pricePerItem;
	
	@JsonProperty("QuantityForSale")
	private BigDecimal quantityForSale;
	
	@JsonProperty("EndDate")
	//Date column
	private String endDate;
	
	@JsonProperty("ItemExpirationDate")
	//Date Column
	private String itemExpirationDate;
	
	@JsonProperty("LotNumber")
	private String lotNumber;
	
	@JsonProperty("Repack")
	private String repack;
	
	@JsonProperty("AuthorizedDistributor")
	private String authorizedDistibutor;
	
	@JsonProperty("Allocation")
	private BigDecimal allocation;
	
	@JsonProperty("BrandOrGeneric")
	private String brnadOrGeneric;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNdc() {
		return ndc;
	}
	public void setNdc(String ndc) {
		this.ndc = ndc;
	}
	public String getTradeName() {
			return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}
	public String getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(String packagePerItem) {
		this.pricePerItem = packagePerItem;
	}
	public BigDecimal getQuantityForSale() {
		/*if(quantityForSale.toString().equals("0.00") && null != quantityForSale)
		{
			quantityForSale=new BigDecimal("1");
		}*/
		return quantityForSale;
	}
	public void setQuantityForSale(BigDecimal quantityForSale) {
		this.quantityForSale = quantityForSale;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getItemExpirationDate() {
		return itemExpirationDate;
	}
	public void setItemExpirationDate(String itemExpirationDate) {
		this.itemExpirationDate = itemExpirationDate;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getRepack() {
		return repack;
	}
	public void setRepack(String repack) {
		this.repack = repack;
	}
	public String getAuthorizedDistibutor() {
		return authorizedDistibutor;
	}
	public void setAuthorizedDistibutor(String authorizedDistibutor) {
		this.authorizedDistibutor = authorizedDistibutor;
	}
	public BigDecimal getAllocation() {
		return allocation;
	}
	public void setAllocation(BigDecimal allocation) {
		this.allocation = allocation;
	}
	public String getBrnadOrGeneric() {
		return brnadOrGeneric;
	}
	public void setBrnadOrGeneric(String brnadOrGeneric) {
		this.brnadOrGeneric = brnadOrGeneric;
	}

	
}
