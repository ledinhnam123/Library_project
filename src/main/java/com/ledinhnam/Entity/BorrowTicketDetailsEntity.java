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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrow_ticket_details")
public class BorrowTicketDetailsEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookEntity book;

	@ManyToOne
	@JoinColumn(name = "borrow_ticket_id")
	private BorrowTicketsEntity borrowTicketD;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public BorrowTicketsEntity getBorrowTicketD() {
		return borrowTicketD;
	}

	public void setBorrowTicketD(BorrowTicketsEntity borrowTicketD) {
		this.borrowTicketD = borrowTicketD;
	}

}
