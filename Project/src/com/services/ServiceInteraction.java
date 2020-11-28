/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Interaction;
import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ihebf
 */
public class ServiceInteraction {

    private Connection cnx;

    public static ServiceInteraction INSTANCE;

    public static ServiceInteraction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceInteraction();
        }
        return INSTANCE;
    }

    private ServiceInteraction() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addInteraction(Interaction i) throws SQLException {
        String request = "INSERT INTO `interactions` (`id`, `idUser`, `idBook`, `ratingValue`, `liked`)"
                + " VALUES (NULL, '" + i.getIdUser() + "', '" + i.getIdBook() + "', '" + i.getRatingValue() + "', '" + i.getLiked() + "')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<Interaction> getInteractions() throws SQLException {
        ArrayList<Interaction> results = new ArrayList<>();
        String request = "SELECT * FROM `interactions`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Interaction i = new Interaction();
            i.setId(rst.getInt(1));
            i.setIdUser(rst.getInt(2));
            i.setIdBook(rst.getInt(3));
            i.setRatingValue(rst.getFloat(4));
            i.setLiked(rst.getInt(5));
            results.add(i);
        }

        return results;
    }

    public Interaction getInteraction(int id) throws SQLException {
        String request = "SELECT * FROM `interactions` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Interaction i = new Interaction();
            i.setId(rst.getInt(1));
            i.setIdUser(rst.getInt(2));
            i.setIdBook(rst.getInt(3));
            i.setRatingValue(rst.getFloat(4));
            i.setLiked(rst.getInt(5));
            return i;
        }

        return null;
    }

    public void updateInteraction(Interaction i) throws SQLException {
        String request = "UPDATE `interactions` SET `ratingValue`=?,`liked`=? "
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setFloat(1, i.getRatingValue());
        pst.setInt(2, i.getLiked());
        pst.setInt(3, i.getId());
        pst.executeUpdate();

    }

    public void deleteInteraction(int id) throws SQLException {
        String request = "DELETE FROM `interactions` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
}
