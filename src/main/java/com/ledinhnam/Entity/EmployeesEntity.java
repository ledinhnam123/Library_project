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
@Table(name = "employees")
public class EmployeesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "identity_id")
    private String identityId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String pass;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "employee")
    private Set<BorrowBookEntity> borrowTickets;

    public Set<BorrowBookEntity> getBorrowTickets() {
        return borrowTickets;
    }

    public void setBorrowTickets(Set<BorrowBookEntity> borrowTickets) {
        this.borrowTickets = borrowTickets;
    }

    public EmployeesEntity() {

    }
    
    public EmployeesEntity(int id) {
           this.id = id;
           this.identityId = "";
           this.phone = "029378235";
           this.email = "tinhpham@gmail.com";
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
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
     * @return the identityId
     */
    public String getIdentityId() {
        return identityId;
    }

    /**
     * @param identityId the identityId to set
     */
    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

}
