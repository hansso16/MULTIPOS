package com.soses.multilines.dto;

import java.time.LocalDate;

public class CustomerProductFormDTO {

	private Integer productId;
	
	private String productCode;
	
	private String productName;
	
	private Integer beginningCase;
	private Integer beginningPiece;
	
	private Integer deliveryCase;
	private Integer deliveryPiece;
    private LocalDate deliveryDate;
    
    private Integer endingCase;
    private Integer endingPiece;
    
    private Integer suggestedCase;
    private Integer suggestedPiece;
    
    private boolean beginningLocked = false;
    private boolean readOnly = false;

	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
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


	public Integer getBeginningCase() {
		return beginningCase;
	}


	public void setBeginningCase(Integer beginningCase) {
		this.beginningCase = beginningCase;
	}


	public Integer getBeginningPiece() {
		return beginningPiece;
	}


	public void setBeginningPiece(Integer beginningPiece) {
		this.beginningPiece = beginningPiece;
	}


	public Integer getDeliveryCase() {
		return deliveryCase;
	}


	public void setDeliveryCase(Integer deliveryCase) {
		this.deliveryCase = deliveryCase;
	}


	public Integer getDeliveryPiece() {
		return deliveryPiece;
	}


	public void setDeliveryPiece(Integer deliveryPiece) {
		this.deliveryPiece = deliveryPiece;
	}


	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public Integer getEndingCase() {
		return endingCase;
	}


	public void setEndingCase(Integer endingCase) {
		this.endingCase = endingCase;
	}


	public Integer getEndingPiece() {
		return endingPiece;
	}


	public void setEndingPiece(Integer endingPiece) {
		this.endingPiece = endingPiece;
	}


	public boolean isBeginningLocked() {
		return beginningLocked;
	}


	public void setBeginningLocked(boolean beginningLocked) {
		this.beginningLocked = beginningLocked;
	}


	public boolean isReadOnly() {
		return readOnly;
	}


	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}


	public Integer getSuggestedCase() {
		return suggestedCase;
	}


	public void setSuggestedCase(Integer suggestedCase) {
		this.suggestedCase = suggestedCase;
	}


	public Integer getSuggestedPiece() {
		return suggestedPiece;
	}


	public void setSuggestedPiece(Integer suggestedPiece) {
		this.suggestedPiece = suggestedPiece;
	}


	@Override
	public String toString() {
		return "CustomerProductFormDTO [productId=" + productId + ", productCode=" + productCode + ", productName="
				+ productName + ", beginningCase=" + beginningCase + ", beginningPiece=" + beginningPiece
				+ ", deliveryCase=" + deliveryCase + ", deliveryPiece=" + deliveryPiece + ", deliveryDate="
				+ deliveryDate + ", endingCase=" + endingCase + ", endingPiece=" + endingPiece + ", suggestedCase="
				+ suggestedCase + ", suggestedPiece=" + suggestedPiece + ", beginningLocked=" + beginningLocked
				+ ", readOnly=" + readOnly + "]";
	}
	
	
}
