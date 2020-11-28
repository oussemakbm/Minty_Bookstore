/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Author;
import com.models.AuthorPrefer;
import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ServicePreferAuthor {

    private Connection cnx;

    public static ServicePreferAuthor INSTANCE;

    public static ServicePreferAuthor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicePreferAuthor();
        }
        return INSTANCE;
    }

    private ServicePreferAuthor() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addPreferAuthor(AuthorPrefer ap) throws SQLException {
        String request = "INSERT INTO `favoriteauthors`(`id`, `idUser`, `idAuthor`) "
                + " VALUES (NULL, '" + ap.getIdUser() + "', '" + ap.getIdAuthor() + "')";

        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<AuthorPrefer> getAuthorsPrefer() throws SQLException {
        ArrayList<AuthorPrefer> results = new ArrayList<>();
        String request = "SELECT * FROM `favoriteauthors`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            AuthorPrefer ap = new AuthorPrefer();
            ap.setId(rst.getInt(1));
            ap.setIdUser(rst.getInt(2));
            ap.setIdAuthor(rst.getInt(3));

            results.add(ap);
        }

        return results;
    }

    public AuthorPrefer getAuthorPrefer(int id) throws SQLException {
        String request = "SELECT * FROM `favoriteauthors` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            AuthorPrefer ap = new AuthorPrefer();
            ap.setId(rst.getInt(1));
            ap.setIdUser(rst.getInt(2));
            ap.setIdAuthor(rst.getInt(3));

            return ap;
        }

        return null;
    }

    public void updateAuthorPrefer(AuthorPrefer ap) throws SQLException {

        String request = "UPDATE `favoriteauthors` SET `idUser`=?,`idAuthor`=? "
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setInt(1, ap.getIdUser());
        pst.setInt(2, ap.getIdAuthor());
        pst.setInt(3, ap.getId());
        pst.executeUpdate();

    }

    public void deleteAuthorPrefer(int id) throws SQLException {
        String request = "DELETE FROM `favoriteauthors` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

}
