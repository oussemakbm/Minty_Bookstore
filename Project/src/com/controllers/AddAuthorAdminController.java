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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddAuthorAdminController implements Initializable {
  @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField about;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXButton save;

    @FXML
    private JFXTextField image;
      @FXML
    private JFXTextField name;
      @FXML
    private AnchorPane UploadPhoto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     public void saveAction(ActionEvent e) {
         ServiceAuthor sa    = ServiceAuthor.getInstance();
         Author a = new Author(name.getText(), description.getText(), image.getText());
         try {
             sa.addAuthor(a);
             
         } catch (SQLException e1) {
             System.out.println("erreor add author");
         }
     
     
     }
      public void UploadPhotoAction(ActionEvent e) {
         ServiceAuthor sa    = ServiceAuthor.getInstance();
         Author a = new Author( name.getText(), description.getText(), image.getText());
         try {
             sa.addAuthor(a);
             
         } catch (SQLException e1) {
             System.out.println("erreor add author");
         }
     
     
     }
    
    
}
