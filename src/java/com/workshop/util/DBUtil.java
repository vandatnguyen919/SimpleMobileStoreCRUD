/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author HIEU
 */
public class DBUtil {
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "1433";
    private static final String DB_NAME = "MobileStore";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "123";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        String url = "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT + ";databaseName=" + DB_NAME;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
        }
        return connection;
    }
    
    // Test connection
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       System.out.println(getConnection());
   }
}
