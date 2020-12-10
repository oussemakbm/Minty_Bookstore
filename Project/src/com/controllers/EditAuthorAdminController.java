/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.models.Author;
import com.services.ServiceAuthor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class EditAuthorAdminController implements Initializable {

    @FXML
    private ImageView profile;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton uploadPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.name.setText(ServiceAuthor.getThisAuthor().getName());
        this.description.setText(ServiceAuthor.getThisAuthor().getDescription());
        
        
    }    

    @FXML
    private void saveAction(ActionEvent event) {
        
        Author a = ServiceAuthor.getThisAuthor();
        a.setName(name.getText());
        a.setDescription(description.getText());
        System.out.println(a.toString());
        try {
            ServiceAuthor.getInstance().updateAuthor(a);
            System.out.println(a.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
