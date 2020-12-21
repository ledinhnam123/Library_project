/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.EmployeesEntity;
import com.ledinhnam.Service.EmployeeService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@ManagedBean
@Named(value = "employeeBean")
@RequestScoped
public class EmployeeBean {

    private int empId;
    private String name;
    private String phone;
    private String pass;
    private String identityId;
    private String email;
    @Transient
    private String confirmPasswork;
    private static EmployeeService employeeService = new EmployeeService();

    public String register() {
        if (!this.pass.isEmpty() && this.pass.equals(this.confirmPasswork)) {
            EmployeesEntity emp = new EmployeesEntity();
            emp.setName(name);
            emp.setEmail(email);
            emp.setPhone(phone);
            emp.setPass(pass);
            // tạo ra đối tượng empl và check rong

            if (employeeService.addEmployeeNew(emp) == true) {
                return "login?faces-redirect=true";
            }

        }
        return "register";

    }

    public String login() {
        EmployeesEntity emp = employeeService.login(email, pass);
        if (emp != null) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("employee", emp);
            return "index?faces-redirect=true";
        }
        return "login";
    }
    
    //check login
    public String checkLogin(){
        if(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("employee") != null)
             return "index?faces-redirect=true";
        
        return null;
    }
    //logout
    public String logout(){
         FacesContext.getCurrentInstance()
                 .getExternalContext()
                 .getSessionMap().remove("employee");
         return "login?faces-redirect=true";
    }

    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) { // vào lần đầu thì ms lấy, bấm thì không làm nữa
            String emplId = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap().get("employee_id");
            if (emplId != null && !emplId.isEmpty()) {
                EmployeesEntity em = employeeService.getEmployeeById(Integer.parseInt(emplId));
                this.empId = em.getId(); // lấy l             
                this.name = em.getName();
                this.email = em.getEmail();
                this.phone = em.getPhone();
                this.identityId = em.getIdentityId();
                this.pass = em.getPass();

            }
        }

    }

    public String addEmployee() {
        EmployeesEntity employee;
        if (this.empId > 0) {
            employee = employeeService.getEmployeeById(this.empId);//link tới
        } else {
            employee = new EmployeesEntity();//stran\sient
        }
        employee.setPass(pass);
        employee.setName(name);
        employee.setPhone(phone);
        employee.setIdentityId(identityId);
        employee.setEmail(email);

        try {
            if (true == employeeService.addOrSaveEmployee(employee)) {
                return "employee-list?faces-redirect=true"; // duong link giao dien 
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "emloyees";
    }

    public List<EmployeesEntity> getEmployees() {
        List<EmployeesEntity> employee = employeeService.getEmployees();
        return employee;
    }

    //xóa độc giả
    public String deleteEmployee(EmployeesEntity employee) throws Exception {
        if (getEmployeeService().deleteEmployee(employee)) {
            return "Successful";
        }

        throw new Exception("Something Wrong!!");
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
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

    /**
     * @return the employeeService
     */
    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    /**
     * @param aEmployeeService the employeeService to set
     */
    public static void setEmployeeService(EmployeeService aEmployeeService) {
        employeeService = aEmployeeService;
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
     * @return the confirmPasswork
     */
    public String getConfirmPasswork() {
        return confirmPasswork;
    }

    /**
     * @param confirmPasswork the confirmPasswork to set
     */
    public void setConfirmPasswork(String confirmPasswork) {
        this.confirmPasswork = confirmPasswork;
    }

    /**
     * @return the conformPasswork
     */
}
