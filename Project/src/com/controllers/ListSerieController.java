/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.models.Serie;
import com.services.ServiceSerie;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListSerieController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private TextField fname;
    
    @FXML
    private Pane idPane;
    
    @FXML
    private JFXButton buttonBack;
    
    private List<Serie> displaylist;
    
    private List<Serie> listSeries;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getSeries();
        getListeners();
        
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFXButton btn = (JFXButton) event.getSource();
                String id = btn.getId();
                try {
                    Window window = btnAdd.getScene().getWindow();
                    SceneLoader.getInstance().NavigateTo(window, "AddSerie");  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }    
    
    public void afficher(){
        idPane.getChildren().clear();
        VBox vbox = new VBox();
        for (int i = 0; i < displaylist.size(); i++) {
            // Labels
            Label lbRow = new Label((i+1)+" - ");
            lbRow.setLayoutX(400);
            lbRow.setLayoutY(18);
            lbRow.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");
           
            Pane tp = new Pane();
            tp.setPrefWidth(500.0);
            tp.setPrefHeight(18.0);
            Label lbTitle = new Label(displaylist.get(i).getName()); 
            lbTitle.setPrefWidth(300.0);
            lbTitle.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 5 8 5;-fx-font-weight:bold");
            tp.getChildren().add(lbTitle);
            
            // Buttons
            JFXButton btne = new JFXButton("Edit");
            btne.setId("e"+displaylist.get(i).getId());
            
            btne.setOnAction((ActionEvent event) -> {
                JFXButton btn = (JFXButton) event.getSource();
                String id = btn.getId();
                try {
                    System.out.println("Setting SerieId: "+ btn.getId().substring(1) + " To Scene Loader setBookId method");
                    SceneLoader.getInstance().setSerieId(Integer.parseInt(btn.getId().substring(1)));
                    Window window = btne.getScene().getWindow();
                    SceneLoader.getInstance().NavigateTo(window, "UpdateSerie");  
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            
            btne.setLayoutX(845);
            btne.setLayoutY(20);
            btne.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btne.getStyleClass().add("button");
            JFXButton btnd = new JFXButton("Delete");
//            Image img = new Image("img\\icons\\delete.png");
//            ImageView view = new ImageView(img);
//            btnd.setGraphic(view);
            //btnd.setGraphic(new ImageView("img/bin.png"));
            btnd.setId("d"+displaylist.get(i).getId());
            
            btnd.setOnAction((ActionEvent event) -> {
                JFXButton btn = (JFXButton) event.getSource();
                System.out.println(btn.getId().substring(1));
                ServiceSerie ss = ServiceSerie.getInstance();
                try {
                    ss.deleteSerie(Integer.parseInt(btn.getId().substring(1)));
                    Serie s = displaylist.get(Integer.parseInt(btn.getId().substring(1)));
                    //displaylist.remove(Integer.parseInt(btn.getId().substring(1)));
                    listSeries.remove(s);
                    displaylist = listSeries;
                    afficher();
                } catch (SQLException e) {
                    System.out.println("erreor add author");
               }
            });
            
            btnd.setLayoutX(905);
            btnd.setLayoutY(20);
            btnd.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btnd.getStyleClass().add("button");
            Pane pbtn = new Pane();
            pbtn.setLayoutY(20);
            pbtn.getChildren().addAll(btne, btnd);
            
            Pane pane = new Pane();
            HBox hbox = new HBox();
            pane.setPrefWidth(750.0);
            pane.setPrefHeight(70.0);
            pane.setStyle("-fx-background-color: #d9d9d9");
            pane.setStyle("-fx-border-color: #22577A");
            hbox.getChildren().addAll(lbRow, tp, btne, btnd);
            pane.getChildren().addAll(hbox);
            vbox.getChildren().addAll(pane);
        }
        idPane.getChildren().addAll(vbox);
    }
    
    public void getSeries(){
        ServiceSerie ss = ServiceSerie.getInstance();
        try {
            listSeries = ss.getSeries();
            displaylist = listSeries;
            afficher();
        } catch (SQLException e) {
            System.out.println("erreur getSeries");
        }
    }
    
    public void getListeners(){
        
        fname.textProperty().addListener((observale, oldtext, newtext)->{
            System.out.println(" Text Changed from "+ oldtext +" to " + newtext + "\n");
            if(!(newtext == null || newtext.equals(""))){
                displaylist = listSeries.stream()
                        .filter(i ->  i.getName().toUpperCase().indexOf(newtext.toUpperCase()) >= 0)
                        .collect(Collectors.toList());
            }else
                displaylist = listSeries;
            afficher();
        });
    }    
    
    @FXML
    void BackAdmin(ActionEvent event) {
        Window currentWindow = this.buttonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");

    }
}
