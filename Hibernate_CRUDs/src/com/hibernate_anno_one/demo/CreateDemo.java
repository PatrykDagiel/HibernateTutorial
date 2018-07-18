package com.hibernate_anno_one.demo;

import com.dagiel.entity.Student;
import com.hibernate_anno_one.entity.Instructor;
import com.hibernate_anno_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            System.out.println("Creating new instructor and inst_details object");
            // create and associate objects
            Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@lub2code.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "luv 2 code");

            //associate objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save instructor


            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }

    }


}
