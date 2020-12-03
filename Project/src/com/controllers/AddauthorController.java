/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.models.AuthorPrefer;
import com.services.ServicePreferAuthor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddauthorController implements Initializable {
 @FXML
    private JFXButton like;

    @FXML
    private ImageView profile;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField about;

    @FXML
    private JFXTextField titles;
    
    @FXML
    private ImageView image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void LikeAction(ActionEvent e) {
         ServicePreferAuthor pa    = ServicePreferAuthor.getInstance();
         AuthorPrefer a = new AuthorPrefer(1, 1);
         try {
             pa.addPreferAuthor(a);
             
         } catch (SQLException e1) {
             System.out.println("erreor add author");
         }
     
     
     }
    
}
