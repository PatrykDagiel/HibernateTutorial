package com.hibernate_one_to_many_uni.demo;

import com.hibernate_one_to_many_uni.entity.Course;
import com.hibernate_one_to_many_uni.entity.Instructor;
import com.hibernate_one_to_many_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyDisplay {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            int theId = 1;
            Instructor theInstructor = session.get(Instructor.class, theId);

            System.out.println("Instructor:  " + theInstructor);

            // get courses
            System.out.println("Courses: " + theInstructor.getCourses());

            // course Removal
            int theId2 = 2;
            Course course = session.get(Course.class, theId2);

            session.delete(course);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
}}
