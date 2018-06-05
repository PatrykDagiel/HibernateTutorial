package com.dagiel.hibernate.demo;

import com.dagiel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            int studentId = 1;
            session.beginTransaction();

            System.out.println("Getting student with id " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            System.out.printf("Updating student");
            myStudent.setFirstName("Scooby");

            // New transaction
            System.out.println("\nUpdate email for all students");

            session.createQuery("update Student set email='foo@gmail.com'")
                    .executeUpdate();


            //commit the transaction
            session.getTransaction().commit();
            System.out.printf("Done");

        } finally {
            factory.close();
        }

    }



}
