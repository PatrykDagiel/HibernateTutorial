package com.many_to_many.demo;

import com.many_to_many.entity.*;
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
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            // create a course
            Course tempCourse = new Course("Pacman - How to Score One Million Points");
            System.out.printf("Saving course completed:: " + tempCourse);
            session.save(tempCourse);

            // create students
            Student tempStudent1 = new Student("John", "Kow", "mail1@mail.com");
            Student tempStudent2 = new Student("Tomek","Now", "tomek@mail.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save students
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Students added: " + tempStudent1 + "    " + tempStudent2);

            session.getTransaction().commit();
            System.out.println("Done correctly");

        } finally {
            factory.close();
        }
}}
