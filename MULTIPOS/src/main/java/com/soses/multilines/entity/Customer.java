package com.soses.multilines.entity;

import java.io.Serializable;
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
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private int customerId;

	@Column(length=100)
	private String address1;

	@Column(length=100)
	private String address2;

	@Column(length=100)
	private String barangay;

	@Column(name="BUSINESS_NAME", length=100)
	private String businessName;

	@Column(length=100)
	private String city;

	@Column(name="CUSTOMER_CODE", nullable=false, length=20)
	private String customerCode;

	@Column(name="CUSTOMER_STATUS", length=3)
	private String customerStatus;

	@Column(length=50)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_TIMESTAMP")
	private LocalDateTime entryTimestamp;

	@Column(name="FIRST_NAME", nullable=false, length=50)
	private String firstName;

	@Column(name="LAST_NAME", nullable=false, length=50)
	private String lastName;

	@Column(name="PHONE_NUMBER", length=15)
	private String phoneNumber;

	@Column(name="POSTAL_CODE", length=5)
	private String postalCode;

	@Column(length=100)
	private String province;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIMESTAMP")
	private LocalDateTime updateTimestamp;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getBarangay() {
		return this.barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getEntryTimestamp() {
		return this.entryTimestamp;
	}

	public void setEntryTimestamp(LocalDateTime entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public LocalDateTime getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}