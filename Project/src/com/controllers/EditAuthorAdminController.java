/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.models.Author;
import com.services.ServiceAuthor;
import com.util.HttpPost;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JFileChooser;

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
    
     @FXML
    void onClickUpload(ActionEvent event) {
        Author currentAuthor = ServiceAuthor.getThisAuthor();
        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        File selectedFile = fc.getSelectedFile();
        String path = selectedFile.getAbsolutePath();
        System.out.println(selectedFile.getAbsolutePath());
        String filename = "file:///" + path;
        Image img = new Image(filename);
        profile.setImage(img);
        
        try {
            HttpPost httpPost = new HttpPost(new URL("http://localhost/bookstore/upload_profile_img.php"));
            httpPost.setFileNames(new String[]{ selectedFile.getAbsolutePath() });
            httpPost.post();
            Author newAuthor = currentAuthor;
            
            newAuthor.setPicUrl("http://localhost/bookstore/profileImages/"+selectedFile.getName());
            try {
                ServiceAuthor.getInstance().updateAuthor(newAuthor);
                ServiceAuthor.setThisAuthor(ServiceAuthor.getInstance().getAuthor(currentAuthor.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(httpPost.getOutput());
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpPost.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }        
    }
    
    @FXML
    void backTohome(ActionEvent event) {
        Window currentWindow = this.name.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");

    }


    }
    

