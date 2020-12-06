package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
    private String username = "root";
    private String password = "";

    private Connection connection;

    static MyConnection instance;

    private MyConnection() {
        try {
            getProperties();
            connection = DriverManager.getConnection(url, username, password);
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
    
    public void getProperties(){
        final Properties prop = new Properties();
	InputStream input = null;

	try {

            input = new FileInputStream("resources/db.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.url = prop.getProperty("db.url");
            this.username = prop.getProperty("db.username");
            this.password = prop.getProperty("db.password");
	
        } catch (final IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
                    try {
			input.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
            }
	}
    }

}  
