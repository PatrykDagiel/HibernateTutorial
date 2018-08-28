package com.many_to_many.demo;

import com.many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseFromT {
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
            int courseId = 10;
            Course tempCourse = session.get(Course.class, courseId);

            // delete pacman course
            System.out.println("Deleting course " + tempCourse);

            session.delete(tempCourse);

            session.getTransaction().commit();
            System.out.println("Done correctly");

        } finally {
            session.close();
        }
}}
