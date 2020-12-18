/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

/**
 *
 * @author Hp
 */
import com.ledinhnam.Entity.BookEntity;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookService {

    private final static SessionFactory factory = HibernateUtil.getFACTORY();

    public List<BookEntity> getAllBook() {
        try ( Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<BookEntity> query = builder.createQuery(BookEntity.class);
            Root<BookEntity> proot = query.from(BookEntity.class);
            query.select(proot);
            return session.createQuery(query).getResultList();
        }
    }

    public List<BookEntity> getBooks(String kw) {
        try ( Session session = factory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<BookEntity> query = builder.createQuery(BookEntity.class);
            Root<BookEntity> proot = query.from(BookEntity.class);
            query.select(proot);
            if (kw != null && !kw.isEmpty()) {
                String b = String.format("%%%s%%", kw);
                Predicate b1 = builder.like(proot.get("bookName").as(String.class), b);
                Predicate b2 = builder.like(proot.get("description").as(String.class), b);
                query = query.where(builder.or(b1, b2));
            }
            return session.createQuery(query).getResultList();
        }
    }

    public boolean addOrSaveBook(BookEntity b) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(b);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }

            return true;
        }

    }
//trang thai cua delete là persitent

    public boolean deleteBook(BookEntity book) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(book);
                session.getTransaction().commit();

            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;

    }
    // lay sach theo Id

    public BookEntity getBookById(int bookId) {
        try ( Session session = factory.openSession()) {
            return session.get(BookEntity.class, bookId);
        }

    }

}
