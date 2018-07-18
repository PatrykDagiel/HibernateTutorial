package com.practice.Hibernate;

import com.practice.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Employee_DB_Manager {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //--- Create ---//
            System.out.println("Employee object creation");
            Employee employee = new Employee("Maciek", "Kowalski", "Nokia");
            Employee employee2 = new Employee("Piotr", "Nowak", "3M");
            Employee employee3 = new Employee("Maciek", "Kowalak", "CS");
            session.beginTransaction();
            session.save(employee);
            session.save(employee2);
            session.save(employee3);
            session.getTransaction().commit();

            // ---- READ ---- //
            System.out.println("Getting employee with id: " + employee.getId());
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee readEmployee = session.get(Employee.class, employee.getId());
            System.out.println("Employee got in read operation: " + employee);
            session.getTransaction().commit();

            //---- Update ----//
            System.out.println("Updating first employee");
            int id_tbu = 1;
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp1_upd = session.get(Employee.class, id_tbu);
            emp1_upd.setCompany("Nowa firma");
            session.createQuery("update Employee set first_name='Nowe_imie'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Finish of update section");
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Employee> theEmployees = session.createQuery("from Employee").getResultList();
            queryStudents(theEmployees);
            session.getTransaction().commit();

            //--- Delete ---//
            System.out.println("Removal: employee with id 2");
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(Employee.class, 2));
            session.getTransaction().commit();
            queryStudents(theEmployees);
            System.out.println("Session done");
        } finally {
            factory.close();
        }
    }

    private static void queryStudents(List<Employee> list) {
        int counter = 1;
        for (Employee x : list) {
            System.out.println("Oto pracownik numer " + counter++ + " " + x);
        }
    }
}
