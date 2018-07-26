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
            Instructor tempInstructor_2 = new Instructor("Jan", "Kowalski", "XYZ");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "luv 2 code");
            InstructorDetail tempInstructorDetail_2 = new InstructorDetail("http://www.dummy.com", "Test");

            //associate objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            tempInstructor_2.setInstructorDetail(tempInstructorDetail_2);

            // start a transaction
            session.beginTransaction();

            // Get instructor by primary key / id
            int theId = 4;
            Instructor delInstructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor: " + delInstructor);

            // delete the instructor
            if(delInstructor != null) {
                session.delete(delInstructor);
                System.out.printf("Removal done");
            }

            // save instructor
            // This will also save details object because of CascadeType.ALL
//            System.out.println("Saving instructor: " + tempInstructor);
//            session.save(tempInstructor);
//            session.save(tempInstructor_2);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }

    }


}
