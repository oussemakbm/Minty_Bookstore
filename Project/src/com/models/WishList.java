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
    private int id,idUser;
    private String name;

    public WishList() {
    }

    public WishList(int idUser,  String name) {
        this.idUser = idUser;
        this.name = name;
    }

    public WishList(int id, int idUser, int idBook, String name) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
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

   
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WishList{" + "id=" + id + ", idUser=" + idUser + ", name=" + name + '}';
    }

    
    
}
