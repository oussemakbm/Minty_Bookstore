/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author ihebf
 */
public class WishList {
    private int id,idUser,idBook;
    private String name;

    public WishList() {
    }

    public WishList(int idUser, int idBook, String name) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.name = name;
    }

    public WishList(int id, int idUser, int idBook, String name) {
        this.id = id;
        this.idUser = idUser;
        this.idBook = idBook;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WishList{" + "id=" + id + ", idUser=" + idUser + ", idBook=" + idBook + ", name=" + name + '}';
    }
    
    
}
