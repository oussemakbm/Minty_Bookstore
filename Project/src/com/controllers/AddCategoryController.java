/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Category;
import com.services.ServiceCategory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ali
 */
public class AddCategoryController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private ImageView gif;
    @FXML
    private Label lresultat;
    @FXML
    private JFXButton jbtnAddCategory;
    @FXML
    private JFXButton reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       jbtnAddCategory.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                gif.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        Category c = new Category();
        c.SetName(title.getText());
        ServiceCategory sc = ServiceCategory.getInstance();
        try {
                
                sc.addCategory(new Category(title.getText()));
                lresultat.setText("Category Added successfully !");
               
            } catch (SQLException ex) {
                lresultat.setText("Error");
            }
            System.out.println(c);
            
            pt.setOnFinished(ex -> {
            
            
            
            
            
            gif.setVisible(false);
        });
        pt.play();

                }
        
    });
       
       reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                title.setText("");
                
            }
        });
   
    }    
    
}
