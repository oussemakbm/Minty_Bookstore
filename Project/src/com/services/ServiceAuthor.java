/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.models.Author;
import com.util.MyConnection;


/**
 *
 * @author ASUS
 */
public class ServiceAuthor {

    private Connection cnx;
    
    private static ServiceAuthor INSTANCE;
    
    
    public static ServiceAuthor getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ServiceAuthor();
        return INSTANCE;
    }
    

    private ServiceAuthor() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
   

    public void addAuthor(Author a) throws SQLException {
        String request = "INSERT INTO `authors`(`id`, `name`, `description`, `picUrl`)"
                + " VALUES (NULL, '" +  a.getName()+"', '"+ a.getDescription() +"', '"+a.getPicUrl()+"')";

        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<Author> getAuthors() throws SQLException {
        ArrayList<Author> results = new ArrayList<>();
        String request = "SELECT * FROM `authors`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Author a = new Author();
            a.setId(rst.getInt(1));
            a.setName(rst.getString(2));
            a.setDescription(rst.getString(3));
            a.setPicUrl(rst.getString(4));
            
            results.add(a);
        }

        return results;
    }
    public ArrayList<String> getNameAuthors() throws SQLException {
        ArrayList<Author> results = new ArrayList<>();
        String request = "SELECT name FROM `authors`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next()) {
            Author a = new Author();
            a.setName(rst.getString(1));
            results.add(a);
            
        }
        
        return null;
    }
    

    public Author getAuthor(int id) throws SQLException {
        String request = "SELECT * FROM `authors` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Author a = new Author();
            a.setId(rst.getInt(1));
            a.setName(rst.getString(2));
            a.setDescription(rst.getString(3));
            a.setPicUrl(rst.getString(4));
           
            return a;
        }

        return null;
    }

    public void updateAuthor(Author a) throws SQLException {
        
        String request="UPDATE `authors` SET `name`=?,`description`=?,`picUrl`=?"
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setString(1, a.getName());
        pst.setString(2, a.getDescription());
        pst.setString(3, a.getPicUrl());
        pst.setInt(4, a.getId());
        pst.executeUpdate();

    }

    public void deleteAuthor(int id) throws SQLException {
        String request = "DELETE FROM `authors` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

}