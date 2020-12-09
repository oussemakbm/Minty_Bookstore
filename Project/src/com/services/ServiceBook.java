/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Book;
import com.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MediaStudio
 */
public class ServiceBook {

    private Connection cnx;

    private static ServiceBook INSTANCE;

    public static ServiceBook getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceBook();
        }
        return INSTANCE;
    }

    private ServiceBook() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addBook(Book b) throws SQLException {

        String request = "INSERT INTO `books` (`id`, `title`, `description`, `imageUrl`, `prix`, `quantity`, `rating`, `nbrPages`, `idAuthor`, `idCategory`, `idLanguage`, `idSerie`, `publishDate`) "
                + " VALUES (NULL, '" + b.getTitle() + "', '" + b.getDescription() + "', '" + b.getImageUrl() + "', '" + b.getPrix() + "', '" + b.getQuantity() + "', '" + b.getRating() + "', '" + b.getNbrPages() + "', '" + b.getIdAuthor() + "', '" + b.getIdCategory() + "', '" + b.getIdLanguage() + "', '" + b.getIdSerie() + "', '" + b.getPublishDate() + "')";

        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public void updateBook(Book b) throws SQLException {

        String req = "UPDATE `books` SET `title`=?,`description`=?,`imageUrl`=?,"
                + "`prix`=?,`quantity`=?,`rating`=?,`nbrPages`=?,"
                + "`idAuthor`=?,`idCategory`=?,`idLanguage`=?,`idSerie`=?,"
                + "`publishDate`=?"
                + "WHERE `id` = ?";

        PreparedStatement pst = cnx.prepareStatement(req);

        pst.setString(1, b.getTitle());
        pst.setString(2, b.getDescription());
        pst.setString(3, b.getImageUrl());
        pst.setFloat(4, b.getPrix());
        pst.setInt(5, b.getQuantity());
        pst.setInt(6, b.getRating());
        pst.setInt(7, b.getNbrPages());
        pst.setInt(8, b.getIdAuthor());
        pst.setInt(9, b.getIdCategory());
        pst.setInt(10, b.getIdLanguage());
        pst.setInt(11, b.getIdSerie());
        pst.setString(12, b.getPublishDate());
        pst.setInt(13, b.getId());
        pst.executeUpdate();

    }

    public void deleteBook(int id) throws SQLException {
        String request = "DELETE FROM `books` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> results = new ArrayList<>();
        String request = "SELECT * FROM `books`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Book b = new Book();
            b.setId(rst.getInt(1));
            b.setTitle(rst.getString(2));
            b.setDescription(rst.getString(3));
            b.setImageUrl(rst.getString(4));
            b.setPrix(rst.getFloat(5));
            b.setQuantity(rst.getInt(6));
            b.setRating(rst.getInt(7));
            b.setNbrPages(rst.getInt(8));
            b.setIdAuthor(rst.getInt(9));
            b.setIdCategory(rst.getInt(10));
            b.setIdLanguage(rst.getInt(11));
            b.setIdSerie(rst.getInt(12));
            b.setPublishDate(rst.getString(13));

            results.add(b);
            System.out.println(b);
        }

        return results;
    }

    public Book getBook(int id) throws SQLException {
        String request = "SELECT * FROM `books` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            Book b = new Book();
            b.setId(rst.getInt(1));
            b.setTitle(rst.getString(2));
            b.setDescription(rst.getString(3));
            b.setImageUrl(rst.getString(4));
            b.setPrix(rst.getFloat(5));
            b.setQuantity(rst.getInt(6));
            b.setRating(rst.getInt(7));
            b.setNbrPages(rst.getInt(8));
            b.setIdAuthor(rst.getInt(9));
            b.setIdCategory(rst.getInt(10));
            b.setIdLanguage(rst.getInt(11));
            b.setIdSerie(rst.getInt(12));
//            b.setPublishDate(rst.getString(13));
            return b;
        }

        return null;
    }

}
