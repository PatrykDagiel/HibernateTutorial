package com.hibernate_one_to_many_uni.demo;

import com.hibernate_one_to_many_uni.entity.Course;
import com.hibernate_one_to_many_uni.entity.Instructor;
import com.hibernate_one_to_many_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyDemo {
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
//            // use the session object to save Java object
//            System.out.println("Creating new instructor and inst_details object");
//            // create and associate objects
//            Instructor tempInstructor_2 = new Instructor("Jan", "Kowalski", "XYZ");
//            InstructorDetail tempInstructorDetail_2 = new InstructorDetail("http://www.dummy.com", "Test");
//            //associate objects
//            tempInstructor_2.setInstructorDetail(tempInstructorDetail_2);

            // start a transaction
            session.beginTransaction();

            // get Instructor from DB
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // create some courses
            Course tempCourse1 = new Course("Air guitar");
            Course tempCourse2 = new Course("The pinball Masterclass");

            // add courses to Instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);


            // save the couses
            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
}}
