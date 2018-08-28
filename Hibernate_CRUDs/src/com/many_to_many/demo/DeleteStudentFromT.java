package com.many_to_many.demo;

import com.many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentFromT {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            // starttransaction
            session.beginTransaction();

            // get the pacman course
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            // delete pacman course
            System.out.println("Deleting student " + tempStudent);

            session.delete(tempStudent);

            session.getTransaction().commit();
            System.out.println("Done correctly");

        } finally {
            session.close();
        }
}}
