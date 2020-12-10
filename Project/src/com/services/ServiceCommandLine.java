/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Book;
import com.models.CommandLine;
import com.models.CommandList;
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
public class ServiceCommandLine {

    private Connection cnx;

    public static ServiceCommandLine INSTANCE;

    public static ServiceCommandLine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceCommandLine();
        }
        return INSTANCE;
    }

    private ServiceCommandLine() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addCommandLine(CommandLine lng) throws SQLException {
        String request = "INSERT INTO `commandline` (`id`, `quantity`, `idCommandList`, `idBook`)"
                + " VALUES (NULL, " + lng.getQuantity()+"," + lng.getIdCommandList()+","+ lng.getIdBook() + ")";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<CommandLine> getCommandLine() throws SQLException {
        ArrayList<CommandLine> results = new ArrayList<>();
        String request = "SELECT * FROM `commandline`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            CommandLine lng = new CommandLine();
            lng.setId(rst.getInt("id"));
            lng.setQuantity(rst.getInt(2));
            lng.setIdCommandList(rst.getInt(3));
            lng.setIdBook(rst.getInt(4));
            results.add(lng);
        }

        return results;
    }

    public void updateCommandLine(CommandLine lng) throws SQLException {
        String request = "UPDATE `commandlie` SET `quantity`=? 'idCommandLst'=? 'idBook'=?"
                + "WHERE `id` = ?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setInt(1, lng.getQuantity());
        pst.setInt(2, lng.getIdCommandList());
        pst.setInt(3, lng.getIdBook());
        pst.setInt(4, lng.getId());
        pst.executeUpdate();

    }

    public void deleteCommandLine(int id) throws SQLException {
        String request = "DELETE FROM `commandline` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
    public ArrayList<Book> getBookList(int id) throws SQLException {
        ArrayList<Book> results = new ArrayList<>();
        
        String request = "SELECT * FROM commandline c, books b WHERE idCommandList=" +  id+ " &&  b.id = c.idBook";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        while (rst.next()) {
            Book b = new Book();
            b.setId(rst.getInt("id"));
            b.setIdSerie(rst.getInt("idSerie"));
            b.setIdCategory(rst.getInt("idCategory"));
            b.setIdAuthor(rst.getInt("idAuthor"));
            b.setIdLanguage(rst.getInt("idLanguage"));
            b.setQuantity(rst.getInt("quantity"));
            b.setRating(rst.getInt("rating"));
            b.setTitle(rst.getString("title"));
            b.setDescription(rst.getString("description"));
            b.setImageUrl(rst.getString("imageUrl"));
            //b.setPublishDate(rst.getString("publishDate"));
            b.setPrix(rst.getFloat("prix"));
            
            results.add(b);
        }
        return results;
        
    }
    
    public float getPriceTotalBooks (int id) throws SQLException {
        String request ="SELECT c.quantity,b.prix FROM commandline c, books b WHERE c.idCommandList=" + id+ "&& b.id=c.idBook";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        float total=0;
         while (rst.next()) {
            total+=(rst.getInt(1)*rst.getFloat(2));
        }
        return total;
    
    }

}
