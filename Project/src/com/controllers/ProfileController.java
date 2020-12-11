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
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.services.ServiceUser;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

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
    
    @FXML
    private ImageView avatar;

    HamburgerBackArrowBasicTransition burgerTask;
    @FXML
    private JFXButton btnCart;
    @FXML
    private JFXButton wishlists;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wellcomeTxt.setText("Welcome " + ServiceUser.getConnectedUser().getName());
        avatar.setImage(new Image(ServiceUser.getConnectedUser().getProfilePicture()));
        try {
            VBox box = SceneLoader.getInstance().getDrawerContent();
            this.drawer.setSidePane(box);
            burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> this.burgerClick(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
        

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

    @FXML
    void goToEditProfile(ActionEvent event) {
        Window currentWindow = this.wellcomeTxt.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "editProfile");
    }

    @FXML
    private void goToCart(ActionEvent event) {
          Window currentWindow = this.wellcomeTxt.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayCart");
    }

    @FXML
    private void goToWishlists(ActionEvent event) {
          Window currentWindow = this.wellcomeTxt.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayWishLists");
        
    }

}
