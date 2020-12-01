/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.services.ServiceUser;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DellXPS
 */
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private Label wellcomeTxt;
    

    HamburgerBackArrowBasicTransition burgerTask;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wellcomeTxt.setText("Wellcome "+ ServiceUser.getConnectedUser().getName());
        try {
            VBox box = SceneLoader.getInstance().getDrawerContent();
            this.drawer.setSidePane(box);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> this.burgerClick(e));

    }

    private void burgerClick(MouseEvent event) {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();
        if (this.drawer.isOpened()) {
            this.drawer.close();
        } else {
            this.drawer.open();
        }

    }

}
