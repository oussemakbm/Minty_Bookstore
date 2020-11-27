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
public class Category {
    private int id;
    private String name;
    
    
    public Category (){
    }
    public Category (String name){
        this.name=name;
    }
    
    public Category (int id, String name){
        this.id=id;
        this.name=name;
    }
    
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void SetId(int id){
        this.id=id;
    }
    public void SetName(String name){
        this.name=name;
    }
     public String toString() {
        return "Category{" + "id=" + id + ", name=" + name +  '}';
    }
    
    
   
    
    
}
