/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.models.User;

/**
 *
 * @author MediaStudio
 */
public class ServiceUser {

    private Connection cnx;

    private static ServiceUser INSTANCE;

    private static User connectedUser;

    public static User getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(User u) {
        connectedUser = u;
    }
    
    private static User userToUpdate;

    public static User getuserToUpdate() {
        return userToUpdate;
    }

    public static void setuserToUpdate(User u) {
        userToUpdate = u;
    }

    public static ServiceUser getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceUser();
        }
        return INSTANCE;
    }

    private ServiceUser() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addUser(User u) throws SQLException {
        String request = "INSERT INTO `users`(`name`, `email`, `password`, `numTel`, `adresse`, `role`, `picUrl`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(request);
        pst.setString(1, u.getName());
        pst.setString(2, u.getEmail());
        pst.setString(3, u.getPassword());
        pst.setString(4, u.getNumTel());
        pst.setString(5, u.getAdresse());
        pst.setString(6, u.getRole());
        pst.setString(7, u.getProfilePicture());
        pst.executeUpdate();
    }

    public void findUserByEmail(String email, String password) throws SQLException {
//        Setting Connected user to null Initially
        setConnectedUser(null);
        
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, password);
//         Fetch Result
        ResultSet result = pst.executeQuery();

        int count = 0;
  
            while (result.next()) {
                System.out.println("Logged in");
                int id = result.getInt("id");
                String name = result.getString("name");
                String numTel = result.getString("numTel");
                String adresse = result.getString("adresse");
                String profilePicture = result.getString("picUrl");
                String role = result.getString("role");

                setConnectedUser(new User(id, name, email, password, role, numTel, adresse, profilePicture));
            }

    }

    public void updateUser(User u) throws SQLException {

        String req = "UPDATE `users` SET `name`=?,`email`=?,`password`=?,`numTel`=?,`adresse`=?,`role`=?,`picUrl`=?"
                + "WHERE `id` = ?";

        PreparedStatement pst = cnx.prepareStatement(req);

        pst.setString(1, u.getName());
        pst.setString(2, u.getEmail());
        pst.setString(3, u.getPassword());
        pst.setString(4, u.getNumTel());
        pst.setString(5, u.getAdresse());
        pst.setString(6, u.getRole());
        pst.setString(7, u.getProfilePicture());
        pst.setInt(8, u.getId());
        pst.executeUpdate();

    }

    public void deleteUser(int id) throws SQLException {
        String request = "DELETE FROM `users` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> results = new ArrayList<>();
        String request = "SELECT * FROM `users`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            User u = new User();
            u.setId(rst.getInt(1));
            u.setName(rst.getString(2));
            u.setEmail(rst.getString(3));
            u.setPassword(rst.getString(4));
            u.setRole(rst.getString(5));
            u.setNumTel(rst.getString(6));
            u.setAdresse(rst.getString(7));
            u.setProfilePicture(rst.getString(8));

            results.add(u);
        }

        return results;
    }

    public User getUser(int id) throws SQLException {
        String request = "SELECT * FROM `users` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            User u = new User();
            u.setId(rst.getInt(1));
            u.setName(rst.getString(2));
            u.setEmail(rst.getString(3));
            u.setPassword(rst.getString(4));
            u.setRole(rst.getString(5));
            u.setNumTel(rst.getString(6));
            u.setAdresse(rst.getString(7));
            u.setProfilePicture(rst.getString(8));
            return u;
        }

        return null;
    }

}
