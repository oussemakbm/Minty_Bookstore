/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ali
 */
public class CommandList {
    
    private int id;
    private String status;
    private float totalprice;
    private int idUser;

    private ArrayList<CommandLine> listOfLines = new ArrayList<CommandLine>();

    public CommandList() {
    }

    public CommandList(int id, String status, float totalprice, int idUser) {
        this.id = id;
        this.status = status;
        this.totalprice = totalprice;
        this.idUser= idUser;
    }

    public CommandList(String status, float totalprice, int idUser) {
        this.status = status;
        this.totalprice = totalprice;
        this.idUser= idUser;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public int getIdUser() {
        return idUser;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public ArrayList<CommandLine> getCls() {
        return  listOfLines;
    }

    public void setCls(ArrayList<CommandLine> cls) {
        this.listOfLines = cls;
    }
    
    public void addLines(CommandLine x) {
        listOfLines.add(x);
    }
    
    public void deleteCls(CommandLine x) {
        listOfLines.remove(x);
    }
    
     public String toString() {
        return "CommandList{" + "id=" + id + ", status=" + status + ", totalprice=" + totalprice + ", idUser="+idUser+ "}";
    }
}
