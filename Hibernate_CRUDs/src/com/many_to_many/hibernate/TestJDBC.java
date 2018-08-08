package com.many_to_many.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String user = "hbstudent";
        String pass = "hbstudent";
        // Test
        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection has been successful");       // Test
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
