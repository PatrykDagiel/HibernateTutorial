package com.many_to_many.demo;

import com.many_to_many.entity.Course;
import com.many_to_many.entity.Instructor;
import com.many_to_many.entity.InstructorDetail;
import com.many_to_many.entity.Review;
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
