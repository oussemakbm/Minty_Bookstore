/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Comment;
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
public class ServiceComment {

    public static ServiceComment INSTANCE;
    private Connection con;

    private ServiceComment() {
        con = MyConnection.getInstance().getConnection();
    }

    public static ServiceComment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceComment();
        }

        return INSTANCE;
    }

    public void addComment(Comment c, int userId, int bookId) {
        String insert = "INSERT INTO `comments`(`idUser`, `idBook`, `body`) VALUES (?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(insert);
            pst.setInt(1, userId);
            pst.setInt(2, bookId);
            pst.setString(3, c.getBody());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteComment(int id) {
        // Delete Comment     
        String delete = "DELETE FROM `comments` WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editComment(int id, String body) {
        // Edit Comment 
        String update = "UPDATE `comments` SET `body`= ? WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(update);
            pst.setString(1, body);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comment> getComments() {

        ArrayList<Comment> result = new ArrayList<Comment>();

        String fetch = "SELECT * FROM comments";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(fetch);
            while (rs.next()) {
                Comment c = new Comment(rs.getString("body"));
                c.setId(rs.getInt("id"));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
