package com.dagiel.hibernate.demo;

import com.dagiel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            //display students
            displayStudents(theStudents);
//
//            // query students: lastName Doe OR firstName Daffy
            theStudents = session.createQuery("from Student s where" + " s.lastName='Doe' OR s.lastName='Maciek'").getResultList();
            displayStudents(theStudents);

            // email ending LIKE %gmail.com
            theStudents = session.createQuery("from Student s where"
            + " s.email LIKE '%gmail.com'").getResultList();
            displayStudents(theStudents);

            //commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }


}
