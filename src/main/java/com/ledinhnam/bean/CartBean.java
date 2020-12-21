/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
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
    public void init(){
        if(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("cart") == null ){
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("cart", new HashMap<>());
            
        }
    }
    //truyen thong tin luu tam de dhien thi cho thanh toan
    public String addItemToCart(int bookId, String bookName){
        Map<Integer,Object> cart = (Map<Integer,Object>) FacesContext
                                    .getCurrentInstance()
                                    .getExternalContext()
                                    .getSessionMap().get("cart");
        
        if(cart.get(bookId)==null){
            Map<String,Object> data = new HashMap<>();
            data.put("bookId", bookId);           
            data.put("bookName", bookName);
            data.put("count",1);            
            cart.put(bookId,data);
            
        }else{
           Map<String,Object> data = (Map<String,Object>) cart.get(bookId);
           data.put("count",Integer.parseInt(data.get("count").toString()+1));
        }
        return "";
        
    }
}
