package com.example.submailer;


import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    Connection conn = null;

    public static Connection ConnectDB() {

        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/submailer","root","");
            return conn;
        }catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;

        }
    }


}
