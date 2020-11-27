/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author Lenovo
 */
public class Serie {
    private int id;
    private String name;

    public Serie() {
    }

    public Serie(int id) {
        this.id = id;
    }
    
    public Serie(String name) {
        this.name = name;
    }

    public Serie(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Serie{" + "id=" + id + ", name=" + name + '}';
    }
    
}
