package com.ledinhnam.Service;

import com.ledinhnam.Entity.TicketEntity;
import com.ledinhnam.Entity.BookEntity;
import com.ledinhnam.Entity.BorrowDetailsEntity;
import com.ledinhnam.Entity.BorrowBookEntity;
import com.ledinhnam.Entity.CategoryEntity;
import com.ledinhnam.Entity.CustomersEntity;
import com.ledinhnam.Entity.EmployeesEntity;
import com.ledinhnam.Entity.InventoryEntity;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private final static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/quanlythuvien");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "1234");
        props.put(Environment.SHOW_SQL, true);

        conf.setProperties(props);
        conf.addAnnotatedClass(BookEntity.class);
        conf.addAnnotatedClass(BorrowBookEntity.class);
        conf.addAnnotatedClass(BorrowDetailsEntity.class);
        conf.addAnnotatedClass(CustomersEntity.class);
        conf.addAnnotatedClass(CategoryEntity.class);
        conf.addAnnotatedClass(InventoryEntity.class);
        conf.addAnnotatedClass(EmployeesEntity.class);
        conf.addAnnotatedClass(TicketEntity.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
