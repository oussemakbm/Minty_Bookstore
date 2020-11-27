package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MediaStudio
 */
public class MyConnection {
    private String url = "jdbc:mysql://localhost:3306/bookstore";
    private String login = "root";
    private String password = "";

    private Connection connection;

    static MyConnection instance;

    private MyConnection() {
        try {
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

}

    

