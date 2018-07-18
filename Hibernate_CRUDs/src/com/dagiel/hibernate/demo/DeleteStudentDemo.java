package com.dagiel.hibernate.demo;

import com.dagiel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            int studentId = 1;
            session.beginTransaction();

            System.out.println("Getting student with id " + studentId);
            Student myStudent = session.get(Student.class, studentId);

//            //delete the student
//            System.out.println("Deleting student " + myStudent);
//            session.delete(myStudent);

            // the other option
            session.createQuery("delete from Student where id=2").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();
            System.out.printf("Done");

        } finally {
            factory.close();
        }

    }


}
