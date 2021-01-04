/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hp
 */
@ManagedBean
@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    /**
     * Creates a new instance of CartBean
     */
    public CartBean() {
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("cart") == null) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("cart", new HashMap<>());

        }
        if (FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("info") == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("info", new HashMap());
        }
    }

    public List<Map<String, Object>> getCarts() {
        Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("cart");

        List<Map<String, Object>> kq = new ArrayList<>();
        for (Object o : cart.values()) {
            Map<String, Object> d = (Map<String, Object>) o;
            kq.add(d);
        }
        return kq;

    }

    //truyen thong tin luu tam de dhien thi cho thanh toan
    public String addItemToCart(int bookId, String bookName) {
        Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("cart");

        if (cart.get(bookId) == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("bookId", bookId);
            data.put("bookName", bookName);
            data.put("count", 1);
            cart.put(bookId, data);

        } else {
            Map<String, Object> data = (Map<String, Object>) cart.get(bookId);
            data.put("count", Integer.parseInt(data.get("count").toString()) + 1);
        }
        return "";

    }

    //thêm thông tin Customer Trên session
    public String addInfoCustomer(int customerId) {
        Map<Integer, Object> info = (Map<Integer, Object>) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("info");
        if (info != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("customerId", customerId);
            info.put(customerId, data);
        }
        return "";
    }

    // lay thong tin 
    public List<Map<String, Object>> getInfo() {
        Map<Integer, Object> info = (Map<Integer, Object>) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("info");
        List<Map<String, Object>> kq = new ArrayList<>();
        for (Object o : info.values()) {
            Map<String, Object> d = (Map<String, Object>) o;
            kq.add(d);
        }
        return kq;
    }

}
