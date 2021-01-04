/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.CustomersEntity;
import com.ledinhnam.Service.CustomerService;
import java.util.ArrayList;
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
@Named(value = "customerBean")
@RequestScoped
public class CustomerBean {

    private int customerId;
    private String name;
    private String email;
    private String phone;
    private int isActive;
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
                this.customerId = c.getId(); // lấy l             
                this.name = c.getName();
                this.email = c.getEmail();
                this.phone = c.getPhone();
                this.isActive = c.getIsActive();

            }
        }

    }

    public String addCustomer() {
        CustomersEntity customer;
        if (this.getCustomerId() > 0) {
            customer = getCustomerService().getCustomerById(this.getCustomerId());//link tới
        } else {
            customer = new CustomersEntity();//stran\sient
        }
        customer.setIsActive(isActive);
        customer.setName(getName());
        customer.setEmail(getEmail());
        customer.setPhone(getPhone());
        try {
            if (true == getCustomerService().addOrSaveCustomer(customer)) {
                return "customer-list?faces-redirect=true"; // duong link giao dien 
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "customers";
    }

    public List<CustomersEntity> getCustomers() {
        List<CustomersEntity> customer = getCustomerService().getCustomers();
        List<CustomersEntity> customer2 = new ArrayList<>();
        for (CustomersEntity cus : customer) {
            CustomersEntity custo = new CustomersEntity();
            if (cus.getDeleteAt() == null) {
                custo.setId(cus.getId());
                custo.setPhone(cus.getPhone());
                custo.setName(cus.getName());
                custo.setEmail(cus.getEmail());
                custo.setIsActive(cus.getIsActive());
                customer2.add(custo);

            }
        }
        return customer2;
    }

    //xóa độc giả
    public String deleteCustomer(CustomersEntity customer) throws Exception {
        customer.setDeleteAt(new Date());
        if (customerService.deleteCustomer(customer)) {
            return "Successful";
        }

        throw new Exception("Something Wrong!!");
    }

    //update customer bookback
    public String updateCusbookBack(CustomersEntity customer) throws Exception {
        customer.setIsActive(1);
        if (customerService.updateCus(customer)) {
            return "Successs";
        }
        throw new Exception("Something Wrong!!");
    }
    //check login

    public String checkCustomer() {
        if (FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("customer") != null) {
            return "payment?faces-redirect=true";
        }

        return null;
    }
    //Customer 

    public String login() {
        CustomersEntity cus = getCustomerService().check(getCustomerId());
        if (cus != null) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("customer", cus);
            return "payment?faces-redirect=true";
        }
        return "customer-info";
    }

    //xóa customer khỏi session
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

}
