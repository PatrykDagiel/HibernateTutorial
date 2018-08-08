package com.hibernate_one_to_many_uni.demo;

import com.hibernate_one_to_many_uni.entity.Course;
import com.hibernate_one_to_many_uni.entity.Instructor;
import com.hibernate_one_to_many_uni.entity.InstructorDetail;
import com.hibernate_one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyDemo2 {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            // get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);
            // print the course
            System.out.println("This is our course: " + tempCourse);
            // print reviews
            System.out.println("Those are our reviews for this course: " + tempCourse.getReviews());

            // create a course
            Course tempCourse2 = new Course("Mario Bros");
            //add some reviews
            tempCourse2.addReview(new Review("Test I"));
            tempCourse2.addReview(new Review("Test II"));
            tempCourse2.addReview(new Review("Test III"));

            session.delete(tempCourse);

            session.getTransaction().commit();

            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
}}
