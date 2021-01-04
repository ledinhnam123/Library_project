/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.Temporal;

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
    private int isActive;
    @Column(name = "delete_at")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deleteAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<BorrowBookEntity> borrowBook;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return String.valueOf(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        CustomersEntity cut = (CustomersEntity) obj;
        return this.getId() == cut.getId();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.getId();
        return hash;
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

    /**
     * @return the isActive
     */
    public int getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the borrowBook
     */
    public Set<BorrowBookEntity> getBorrowBook() {
        return borrowBook;
    }

    /**
     * @param borrowBook the borrowBook to set
     */
    public void setBorrowBook(Set<BorrowBookEntity> borrowBook) {
        this.borrowBook = borrowBook;
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
     * @return the deleteAt
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * @param deleteAt the deleteAt to set
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

}
