/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author ASUS
 */
public class AuthorPrefer {
private int id ;     
private int idUser;
private int idAuthor;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public AuthorPrefer(int id, int idUser, int idAuthor) {
        this.id = id;
        this.idUser = idUser;
        this.idAuthor = idAuthor;
    }

    public AuthorPrefer(int idUser, int idAuthor) {
        this.idUser = idUser;
        this.idAuthor = idAuthor;
    }

    
public AuthorPrefer(){};
public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AuthorPrefer{" + "id=" + id + ", idUser=" + idUser + ", idAuthor=" + idAuthor + '}';
    }

    
}
