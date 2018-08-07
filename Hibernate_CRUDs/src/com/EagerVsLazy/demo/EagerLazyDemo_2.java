package com.EagerVsLazy.demo;

import com.EagerVsLazy.entity.Course;
import com.EagerVsLazy.entity.Instructor;
import com.EagerVsLazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EagerLazyDemo_2 {
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

            // option 2: Hibernate query with HQL
            int theId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i "
                    + "JOIN FETCH i.courses "
                    + "where i.id=:theInstructorId",
                    Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute the query
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("luv2code: Instructor:  " + tempInstructor);

            // commit transaction
            session.getTransaction().commit();
            session.close();

            // get courses - should fail
            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
}}
