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
import com.util.MyConnection;

/**
 *
 * @author Lenovo
 */
public class ServiceLangue {

    private Connection cnx;

    public static ServiceLangue INSTANCE;

    public static ServiceLangue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLangue();
        }
        return INSTANCE;
    }

    private ServiceLangue() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addLangue(Langue l) throws SQLException {
        String request = "INSERT INTO `languages` (`name`)"
                + " VALUES ('" + l.getName() + "')";

        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<Langue> getLangues() throws SQLException {
        ArrayList<Langue> results = new ArrayList<>();
        String request = "SELECT * FROM `languages`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Langue p = new Langue();
            p.setId(rst.getInt("id"));
            p.setName(rst.getString(2));
            results.add(p);
        }

        return results;
    }

    public Langue getLangue(int id) throws SQLException {
        String request = "SELECT * FROM `languages` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Langue p = new Langue();
            p.setId(rst.getInt("id"));
            p.setName(rst.getString(2));
            return p;
        }

        return null;
    }

    public void updateLangue(Langue p) throws SQLException {
        String request = "UPDATE `languages` SET `name`=?"
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setString(1, p.getName());
        pst.setInt(2, p.getId());
        pst.executeUpdate();

    }

    public void deleteLangue(int id) throws SQLException {
        String request = "DELETE FROM `languages` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

}
