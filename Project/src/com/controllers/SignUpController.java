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
import com.models.User;
import com.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton BtnSignUp;

    @FXML
    private JFXTextField textUser;

    @FXML
    private JFXPasswordField textPassword;

    @FXML
    private JFXTextField textEmail;

    @FXML
    private JFXTextField textTel;

    @FXML
    private JFXTextField textAdresse;

    @FXML
    private JFXTextField textpic;
    @FXML
    private ImageView gif;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gif.setVisible(false);
        textUser.setStyle("-fx-text-inner-color: #a0a2ab");
        textPassword.setStyle("-fx-text-inner-color: #a0a2ab");
        textEmail.setStyle("-fx-text-inner-color: #a0a2ab");
        textTel.setStyle("-fx-text-inner-color: #a0a2ab");
        textAdresse.setStyle("-fx-text-inner-color: #a0a2ab");
        textpic.setStyle("-fx-text-inner-color: #a0a2ab");

    }

    public void signUpAction(ActionEvent e) {
        gif.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
            ServiceUser us = ServiceUser.getInstance();
            User u = new User(textUser.getText(), textEmail.getText(), textPassword.getText(), "CLIENT", textTel.getText(), textAdresse.getText(), textpic.getText());
            try {
                us.addUser(u);
            } catch (SQLException ex) {
                System.out.println("Erreur Add USER");;
            }
            System.out.println("User Added Successfully");
//            System.out.println("Sign Up Successfully");

            gif.setVisible(false);
        });
        pt.play();

    }

    public void loginAction(ActionEvent e1) throws IOException {

        SceneLoader.getInstance().NavigateTo(BtnSignUp.getScene().getWindow(), "home");
    }

}
