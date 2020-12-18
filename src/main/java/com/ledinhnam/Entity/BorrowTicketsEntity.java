/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Entity;

/**
 *
 * @author Hp
 */

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "borrow_ticket_details")
public class BorrowTicketsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "borrow_date")
	private Date boorrowDate;
	@Column(name = "appointment_date")
	private Date appointmentDate;
	@Column(name = "return_date")
	private Date returnDate;
	@Column(name = "status")
	private Boolean status;
	@Column(name = "note")
	private String node;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private EmployeesEntity employee;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomersEntity customer;

	@OneToMany(mappedBy = "borrowTicketD", fetch = FetchType.LAZY)
	private Set<BorrowTicketDetailsEntity> borrowTicketDetail;

	@OneToMany(mappedBy = "borrowTicket", fetch = FetchType.LAZY)
	private Set<TicketEntity> tickets;

	public Set<TicketEntity> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketEntity> tickets) {
		this.tickets = tickets;
	}

	public Set<BorrowTicketDetailsEntity> getBorrowTicketDetail() {
		return borrowTicketDetail;
	}

	public void setBorrowTicketDetail(Set<BorrowTicketDetailsEntity> borrowTicketDetail) {
		this.borrowTicketDetail = borrowTicketDetail;
	}

	public CustomersEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomersEntity customer) {
		this.customer = customer;
	}

	public BorrowTicketsEntity() {

	}

	public EmployeesEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeesEntity employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getBoorrowDate() {
		return boorrowDate;
	}

	public void setBoorrowDate(Date boorrowDate) {
		this.boorrowDate = boorrowDate;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}

