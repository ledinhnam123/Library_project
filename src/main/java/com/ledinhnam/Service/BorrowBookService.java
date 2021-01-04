/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.BorrowDetailsEntity;
import com.ledinhnam.Entity.BorrowBookEntity;

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
public class BorrowBookService {

    private final static SessionFactory factory = HibernateUtil.getFACTORY();

    public boolean addBorrowBook(BorrowBookEntity borrow, List<BorrowDetailsEntity> details) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.save(borrow);
                for (BorrowDetailsEntity detail : details) {
                    session.save(detail);
                }
                session.getTransaction().commit();
                return true;
            } catch (Exception ex) {
                System.err.println("Error in service!");
                session.getTransaction().rollback();

            }
            return false;
        }
    }

    public BorrowBookEntity getBorrowBookById(int borrowId) {
        try ( Session session = factory.openSession()) {
            return session.get(BorrowBookEntity.class, borrowId);
        }

    }

    //nạp customer ID ,Borrwow Id lên session
    public BorrowBookEntity input(int id) {

        try ( Session session = factory.openSession()) {

            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<BorrowBookEntity> q = b.createQuery(BorrowBookEntity.class);
            Root<BorrowBookEntity> root = q.from(BorrowBookEntity.class);
            q.select(root);
            q.where(b.equal(root.get("id"), id));
            return session.createQuery(q).getSingleResult();
        }

    }

    public boolean updateBrowwow(BorrowBookEntity borrowBook) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.update(borrowBook);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }
            return true;
        }
    }
//getLisst BorrowBook

   public List<BorrowBookEntity> getBorrowBooks(String kw) {
        try ( Session session = factory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<BorrowBookEntity> query = builder.createQuery(BorrowBookEntity.class);
            Root<BorrowBookEntity> proot = query.from(BorrowBookEntity.class);
            query.select(proot);
            return session.createQuery(query).getResultList();
        }
    }

}
