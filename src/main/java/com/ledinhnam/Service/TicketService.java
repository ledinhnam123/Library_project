/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.TicketEntity;
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
public class TicketService {

    private final static SessionFactory factory = HibernateUtil.getFACTORY();
    public TicketEntity getTick;

    public TicketEntity getTicketById(int tickId) {
        try ( Session sesstion = factory.openSession()) {
            return sesstion.get(TicketEntity.class, tickId);
        }
    }

    // lay danh sach Thẻ
    public List<TicketEntity> getTickets(String kw) {
        try ( Session session = factory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TicketEntity> query = builder.createQuery(TicketEntity.class);
            Root<TicketEntity> proot = query.from(TicketEntity.class);
            query.select(proot);

            return session.createQuery(query).getResultList();
        }
    }

    //xóa thẻ
    public boolean deleteTicket(TicketEntity ticket) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(ticket);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                return false;
            }

        }
    }

    public boolean addOrsaveTicket(TicketEntity t) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(t);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }

            return true;
        }

    }

}
