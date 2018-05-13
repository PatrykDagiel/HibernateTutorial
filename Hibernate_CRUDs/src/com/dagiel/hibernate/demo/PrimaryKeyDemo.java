package com.dagiel.hibernate.demo;

import com.dagiel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            System.out.println("Creating new 3 student objects");
            // create a Student object
            Student student1 = new Student("Maciek", "Kowalski", "olaboga@gmail.com");
            Student student2 = new Student("Kowalski", "Marko", "Abc@xx.pl");
            Student student3 = new Student("Nowak", "John", "xyz@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            session.save(student1);
            session.save(student2);
            session.save(student3);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
    }

}
