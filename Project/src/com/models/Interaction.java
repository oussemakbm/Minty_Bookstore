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
public class Interaction {
    private int id,idUser,idBook;
    private float ratingValue;
    private int liked;

    
    
    public Interaction() {
    }

    public Interaction(int idUser, int idBook, float ratingValue, int liked) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.ratingValue = ratingValue;
        this.liked = liked;
    }

    
    
    public Interaction(int id, int idUser, int idBook, float ratingValue, int liked) {
        this.id = id;
        this.idUser = idUser;
        this.idBook = idBook;
        this.ratingValue = ratingValue;
        this.liked = liked;
    }

   
    
    public int getId() {
        return id;
    }

    public float getRatingValue() {
        return ratingValue;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdBook() {
        return idBook;
    }

    public int getLiked() {
        return liked;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public String toString() {
        return "Interaction{" + "id=" + id + ", idUser=" + idUser + ", idBook=" + idBook + ", ratingValue=" + ratingValue + ", liked=" + liked + '}';
    }
    
    
    
    
}
