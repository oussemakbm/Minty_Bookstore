/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javaws.progress.Progress;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DellXPS
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML ProgressIndicator pi;
    @FXML
    private JFXTextField textUsername;

    @FXML
    private JFXPasswordField textPassword;

    @FXML
    private JFXButton jbtnSignUp;

    @FXML
    private JFXButton jbtnLLogin;

    @FXML
    private ImageView gifLoading;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        gifLoading.setVisible(false);
        textUsername.setStyle("-fx-text-inner-color: #a0a2ab");
        textPassword.setStyle("-fx-text-inner-color: #a0a2ab");
        
    }    
    @FXML
    public void loginAction(ActionEvent e){
        gifLoading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
            System.out.println("Login Successfully");
            gifLoading.setVisible(false);
        });
        pt.play();  
        
    }
    
    @FXML
    public void SignUpAction(ActionEvent e1) throws IOException{
            Window currentWindow = this.jbtnLLogin.getScene().getWindow();
            SceneLoader.getInstance().NavigateTo(currentWindow, "signUp");
    }
    
}
