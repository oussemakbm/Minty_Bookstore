/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Serie;
import com.services.ServiceSerie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AddSerieController implements Initializable {

    @FXML
    private JFXTextField textserie;
    @FXML
    private JFXButton jbtnSave;
    @FXML
    private ImageView gifLoading;
    
    @FXML
    private JFXButton buttonBack;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gifLoading.setVisible(false);
        textserie.setStyle("-fx-text-inner-color: #a0a2ab");
    }   
    
    @FXML
    public void saveAction(ActionEvent e){
        gifLoading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
            ServiceSerie ss = ServiceSerie.getInstance();
            try {
                ss.addSerie(new Serie(textserie.getText()));
            } catch (SQLException ex) {
                System.out.println("Erreur Add LANGUE");;
            }
            System.out.println("LANGUE Added Successfully");
            //SceneLoader.getInstance().NavigateTo(this.jbtnSave.getScene().getWindow(), "login");
            gifLoading.setVisible(false);
        });
        pt.play();
    } 
    
    @FXML
    void BackAdmin(ActionEvent event) {
        Window currentWindow = this.buttonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "ListSerie");

    }
}
