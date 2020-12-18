/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.CategoryEntity;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hp
 */

public class CategoryService {

    private final static SessionFactory factory = HibernateUtil.getFACTORY();
// lay danh sach danh muc sach
    public List<CategoryEntity> getCategorys() {
        try (Session session = factory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
            Root<CategoryEntity> croot = query.from(CategoryEntity.class);
            query.select(croot);
            return session.createQuery(query).getResultList();
    }
       
    }
    // lay category theo Id
    public CategoryEntity getCategoryById(int cateId){
        try (Session session = factory.openSession()){
            return session.get(CategoryEntity.class, cateId);          
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
      
    }
}
