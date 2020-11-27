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
public class User {
    private int id;

    private String name,email,password,role,numTel,adresse,profilePicture;
    
    public User(){
        
    }

    public User(int id, String name, String email, String password, String role, String numTel, String adresse, String profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.numTel = numTel;
        this.adresse = adresse;
        this.profilePicture = profilePicture;
    }
        public User(String name, String email, String password, String role, String numTel, String adresse, String profilePicture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.numTel = numTel;
        this.adresse = adresse;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + ", numTel=" + numTel + ", adresse=" + adresse + ", profilePicture=" + profilePicture + '}';
    }
    
    
    
    
     
    
    
}
