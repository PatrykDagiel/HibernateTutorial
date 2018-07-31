package com.hibernate_anno_one.demo;

import com.hibernate_anno_one.entity.Instructor;
import com.hibernate_anno_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BiDirDelete {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            int theId = 2;
            InstructorDetail temInstructorDetail = session.get(InstructorDetail.class, theId);

            //print theInstructorDetail
            System.out.printf("InstructorDetail is: " + temInstructorDetail);

            //print associated instructor
            System.out.println("Associated instructor is: " + temInstructorDetail.getInstructor());

            // delete Instructor Detail with passed ID
            System.out.printf("Deleting temInstructorDetail " + temInstructorDetail);
            session.delete(temInstructorDetail);

            session.getTransaction().commit();

        } catch(Exception exc) {
            exc.printStackTrace();
        } finally {
            }
            session.close();
            factory.close();
        }
}
