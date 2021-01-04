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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "borrow_book")
public class BorrowBookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeesEntity employee;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;
    @Column(name = "appointment_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date appointmentDate;
    @Column(name = "return_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date returnDate;
    
    @Column(name="money_fine")
    private double moneyFine;

    @OneToMany(mappedBy = "borrowBook", cascade = CascadeType.ALL)
    private Set<BorrowDetailsEntity> borrowDetail;
    public BorrowBookEntity() {


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the employee
     */
    public EmployeesEntity getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(EmployeesEntity employee) {
        this.employee = employee;
    }

    /**
     * @return the customer
     */
    public CustomersEntity getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomersEntity customer) {
        this.customer = customer;
    }

    /**
     * @return the appointmentDate
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the borrowDetail
     */
    public Set<BorrowDetailsEntity> getBorrowDetail() {
        return borrowDetail;
    }

    /**
     * @param borrowDetail the borrowDetail to set
     */
    public void setBorrowDetail(Set<BorrowDetailsEntity> borrowDetail) {
        this.borrowDetail = borrowDetail;
    }

    /**
     * @return the moneyFine
     */
    public double getMoneyFine() {
        return moneyFine;
    }

    /**
     * @param moneyFine the moneyFine to set
     */
    public void setMoneyFine(double moneyFine) {
        this.moneyFine = moneyFine;
    }

}
