package com.soses.multilines.dto;

public class ProductDTO {

	private int productId;
	
	private String productCode;
	
	private String productName;
	

	public ProductDTO(int productId, String productCode, String productName) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productCode=" + productCode + ", productName=" + productName
				+ "]";
	}
	
	
}
