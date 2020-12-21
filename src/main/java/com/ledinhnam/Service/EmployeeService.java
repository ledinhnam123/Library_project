/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.EmployeesEntity;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hp
 */
public class EmployeeService {

    private final static SessionFactory factory = HibernateUtil.getFACTORY();

    public List<EmployeesEntity> getEmployees() {
        try ( Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<EmployeesEntity> query = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> proot = query.from(EmployeesEntity.class);
            query.select(proot);
            return session.createQuery(query).getResultList();
        }
    }
    // lấy Customer theo Id  

    public EmployeesEntity getEmployeeById(int empId) {
        try ( Session sesstion = factory.openSession()) {
            return sesstion.get(EmployeesEntity.class, empId);
        }
    }

    //Xoa nhan doc gia
    public boolean deleteEmployee(EmployeesEntity employee) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(employee);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;

    }

    // them doc gia
    public boolean addOrSaveEmployee(EmployeesEntity em) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(em);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }

            return true;
        }

    }

    //đang nhap dang dang ky co ma bam
    public boolean addEmployeeNew(EmployeesEntity emp) {
        try ( Session session = factory.openSession()) {

            try {
                session.getTransaction().begin();

                emp.setPass(DigestUtils.md5Hex(emp.getPass()));
                session.save(emp);

                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();

            }
        }
        return false;
    }
    public EmployeesEntity login(String email,String pass){
        
        try (Session session = factory.openSession()){
             pass =DigestUtils.md5Hex(pass);
             CriteriaBuilder b = session.getCriteriaBuilder();
             CriteriaQuery<EmployeesEntity> q = b.createQuery(EmployeesEntity.class);
             Root<EmployeesEntity> root = q.from(EmployeesEntity.class);
             q.select(root);          
             q.where(b.and(b.equal(root.get("email").as(String.class), email),
                     b.equal(root.get("pass").as(String.class), pass)));
             
            return session.createQuery(q).getSingleResult() ;
        }
      
    }

}
