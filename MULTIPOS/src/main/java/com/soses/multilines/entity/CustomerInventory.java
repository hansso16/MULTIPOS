package com.soses.multilines.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.soses.multilines.common.UnitModeEnum;
import com.soses.multilines.common.UnitModeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_inventory")
@NamedQuery(name="CustomerInventory.findAll", query="SELECT ci FROM CustomerInventory ci")
public class CustomerInventory implements Serializable {

	private static final long serialVersionUID = 5996528358278924049L;

	@EmbeddedId
	private CustomerInventoryPK id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("agentId")
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /* ---------------- INVENTORY FIELDS ---------------- */

    @Column(name = "beginning_case")
    private Integer beginningCase = 0;

    @Column(name = "beginning_piece")
    private Integer beginningPiece = 0;

    @Column(name = "delivery_case")
    private Integer deliveryCase = 0;

    @Column(name = "delivery_piece")
    private Integer deliveryPiece = 0;

    @Column(name = "ending_case")
    private Integer endingCase = 0;

    @Column(name = "ending_piece")
    private Integer endingPiece = 0;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    /* ---------------- METADATA ---------------- */

    @Convert(converter = UnitModeConverter.class)
    @Column(name = "unit_mode", nullable = false, length = 10)
    private UnitModeEnum unitMode; // CASE or PIECE

    @Column(name = "entry_timestamp", nullable = false)
    private LocalDateTime entryTimestamp;

    @Column(name = "submitted_by", nullable = false)
    private String submittedBy; // agent username
    
    public CustomerInventory() {}

	public CustomerInventoryPK getId() {
		return id;
	}

	public void setId(CustomerInventoryPK id) {
		this.id = id;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public UnitModeEnum getUnitMode() {
		return unitMode;
	}

	public void setUnitMode(UnitModeEnum unitMode) {
		this.unitMode = unitMode;
	}

	public LocalDateTime getEntryTimestamp() {
		return entryTimestamp;
	}

	public void setEntryTimestamp(LocalDateTime entryTimestamp) {
		this.entryTimestamp = entryTimestamp;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	@Override
	public String toString() {
		return "CustomerInventory [id=" + id + ", agent=" + agent + ", customer=" + customer + ", product=" + product
				+ ", beginningCase=" + beginningCase + ", beginningPiece=" + beginningPiece + ", deliveryCase="
				+ deliveryCase + ", deliveryPiece=" + deliveryPiece + ", endingCase=" + endingCase + ", endingPiece="
				+ endingPiece + ", deliveryDate=" + deliveryDate + ", unitMode=" + unitMode + ", entryTimestamp="
				+ entryTimestamp + ", submittedBy=" + submittedBy + "]";
	}

	public CustomerInventory(CustomerInventoryPK id, Agent agent, Customer customer, Product product,
			Integer beginningCase, Integer beginningPiece, Integer deliveryCase, Integer deliveryPiece,
			Integer endingCase, Integer endingPiece, LocalDate deliveryDate, UnitModeEnum unitMode,
			LocalDateTime entryTimestamp, String submittedBy) {
		super();
		this.id = id;
		this.agent = agent;
		this.customer = customer;
		this.product = product;
		this.beginningCase = beginningCase;
		this.beginningPiece = beginningPiece;
		this.deliveryCase = deliveryCase;
		this.deliveryPiece = deliveryPiece;
		this.endingCase = endingCase;
		this.endingPiece = endingPiece;
		this.deliveryDate = deliveryDate;
		this.unitMode = unitMode;
		this.entryTimestamp = entryTimestamp;
		this.submittedBy = submittedBy;
	}

}
