/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.CustomersEntity;
import com.ledinhnam.Service.CustomerService;
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
@Named(value = "customerBean")
@RequestScoped
public class CustomerBean {
 
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private static CustomerService customerService = new CustomerService();

    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) { // vào lần đầu thì ms lấy, bấm thì không làm nữa
            String cusId = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap().get("customer_id");
            if (cusId != null && !cusId.isEmpty()) {
                CustomersEntity c = customerService.getCustomerById(Integer.parseInt(cusId));
                this.customerId=c.getId(); // lấy l             
                this.name=c.getName();
                this.email = c.getEmail();
                this.phone = c.getPhone();
            }
        }
        
    }
    public String addCustomer(){
        CustomersEntity customer;
        if(this.customerId > 0)
            customer=customerService.getCustomerById(this.customerId);//link tới
        else
            customer = new CustomersEntity();//stran\sient
        
   
        customer.setEmail(email);
        customer.setPhone(phone);
        try {
             if (true == customerService.addOrSaveCustomer(customer)) {
                return "customer-list?faces-redirect=true"; // duong link giao dien 
            }
        } catch (Exception ex) {
             Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "customers";
    }

    public List<CustomersEntity> getCustomers() {
        List<CustomersEntity> customer = getCustomerService().getCustomers();
        return customer;
    }
    //xóa độc giả
        public String deleteCustomer(CustomersEntity customer) throws Exception {
        if (getCustomerService().deleteCustomer(customer)) {
            return "Successful";
        }

        throw new Exception("Something Wrong!!");
    }
    
    

    /**
     * @return the customerService
     */
    public static CustomerService getCustomerService() {
        return customerService;
    }

    /**
     * @param aCustomerService the customerService to set
     */
    public static void setCustomerService(CustomerService aCustomerService) {
        customerService = aCustomerService;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

 

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
