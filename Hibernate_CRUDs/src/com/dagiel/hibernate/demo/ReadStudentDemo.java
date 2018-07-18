package com.dagiel.hibernate.demo;

import com.dagiel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
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
            System.out.println("Creating new student object");
            // create a Student object
            Student student = new Student("Daffy", "Duck", "daffy@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            session.save(student);
            // commit transaction
            session.getTransaction().commit();

            // READ Students ID
            System.out.println("Saved student. Generated ID: " + student.getId());
            // new session
            session = factory.getCurrentSession();
            session.beginTransaction();
            // retrieve student based on ID
            System.out.println("\nGetting student with id: " + student.getId());
            Student myStudent = session.get(Student.class, student.getId());
            System.out.println("get complete: " + myStudent);
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done correctly");
        } finally {
            factory.close();
        }

    }


}
