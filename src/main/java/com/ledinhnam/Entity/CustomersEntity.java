/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "customers")
public class CustomersEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	private String phone;
	@Column(name = "isActive")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
	private Set<BorrowTicketsEntity> borrowTickets;

           @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
           private Set<TicketEntity> tickets;
	public CustomersEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

     public boolean addOrSaveBook(CustomersEntity customer) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

    /**
     * @return the borrowTickets
     */
    public Set<BorrowTicketsEntity> getBorrowTickets() {
        return borrowTickets;
    }

    /**
     * @param borrowTickets the borrowTickets to set
     */
    public void setBorrowTickets(Set<BorrowTicketsEntity> borrowTickets) {
        this.borrowTickets = borrowTickets;
    }

    /**
     * @return the tickets
     */
    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    /**
     * @param tickets the tickets to set
     */
    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}

