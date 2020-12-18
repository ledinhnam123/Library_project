/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Converter;

import com.ledinhnam.Service.CategoryService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



/**
 *
 * @author Hp
 */
@FacesConverter("CategoryConverter")
public class CategoryConverter implements  Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) { // tu o ngoai do vao menageBean
        return new CategoryService().getCategoryById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
