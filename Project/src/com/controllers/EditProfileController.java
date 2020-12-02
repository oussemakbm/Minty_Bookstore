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
import com.services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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

    @FXML
    void onClickCancel(ActionEvent event) {
        SceneLoader.getInstance().NavigateTo(this.saveBtn.getScene().getWindow(), "profile");
    }

    @FXML
    void onClickSave(ActionEvent event) {
//        I have to validate all TextFields before sumbiting
//        Validation Will Start Here
//        Validation Will End Here
    


    }

    @FXML
    void onClickUpload(ActionEvent event) {
          
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
