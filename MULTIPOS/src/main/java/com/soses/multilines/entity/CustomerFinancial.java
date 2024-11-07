package com.soses.multilines.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the customer_financials database table.
 * 
 */
@Entity
@Table(name="customer_financials")
@NamedQuery(name="CustomerFinancial.findAll", query="SELECT c FROM CustomerFinancial c")
public class CustomerFinancial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private int customerId;

	@Column(precision=10, scale=2)
	private BigDecimal balance;

	@Column(name="CREDIT_LIMIT", precision=10, scale=2)
	private BigDecimal creditLimit;

	@Column(name="CREDIT_USED", precision=10, scale=2)
	private BigDecimal creditUsed;

	@Column(name="CUSTOMER_CODE", nullable=false, length=20)
	private String customerCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_TIMESTAMP")
	private LocalDateTime entryTimestamp;

	@Column(name="OVERDUE_AMOUNT", precision=10, scale=2)
	private BigDecimal overdueAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIMESTAMP")
	private LocalDateTime updateTimestamp;

	public CustomerFinancial() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getCreditLimit() {
		return this.creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getCreditUsed() {
		return this.creditUsed;
	}

	public void setCreditUsed(BigDecimal creditUsed) {
		this.creditUsed = creditUsed;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public LocalDateTime getEntryTimestamp() {
		return this.entryTimestamp;
	}

	public void setEntryTimestamp(LocalDateTime entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public BigDecimal getOverdueAmount() {
		return this.overdueAmount;
	}

	public void setOverdueAmount(BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public LocalDateTime getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}