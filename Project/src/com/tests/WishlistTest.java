/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.WishList;
import com.services.ServiceWishList;
import java.sql.SQLException;

/**
 *
 * @author DellXPS
 */
public class WishlistTest {

    public static void main(String[] args) throws SQLException {
        ServiceWishList ws = ServiceWishList.getInstance();
        WishList w1 = new WishList(1, "Wish List 1"); // (idUser,idBook,name)
        WishList w2 = new WishList( 1, "Wish List 2");
        ws.addWishList(w1);
        ws.addWishList(w2);
        System.out.println(ws.getWishLists());
        WishList w3 = new WishList();
        w3.setId(3);
        w3.setName("Wish List updated");
        ws.updateWishList(w3);
        System.out.println(ws.getWishList(3));
        ws.deleteWishList(4);
        System.out.println(ws.getWishLists());
    }
}
