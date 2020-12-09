/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class HomeAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton profileBtn;

    @FXML
    private JFXButton goUsers;

    @FXML
    private JFXButton GoAuthors;

    @FXML
    private JFXButton goLanguage;

    @FXML
    private JFXButton goCategorys;
        @FXML
    private JFXButton addBookbtn;

    @FXML
    private JFXButton biewBookBtn;
    
    @FXML
    private JFXButton addUserBtn;

    @FXML
    private JFXButton ViewUsersbtn;
    
    @FXML
    private JFXButton addAuthorBtn;

    @FXML
    private JFXButton ViewAuthorsbtn;
    @FXML
    private JFXButton addLanguageBtn;

    @FXML
    private JFXButton ViewLanguagebtn;
    @FXML
    private JFXButton addCategoryBtn;

    @FXML
    private JFXButton ViewCategorysbtn;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBookbtn.setVisible(false);
        biewBookBtn.setVisible(false);
        addUserBtn.setVisible(false);
        ViewUsersbtn.setVisible(false);
        addAuthorBtn.setVisible(false);
        ViewAuthorsbtn.setVisible(false);
        addLanguageBtn.setVisible(false);
        ViewLanguagebtn.setVisible(false);
        addCategoryBtn.setVisible(false);
        ViewCategorysbtn.setVisible(false);

    }
    
    
    @FXML
    void goCategory(MouseEvent event) {
        addBookbtn.setVisible(false);
        biewBookBtn.setVisible(false);
        addUserBtn.setVisible(false);
        ViewUsersbtn.setVisible(false);
        addAuthorBtn.setVisible(false);
        ViewAuthorsbtn.setVisible(false);
        addLanguageBtn.setVisible(false);
        ViewLanguagebtn.setVisible(false);
         if (addCategoryBtn.isVisible()){
        addCategoryBtn.setVisible(false);
        ViewCategorysbtn.setVisible(false);
        }
        else{
        addCategoryBtn.setVisible(true);
        ViewCategorysbtn.setVisible(true);
        }
        

    }
    
    
    @FXML
    void goLanguage(MouseEvent event) {
        addBookbtn.setVisible(false);
        biewBookBtn.setVisible(false);
        addUserBtn.setVisible(false);
        ViewUsersbtn.setVisible(false);
        addAuthorBtn.setVisible(false);
        ViewAuthorsbtn.setVisible(false);
        addCategoryBtn.setVisible(false);
        ViewCategorysbtn.setVisible(false);
         if (addLanguageBtn.isVisible()){
        addLanguageBtn.setVisible(false);
        ViewLanguagebtn.setVisible(false);
        }
        else{
        addLanguageBtn.setVisible(true);
        ViewLanguagebtn.setVisible(true);
        }
        
        

    }
    
    @FXML
    void goAuthors(MouseEvent event) {
        addBookbtn.setVisible(false);
        biewBookBtn.setVisible(false);
        addUserBtn.setVisible(false);
        ViewUsersbtn.setVisible(false);
        addLanguageBtn.setVisible(false);
        ViewLanguagebtn.setVisible(false);
        addCategoryBtn.setVisible(false);
        ViewCategorysbtn.setVisible(false);
        if (addAuthorBtn.isVisible()){
        addAuthorBtn.setVisible(false);
        ViewAuthorsbtn.setVisible(false);
        }
        else{
        addAuthorBtn.setVisible(true);
        ViewAuthorsbtn.setVisible(true);
        }
        

    }
    

    @FXML
    void goUsers(MouseEvent event) {
        addBookbtn.setVisible(false);
        biewBookBtn.setVisible(false);
        addLanguageBtn.setVisible(false);
        ViewLanguagebtn.setVisible(false);
        addAuthorBtn.setVisible(false);
        ViewAuthorsbtn.setVisible(false);
        addCategoryBtn.setVisible(false);
        ViewCategorysbtn.setVisible(false);

        if (addUserBtn.isVisible()){
        addUserBtn.setVisible(false);
        ViewUsersbtn.setVisible(false);
        }
        else{
        addUserBtn.setVisible(true);
        ViewUsersbtn.setVisible(true);
        }
        
        
        

    }
 

    @FXML
    void bookCilck(MouseEvent event) {
        addUserBtn.setVisible(false);
        ViewUsersbtn.setVisible(false);
        addLanguageBtn.setVisible(false);
        ViewLanguagebtn.setVisible(false);
        addCategoryBtn.setVisible(false);
        ViewCategorysbtn.setVisible(false);
        if (addBookbtn.isVisible()){
        addBookbtn.setVisible(false);
        biewBookBtn.setVisible(false);
        }
        else{
            addBookbtn.setVisible(true);
        biewBookBtn.setVisible(true);
        }
        

    }
        @FXML
    void AddAuthor(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddAuthorAdmin");

    }

    @FXML
    void goBook(ActionEvent event) {
        
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "addBook");


    }
        @FXML
    void AddUser(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddUserAdmin");
        

    }
    
        @FXML
    void DisplayUsers(ActionEvent event) {
         Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayUsersAdmin");

    }



    @FXML
    void goHome(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");
        

    }

 
        @FXML
    void AddLanguage(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "addlangue");

    }
    @FXML
    void View_Authors(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayAuthorsAdmin");
        
    }
    

//    @FXML
//    void goUsers(ActionEvent event) {
//        Window currentWindow = this.homeBtn.getScene().getWindow();
//        SceneLoader.getInstance().NavigateTo(currentWindow, "AddUserAdmin");
//
//    }
 

    
    
    
     

}