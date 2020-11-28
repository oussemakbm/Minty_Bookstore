/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Comment;
import com.models.FavoriteSerie;
import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DellXPS
 */
public class ServiceFavoriteSerie {

    public ServiceFavoriteSerie INSTANCE;
    private Connection con;

    private ServiceFavoriteSerie() {
        con = MyConnection.getInstance().getConnection();
    }

    public ServiceFavoriteSerie getInstance() {
        if (INSTANCE == null) {
            this.INSTANCE = new ServiceFavoriteSerie();
        }

        return INSTANCE;
    }

    public void addFavoriteSerie(int idUser, int idSerie) {
        String insert = "INSERT INTO `favoriteseries`(`idUser`, `idSerie`) VALUES (?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(insert);
            pst.setInt(1, idUser);
            pst.setInt(2, idSerie);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFavoriteSerie(int idFavoriteSerie) {

        // Delete Comment     
        String delete = "DELETE FROM `favoriteseries` WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setInt(1, idFavoriteSerie);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FavoriteSerie> getFavoriteSeries() {
        ArrayList<FavoriteSerie> result = new ArrayList<FavoriteSerie>();
        String fetch = "SELECT * FROM `favoriteseries`";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(fetch);
            while (rs.next()) {
                FavoriteSerie f = new FavoriteSerie();
                f.setId(rs.getInt("id"));
                result.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
