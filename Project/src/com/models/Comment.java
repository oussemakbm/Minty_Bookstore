/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author DellXPS
 */
public class Comment {
    private int id;
    private int userId;
    private int bookId;

    public int getUserId() {
        return userId;
    }

    public Comment(int userId, int bookId, String body) {
        this.userId = userId;
        this.bookId = bookId;
        this.body = body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    private String body;

    public Comment(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", body=" + body + '}';
    }
    
    
    
}
