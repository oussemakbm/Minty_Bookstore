/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.models.Author;
import com.models.Langue;
import com.models.Serie;
import com.util.MyConnection;
/**
 *
 * @author Lenovo
 */
public class ServiceSerie {
    private Connection cnx;

    public ServiceSerie() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void addSerie(Serie l) throws SQLException {
        String request = "INSERT INTO `series` (`name`)"
                + " VALUES ('" + l.getName() + "')";

        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<Serie> getSeries() throws SQLException {
        ArrayList<Serie> results = new ArrayList<>();
        String request = "SELECT * FROM `series`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Serie s = new Serie();
            s.setId(rst.getInt("id"));
            s.setName(rst.getString(2));
            results.add(s);
        }

        return results;
    }

    public Serie getSerie(int id) throws SQLException {
        String request = "SELECT * FROM `series` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Serie s = new Serie();
            s.setId(rst.getInt("id"));
            s.setName(rst.getString(2));
            return s;
        }

        return null;
    }

    public void updateSerie(Serie p) throws SQLException {
        String request = "UPDATE `series` SET `name`=?"
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setString(1, p.getName());
        pst.setInt(2, p.getId());
        pst.executeUpdate();

    }

    public void deleteSerie(int id) throws SQLException {
        String request = "DELETE FROM `series` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
}
