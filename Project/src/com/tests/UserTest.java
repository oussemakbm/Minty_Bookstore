/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.User;
import com.services.ServiceUser;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MediaStudio
 */
public class UserTest {
    
    public static void main(String[] args) {
         ServiceUser us = new ServiceUser();
//         CRUD ENTITY USER BY Yasser
        try {
           us.addUser(new User("yasser", "yasser.dghais@esprit.tn", "PASSWORD+58", "client", "52190201", "Nabeul", "profilePicture"));
            
           us.updateUser(new User(32,"yasser DGHAIS", "yasser.dghais@esprit.tn", "PASSWORD+58", "ADMIN", "52190201", "Nabeul", "profilePicture"));
           us.deleteUser(30);
            
           System.out.println(us.getUser(32));
           System.out.println(us.getUsers());
        } catch (Exception e) {
            System.out.println("Exception Table User");
        }
        
    }
    
}
