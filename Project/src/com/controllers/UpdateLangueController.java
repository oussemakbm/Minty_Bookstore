/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Langue;
import com.services.ServiceLangue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class UpdateLangueController implements Initializable {

    @FXML
    private JFXTextField textlangue;

    @FXML
    private JFXButton jbtnSave;

    @FXML
    private ImageView gifLoading;
    
    @FXML
    private ImageView gif;
    
    @FXML
    private ImageView imgGridView;
    
    @FXML
    private JFXButton buttonBack;
    
    public Langue lg  = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gifLoading.setVisible(false);
        textlangue.setStyle("-fx-text-inner-color: #a0a2ab");
        getLangue(SceneLoader.getInstance().getSelectedLangueId());
    }
    
    public void getLangue(int x){
        ServiceLangue sb  = ServiceLangue.getInstance();
        try {
                lg = sb.getLangue(x);
               
        } catch (SQLException ex) {
            System.out.println("Error Update Langue");
        } 
        textlangue.setText(lg.getName());
    }
    
    @FXML
    public void saveAction(ActionEvent e){
        gifLoading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
            ServiceLangue ls = ServiceLangue.getInstance();
            try {
                ls.updateLangue(new Langue(lg.getId(),textlangue.getText()));
            } catch (SQLException ex) {
                System.out.println("Erreur Update LANGUE");;
            }
            System.out.println("LANGUE Updated Successfully");
            //SceneLoader.getInstance().NavigateTo(this.jbtnSave.getScene().getWindow(), "login");
            gifLoading.setVisible(false);
        });
        pt.play();
    }
    
    
     @FXML
    void DraggerButtonAction(DragEvent event) {
        if(event.getDragboard().hasFiles()){
        event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    @FXML
    void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imgGridView.setImage(img);

    }
    
    @FXML
    void BackAdmin(ActionEvent event) {
        Window currentWindow = this.buttonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "ListLangues");

    }
    
}
