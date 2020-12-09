
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.CommandList;
import com.models.User;
import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ali
 */
public class ServiceCommandList {

    private Connection cnx;

    public static ServiceCommandList INSTANCE;

    public static ServiceCommandList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceCommandList();
        }
        return INSTANCE;
    }

    public ServiceCommandList() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addCommandList(CommandList lc) throws SQLException {
        String request = "INSERT INTO `commandlist` (`id`, `status`, `totalprice`, 'idUser')"
                + " VALUES (NULL, '" + lc.getStatus() + "', '" + lc.getTotalprice() + "', '" + lc.getIdUser() + "')";

        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<CommandList> getCommandList() throws SQLException {
        ArrayList<CommandList> results = new ArrayList<>();
        String request = "SELECT * FROM `commandlist`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            CommandList lc = new CommandList();
            lc.setId(rst.getInt("id"));
            lc.setStatus(rst.getString(2));
            lc.setTotalprice(rst.getFloat(3));
            lc.setIdUser(rst.getInt(3));
            results.add(lc);
        }

        return results;
    }

    public void updateCommandList(CommandList lc) throws SQLException {
        String request = "UPDATE `commandlist` SET `status`=?,`totalprice`=?, 'idUser'=? "
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setString(1, lc.getStatus());
        pst.setFloat(2, lc.getTotalprice());
        pst.setInt(3, lc.getIdUser());
        pst.setInt(4, lc.getId());
        pst.executeUpdate();

    }

    public void deleteCommandList(int id) throws SQLException {
        String request = "DELETE FROM `commandline` WHERE idCommandlist =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
         request = "DELETE FROM `commandlist` WHERE id =" + id;
        stm.executeUpdate(request);
    }
    public ArrayList<CommandList> getCommandListUser(int id) throws SQLException {
        ArrayList<CommandList> results = new ArrayList<>();
        User u = ServiceUser.getConnectedUser();
        String request ="SELECT * FROM `commandlist` WHERE idUser =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next()) {
            CommandList lc = new CommandList();
            lc.setId(rst.getInt("id"));
            lc.setStatus(rst.getString("status"));
            lc.setTotalprice(rst.getFloat("totalprice"));
            lc.setIdUser(rst.getInt("iduser"));
            results.add(lc);
        }
        return results;
    }
    
    
    

}
