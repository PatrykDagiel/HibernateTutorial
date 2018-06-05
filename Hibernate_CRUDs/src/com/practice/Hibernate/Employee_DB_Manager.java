package com.practice.Hibernate;

import com.practice.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Employee_DB_Manager {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

        }

        finally {
            factory.close();
        }
    }
}
