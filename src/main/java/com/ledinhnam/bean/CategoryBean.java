/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.CategoryEntity;
import com.ledinhnam.Service.CategoryService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Hp
 */
@ManagedBean
@Named(value = "categoryBean")
@SessionScoped
public class CategoryBean implements Serializable {
private  static CategoryService categoryService = new CategoryService();
    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
    }
    
    public List<CategoryEntity> getCategories(){
        List<CategoryEntity> cates = categoryService.getCategorys();
        return cates;
    }
   
}
