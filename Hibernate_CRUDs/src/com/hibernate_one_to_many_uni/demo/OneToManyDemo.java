package com.hibernate_one_to_many_uni.demo;

import com.hibernate_one_to_many_uni.entity.Course;
import com.hibernate_one_to_many_uni.entity.Instructor;
import com.hibernate_one_to_many_uni.entity.InstructorDetail;
import com.hibernate_one_to_many_uni.entity.Review;
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
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            // create a course
            Course tempCourse = new Course("Pacman - How to Score One Million Points");
            //add some reviews
            tempCourse.addReview(new Review("Great course"));
            tempCourse.addReview(new Review("Great course ... really nice"));
            tempCourse.addReview(new Review("Great course .. 4/5"));

            System.out.println("Saving the course: " + tempCourse);
            // save and commit
            session.save(tempCourse);
            session.getTransaction().commit();

            System.out.println("Done correctly");
        } finally {
            factory.close();
        }
}}
