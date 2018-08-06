package com.EagerVsLazy.demo;

import com.EagerVsLazy.entity.Course;
import com.EagerVsLazy.entity.Instructor;
import com.EagerVsLazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
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

            System.out.println("luv2code: Instructor:  " + theInstructor);
            System.out.println("Pause");
            System.out.println("luv2code: Courses: " + theInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
            session.close();

            // get courses - should fail
            System.out.println("luv2code: Courses: " + theInstructor.getCourses());
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
}}
