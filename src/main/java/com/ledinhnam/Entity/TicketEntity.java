/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Hp
 */
@Entity
@Table(name="ticket")
public class TicketEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="fine_money")
	private double fineMoney;
	
	@ManyToOne
	@JoinColumn(name="borrow_ticket_id")
	private BorrowTicketsEntity borrowTicket;
	
	
	public BorrowTicketsEntity getBorrowTicket() {
		return borrowTicket;
	}
	public void setBorrowTicket(BorrowTicketsEntity borrowTicket) {
		this.borrowTicket = borrowTicket;
	}
	public TicketEntity() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public double getFineMoney() {
		return fineMoney;
	}
	public void setFineMoney(double fineMoney) {
		this.fineMoney = fineMoney;
	}
	
	
}

