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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

    }
      @FXML
    void goAuthors(ActionEvent event) {

    }

    @FXML
    void goBook(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "addBook");

    }

    @FXML
    void goCategory(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddAuthorAdmin");

    }

    @FXML
    void goHome(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");
        

    }

    @FXML
    void goLanguage(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "addlangue");
        

    }

    @FXML
    void goUsers(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddUserAdmin");

    }

    
    
    
     

}