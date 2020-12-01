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
 * @author DellXPS
 */
public class DrawerContentController implements Initializable {

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton profileBtn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void GoToProfile(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "profile");
    }

    @FXML
    void goToHome(ActionEvent event) {
        Window currentWindow = this.homeBtn.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "home");
    }

}
