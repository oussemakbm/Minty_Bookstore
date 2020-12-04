/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.models.User;
import com.services.ServiceUser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author DellXPS
 */
public class EditProfileController implements Initializable {

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField numTelTxt;

    @FXML
    private JFXTextArea adressTxt;

    @FXML
    private ImageView avatarPic;

    @FXML
    private JFXButton uploadBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    HamburgerBackArrowBasicTransition burgerTask;

    private FileInputStream fis;
    
    byte[] person_image = null;
    

    @FXML
    void onClickCancel(ActionEvent event) {
        SceneLoader.getInstance().NavigateTo(this.saveBtn.getScene().getWindow(), "profile");
    }

    @FXML
    void onClickSave(ActionEvent event) {
//        I have to validate all TextFields before sumbiting
//        Validation Will Start Here
//        Validation Will End Here
        
        User u = ServiceUser.getConnectedUser();
        u.setName(nameTxt.getText());
        try {
            ServiceUser.getInstance().updateUser(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    @FXML
    void onClickUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
//        Filters to only get Image files 
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
//       Open Browser Dialog to choose an image File 
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file.getName());
            System.out.println("Path: " + file.getPath());
        }
        
        BufferedImage bf;
        
        try {
             bf = ImageIO.read(file);
             Image image = SwingFXUtils.toFXImage(bf, null);
             this.avatarPic.setImage(image);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = SceneLoader.getInstance().getDrawerContent();
            this.drawer.setSidePane(box);
        } catch (Exception e) {
            e.printStackTrace();
        }

        burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> this.burgerClick(e));
//        Initialize Input fileds 
        this.nameTxt.setText(ServiceUser.getConnectedUser().getName());
        this.emailTxt.setText(ServiceUser.getConnectedUser().getEmail());
        this.adressTxt.setText(ServiceUser.getConnectedUser().getAdresse());
        this.numTelTxt.setText(ServiceUser.getConnectedUser().getNumTel());

    }

    private void burgerClick(MouseEvent event) {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();
        if (this.drawer.isShown()) {
            this.drawer.close();
        } else {
            this.drawer.open();
        }

    }

}
