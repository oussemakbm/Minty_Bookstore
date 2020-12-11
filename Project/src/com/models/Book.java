/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author MediaStudio
 */
public class Book {
    
    //			,							
    private int id,idCategory,idAuthor,idLanguage,quantity,nbrPages,rating,idSerie;
    private String title , description,imageUrl,publishDate;
    private float prix;
    
    public Book(){
        
    }

    public Book(int id, int idSerie, int idCategory, int idAuthor, int idLanguage, int quantity, int nbrPages, int rating, String title, String description, String imageUrl, String publishDate, float prix) {
        this.id = id;
        this.idCategory = idCategory;
        this.idAuthor = idAuthor;
        this.idLanguage = idLanguage;
        this.quantity = quantity;
        this.nbrPages = nbrPages;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.prix = prix;
        this.idSerie = idSerie;
    }
    
    public Book(int idSerie, int idCategory, int idAuthor, int idLanguage, int quantity, int nbrPages, int rating, String title, String description, String imageUrl, String publishDate, float prix) {
        
        this.idSerie = idSerie;
        this.idCategory = idCategory;
        this.idAuthor = idAuthor;
        this.idLanguage = idLanguage;
        this.quantity = quantity;
        this.nbrPages = nbrPages;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.prix = prix;
        
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id ;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public int getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNbrPages() {
        return nbrPages;
    }

    public void setNbrPages(int nbrPages) {
        this.nbrPages = nbrPages;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", idCategory=" + idCategory + ", idAuthor=" + idAuthor + ", idLanguage=" + idLanguage + ", quantity=" + quantity + ", nbrPages=" + nbrPages + ", rating=" + rating + ", idSerie=" + idSerie + ", title=" + title + ", description=" + description + ", imageUrl=" + imageUrl + ", publishDate=" + publishDate + ", prix=" + prix + '}';
    }


    
    
    
            
}
