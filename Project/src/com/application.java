/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.models.Author;
import com.models.AuthorPrefer;
import com.models.Book;
import com.models.Category;
import com.models.CommandLine;
import com.models.CommandList;
import com.models.Comment;
import com.models.Interaction;
import com.models.User;
import com.models.WishList;
import com.services.ServiceInteraction;
import com.services.ServiceAuthor;
import com.services.ServiceBook;
import com.services.ServiceCategory;
import com.services.ServiceCommandLine;
import com.services.ServiceCommandList;
import com.services.ServiceComment;
import com.services.ServicePreferAuthor;
import com.services.ServiceUser;
import com.services.ServiceWishList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DellXPS
 */
public class application extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("views/home.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

//  ++++++++++++++++ AUTHOR CRUD TEST ++++++++++++++++++++++
// ++++++++++++++++  Comments CRUD TEST  ++++++++++++++++++++++
        //++++++++++++++++ Test Interaction ++++++++++++++++++++++
    // (idUser,idBook,ratingValue,liked)

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++ Test WishList ++++++++++++++++++++++++++++

        //++++++++++++++++ Test Category++++++++++++++++++++++

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //++++++++++++++++ Test CommandLine ++++++++++++++++++++++++++++

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++ Test Liste de Commande ++++++++++++++++++++++

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        launch(args);
        
//        ServiceUser us = new ServiceUser();
////         CRUD ENTITY USER BY Yasser
//        try {
//            //us.addUser(new User("yasser", "yasser.dghais@esprit.tn", "PASSWORD+58", "client", "52190201", "Nabeul", "profilePicture"));
//            //idUser=8
////            us.updateUser(new User(32,"yasser DGHAIS", "yasser.dghais@esprit.tn", "PASSWORD+58", "ADMIN", "52190201", "Nabeul", "profilePicture"));
//            us.deleteUser(30);
//            
////            System.out.println(us.getUser(32));
//            System.out.println(us.getUsers());
//        } catch (Exception e) {
//            System.out.println("Exception Table User");
//        }
        //CRUD ENTITY BOOK By Yasser
//        ServiceBook sb = new ServiceBook();
//        try {
////            sb.addBook(new Book(1,1, 1, 1, 1, 1, 1, "le long chemin vers la liberté", "Mandela", "IMG", "20/1/2000", 12));
////            
////            sb.updateBook(new Book(7,1,1, 1, 1, 1, 1, 1, "le long chemin vers la liberté", "Nelson Mandela", "IMG", "20/1/2000", 12));
////            sb.deleteBook(4);
////            System.out.println(sb.getBook(13));
//            System.out.println(sb.getBooks());
////            
//              
//        } catch (Exception e) {
//            System.out.println("Exception Table BOOK");
//        }

    }

}
