/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Category;
import com.models.CommandLine;
import java.sql.SQLException;
import java.util.ArrayList;
import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ali
 */
public class ServiceCategory {

    private Connection cnx;

    public static ServiceCategory INSTANCE;

    public static ServiceCategory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceCategory();
        }
        return INSTANCE;
    }

    private ServiceCategory() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addCategory(Category c) throws SQLException {
        String request = "INSERT INTO 'categories' ('id', 'name' )"
                + " VALUES (NULL, '" + c.getName() + "')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);

    }

    public ArrayList<Category> getCategories() throws SQLException {
        ArrayList<Category> results = new ArrayList<Category>();
        String request = "SELECT * FROM `categories`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Category c = new Category();
            c.SetId(rst.getInt(1));
            c.SetName(rst.getString(2));

            results.add(c);
            
        }
        
        return results;

    }

    public void deleteCategory(int id) throws SQLException {
        String request = "DELETE FROM `categories` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);

    }

    public void updateCategory(Category c) throws SQLException {
        String request = "UPDATE `categories` SET `name`=? "
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setString(1, c.getName());
        pst.setInt(2, c.getId());
        pst.executeUpdate();

    }

}
