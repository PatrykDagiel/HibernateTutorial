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

            // query students last name = "Doe"
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            displayStudents(theStudents);
            //commit transaction
            session.getTransaction().commit();
        } finally {
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }


}
