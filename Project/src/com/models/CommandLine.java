/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author ali
 */
public class CommandLine {
    private int id;
    private int quantity;
    private int idCommandList;
    private int idBook;
    
    public CommandLine(){}

    public CommandLine(int id, int quantity, int idCommandList, int idBook) {
        this.id = id;
        this.quantity = quantity;
        this.idCommandList = idCommandList;
        this.idBook = idBook;
    }

    public CommandLine(int quantity, int idCommandList, int idBook) {
        this.quantity = quantity;
        this.idCommandList = idCommandList;
        this.idBook = idBook;
    }   
    

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getIdCommandList() {
        return idCommandList;
    }

    public int getIdBook() {
        return idBook;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIdCommandList(int idCommandList) {
        this.idCommandList = idCommandList;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
    
    
   public String toString() {
        return "CommandLine{" + "id=" + id + ", quantity=" + quantity + ", idCommandList=" +idCommandList+ ", idBook=" + idBook +'}';
    }

    
}
