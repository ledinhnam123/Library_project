/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.CustomersEntity;
import com.ledinhnam.Entity.TicketEntity;
import com.ledinhnam.Service.TicketService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hp
 */
@ManagedBean
@Named(value = "ticketBean")
@RequestScoped
public class TicketBean {

    private int ticketId;
    private Date createDate;
    private Date expiredDate;
    private CustomersEntity customer;
    private TicketService ticketService = new TicketService();

    /**
     * Creates a new instance of TicketBean
     */
    public TicketBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) { // vào lần đầu thì ms lấy, bấm thì không làm nữa
            String tickId = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap().get("ticket_id");
            if (tickId != null && !tickId.isEmpty()) {
                TicketEntity t = ticketService.getTicketById(Integer.parseInt(tickId));
                this.ticketId = t.getId();
                this.createDate = t.getCreateAt();// lấy l
                this.expiredDate = t.getExpiredDate();
                this.customer = t.getCustomer();
            }
        }
    }

    //get Danh sach ticket
    public List<TicketEntity> getTicks() {
        List<TicketEntity> tickets = ticketService.getTickets(null);

        return tickets;
    }

    public String deleteTicket(TicketEntity ticket) throws Exception {
        if (ticketService.deleteTicket(ticket)) {
            return "Successful";
        }

        throw new Exception("Something Wrong!!");
    }

    
    ///save và update
    public String addTicket() {
        TicketEntity ticket;
        if (this.ticketId > 0) {
            ticket = ticketService.getTicketById(ticketId);//link tới
        } else {
            ticket = new TicketEntity();//stran\sient
        }
        ticket.setCreateAt(createDate);
        ticket.setExpiredDate(expiredDate);
        ticket.setCustomer(customer);

        try {
            if (true == ticketService.addOrsaveTicket(ticket)) {
                return "tiket-list?faces-redirect=true"; // duong link giao dien 
            }
        } catch (Exception ex) {
            Logger.getLogger(TicketEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "emloyees";
    }

    /**
     * @return the ticketId
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId the ticketId to set
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
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
     * @return the expiredDate
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * @param expiredDate the expiredDate to set
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
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
     * @return the ticketService
     */
    public TicketService getTicketService() {
        return ticketService;
    }

    /**
     * @param ticketService the ticketService to set
     */
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
