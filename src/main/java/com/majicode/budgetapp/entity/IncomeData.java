package com.majicode.budgetapp.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INCOME")
public class IncomeData {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID incomeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PLANNED_AMOUNT")
	private Double plannedAmount;

	@Column(name = "RECEIVED_AMOUNT")
	private Double receivedAmount;

	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Column(name = "DATE_UPDATED")
	private Date dateUpdated;

	public UUID getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(UUID incomeId) {
		this.incomeId = incomeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPlannedAmount() {
		return plannedAmount;
	}

	public void setPlannedAmount(Double plannedAmount) {
		this.plannedAmount = plannedAmount;
	}

	public Double getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(Double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

}
