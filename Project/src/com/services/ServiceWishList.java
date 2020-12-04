/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Book;
import com.models.WishList;
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
public class ServiceWishList {

    private Connection cnx;

    public static ServiceWishList INSTANCE;

    public static ServiceWishList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceWishList();
        }
        return INSTANCE;
    }

    private ServiceWishList() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void addWishList(WishList w) throws SQLException {
        String request = "INSERT INTO `wishlist` (`id`, `name`, `idUser`, `idBook`)"
                + " VALUES (NULL, '" + w.getName() + "', '" + w.getIdUser() + "', '" + w.getIdBook() + "')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public ArrayList<WishList> getWishLists() throws SQLException {
        ArrayList<WishList> results = new ArrayList<>();
        String request = "SELECT * FROM `wishlist`";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            WishList w = new WishList();
            w.setId(rst.getInt(1));
            w.setName(rst.getString(2));
            w.setIdUser(rst.getInt(3));
            w.setIdBook(rst.getInt(4));
            results.add(w);
        }

        return results;
    }

    public WishList getWishList(int id) throws SQLException {
        String request = "SELECT * FROM `wishlist` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        if (rst.next()) {
            WishList w = new WishList();
            w.setId(rst.getInt(1));
            w.setName(rst.getString(2));
            w.setIdUser(rst.getInt(3));
            w.setIdBook(rst.getInt(4));
            return w;
        }

        return null;
    }

    public void updateWishList(WishList w) throws SQLException {
        String request = "UPDATE `wishlist` SET `name`=? WHERE `id`=?";
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setString(1, w.getName());
        pst.setInt(2, w.getId());

        pst.executeUpdate();

    }

    public void deleteWishList(int id) throws SQLException {
        String request = "DELETE FROM `wishlist` WHERE id =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
    
    public void addBookToWishList(WishList ws,Book book) throws SQLException{
        String request= "INSERT INTO `wishlistitem` VALUES (NULL,?,?)";
        PreparedStatement pst= cnx.prepareStatement(request);
        pst.setInt(1, ws.getId());
        pst.setInt(2, book.getId());
        pst.executeUpdate();
    }
    
    public ArrayList<Book> getBooksInWishList(WishList ws) throws SQLException{
        ArrayList<Book> books=new ArrayList<>();
        String request = "SELECT * FROM `books` b,`wishlist` w,`wishlistitem` wi WHERE w.id=wi.id_wishlist AND wi.id_book=b.id AND w.id="+ws.getId();
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);

        while (rst.next()) {
            Book b = new Book();
            b.setId(rst.getInt(1));
            b.setRating((int)rst.getFloat("rating"));
            b.setTitle(rst.getString("title"));
            b.setPrix(rst.getFloat("prix"));
            books.add(b);
        }

        return books;
    }
    
    public boolean checkBookInWishList(WishList ws,Book b) throws SQLException{
        String request = "SELECT * FROM `wishlistitem` wi WHERE wi.id_wishlist="+ws.getId()+" AND wi.id_book="+b.getId();
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(request);
        int i=0;
        while(rst.next()){
            i++;
        }
        return i>0; 
    }
    
    public void deleteBookFromWishList(WishList ws,Book b) throws SQLException{
        String request="DELETE FROM `wishlistitem` WHERE id_wishlist="+ws.getId()+" AND id_book="+b.getId();
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
}
