package com.many_to_many.demo;

import com.many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MoreCourses {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            // starttransaction
            session.beginTransaction();

            // get the Student from database
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("Our student is " + tempStudent);
            System.out.println("Courses at the beginning: " + tempStudent.getCourses());

            // create more courses
            Course course1 = new Course("Rubiks Cube - How to Speed Cube");
            Course course2 = new Course("Atari 2600 - GD");

            // add student to courses
            course1.addStudent(tempStudent);
            course2.addStudent(tempStudent);

            // save courses
            System.out.printf("Saving courses\n");
            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done correctly");

        } finally {
            session.close();
        }
}}
