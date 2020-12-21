/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.CustomersEntity;
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
public class CustomerService {
    
     private final static SessionFactory factory = HibernateUtil.getFACTORY();
     
       public List<CustomersEntity> getCustomers() {
        try ( Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CustomersEntity> query = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> proot = query.from(CustomersEntity.class);
            query.select(proot);
            return session.createQuery(query).getResultList();
        }
    }
     // lấy Customer theo Id  
       public CustomersEntity getCustomerById(int cusId){
           try(Session sesstion  =factory.openSession()){
               return sesstion.get(CustomersEntity.class, cusId);
           }
       }
       
      //Xoa nhan doc gia
       
    public boolean deleteCustomer(CustomersEntity custormer) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(custormer);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;

    }
    // them doc gia
      public boolean addOrSaveCustomer(CustomersEntity c) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(c);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }

            return true;
        }

    }
}
