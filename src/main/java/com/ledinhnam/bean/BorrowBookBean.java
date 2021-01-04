/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.BookEntity;
import com.ledinhnam.Entity.BorrowDetailsEntity;
import com.ledinhnam.Entity.BorrowBookEntity;
import com.ledinhnam.Entity.CustomersEntity;

import com.ledinhnam.Entity.EmployeesEntity;

import com.ledinhnam.Service.BookService;
import com.ledinhnam.Service.BorrowBookService;
import com.ledinhnam.Service.CustomerService;
import com.ledinhnam.Service.TicketService;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hp
 */
@ManagedBean
@Named(value = "borrowBookBean")
@RequestScoped
public class BorrowBookBean {

    private static BorrowBookService borrowService = new BorrowBookService();
    private static BookService bookService = new BookService();
    private static CustomerService customerService = new CustomerService();
    private static TicketService ticketService = new TicketService();

    Date date = new Date();
    private CustomersEntity customer;
    private int id;
    /**
     * Creates a new instance of BorrowBookBean
     */
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BorrowBookBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String borrowId = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap().get("borrowId");
            if (borrowId != null && !borrowId.isEmpty()) {
                BorrowBookEntity b = borrowService.getBorrowBookById(Integer.parseInt(borrowId));
                this.id = b.getId();
                this.customer = b.getCustomer();
            }
        }
    }

    public String add() {

        Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("cart");
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        CustomersEntity cusCheck = ((CustomersEntity) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("customer"));
        CustomersEntity customerCheckQuanty = customerService.getCustomerById(cusCheck.getId());

        if (customerCheckQuanty.getIsActive() != 1) {

            if (cart != null) {

                BorrowBookEntity br = new BorrowBookEntity();

                br.setEmployee((EmployeesEntity) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap().get("employee"));
                br.setCreateDate(new Date());

                Calendar c1 = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateTring = ZDate.dateString(date);
                c1.setTime(date);
                c1.roll(Calendar.MONTH, 1);
                dateTring = sdf.format(c1.getTime());
                System.out.println(dateTring);
                Date dateApoit = ZDate.dateParse(dateTring);
                br.setAppointmentDate(dateApoit);
                //customer

                br.setCustomer(cusCheck);
                //mượn xong đánh giấu lại false là đã mượn
                cusCheck.setIsActive(1);
                customerService.updateCus(cusCheck);
                //còn bổ sung nữa
                List<BorrowDetailsEntity> details = new ArrayList<>();

                // List<Map<String, Object>> kq = new ArrayList<>();
                for (Object o : cart.values()) {
                    Map<String, Object> d = (Map<String, Object>) o;
                    BookEntity book = getBookService().getBookById(Integer
                            .parseInt(d.get("bookId").toString()));

                    BorrowDetailsEntity detail = new BorrowDetailsEntity();
                    detail.setBook(book);
                    detail.setBorrowBook(br);
                    detail.setQuantity(Integer.parseInt(d.get("count").toString()));// lấy từ session
                    details.add(detail);

                }
                if (getBorrowService().addBorrowBook(br, details) == true) {
                    if (this.id > 0) {
                        br.setCustomer(customer);

                    }
                    //removerCart
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                            .remove("cart");
                    //removerCustomer
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getSessionMap().remove("customer");
                    return "index?faces-redirect=true";
                }
            }
            return "payment";
        }
        return "error?faces-redirect=true";

    }

    //Phần trả sách
    //check 
    public String returnBook() {
        BorrowBookEntity borrowBook = borrowService.input(id);
        if (borrowBook != null) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("borrowbook", borrowBook);
            return "book-back?faces-redirect=true";
        }
        return "book-back";
    }

    //check login
    public String checkInput() {
        if (FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("borrowbook") != null) {
            return "index?faces-redirect=true";
        }

        return null;
    }
//Trả sách

    public String updateBorrowBook() {
        //nhap phieu tra
        BorrowBookEntity borrowUpdate = (BorrowBookEntity) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("borrowbook");

        if (borrowUpdate != null) {
            CustomersEntity customerUpdate = borrowUpdate.getCustomer();

            //check nguoc lại trạng thái đã trả sách
            customerUpdate.setIsActive(0);
            customerService.updateCus(customerUpdate);
            borrowUpdate.setReturnDate(date);
            long milisReturnDate = date.getTime();
            long miliesAppointDate = borrowUpdate.getAppointmentDate().getTime();
            int totalDateExpired = (int) ((milisReturnDate - miliesAppointDate) / (1000 * 60 * 60 * 24));
            //checjk tinh tien
            if (milisReturnDate > miliesAppointDate) {

                double totalMoneyFine = totalDateExpired * (5000);
                borrowUpdate.setMoneyFine(totalMoneyFine);
                System.out.println("com.ledinhnam.bean.BorrowBookBean.updateBorrowBook()");
                System.out.println("Tien phat" + totalMoneyFine);
            }
            if (borrowService.updateBrowwow(borrowUpdate) == true) {

                if (this.id > 0) {

                    //removerCustomer
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getSessionMap().remove("borrowbook");
                }
                return "index?faces-redirect=true";
            }

        }

        return null;
    }

    public List<BorrowBookEntity> getBorrrowBooks() {
        List<BorrowBookEntity> borrows = borrowService.getBorrowBooks(null);

        return borrows;
    }

    /**
     * @return the bookService
     */
    /**
     * @param aBookService the bookService to set
     */
    /**
     * @return the borrowService
     */
    public static BorrowBookService getBorrowService() {
        return borrowService;
    }

    /**
     * @param aBorrowService the borrowService to set
     */
    public static void setBorrowService(BorrowBookService aBorrowService) {
        borrowService = aBorrowService;
    }

    /**
     * @return the time
     */
    /**
     * @return the bookService
     */
    public static BookService getBookService() {
        return bookService;
    }

    /**
     * @param aBookService the bookService to set
     */
    public static void setBookService(BookService aBookService) {
        bookService = aBookService;
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
     * @return the format
     */
    public static DateFormat getFormat() {
        return format;
    }

    /**
     * @param aFormat the format to set
     */
    public static void setFormat(DateFormat aFormat) {
        format = aFormat;
    }

    /**
     * @return the dateFormat
     */
    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * @param aDateFormat the dateFormat to set
     */
    public static void setDateFormat(DateFormat aDateFormat) {
        dateFormat = aDateFormat;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

//Class ZDate

