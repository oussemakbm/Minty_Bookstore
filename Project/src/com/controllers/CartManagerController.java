/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.models.Book;
import com.models.CommandLine;
import com.models.CommandList;
import com.models.Langue;
import com.services.ServiceBook;
import com.services.ServiceCommandLine;
import com.services.ServiceCommandList;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class CartManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField ftprice;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btndeleteAll;

    @FXML
    private Pane idPane;

    @FXML
    private JFXButton buttonBack;
    
    private CommandList list;
    
    private ArrayList<Book> listbooks;
    
    private ArrayList<CommandLine> displayline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SceneLoader.getInstance().setCommande();
        list = SceneLoader.getInstance().getSelectedList();
        displayline = list.getCls();
        getBooks();
        getCommandLines();
    }    
    public void getCommandLines(){
        idPane.getChildren().clear();
        VBox vbox = new VBox();
        for (int i = 0; i < displayline.size(); i++) {
            // Labels
            Label lbRow = new Label((i+1)+" - ");
            lbRow.setLayoutX(400);
            lbRow.setLayoutY(18);
            lbRow.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");
           
            Pane tp = new Pane();
            tp.setPrefWidth(300.0);
            tp.setPrefHeight(18.0);
            Label lbTitle = null;
            for(Book b : listbooks){
                if(b.getId() == displayline.get(i).getIdBook()){
                    lbTitle = new Label(b.getTitle());
                }     
            }
             
            lbTitle.setPrefWidth(300.0);
            lbTitle.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 5 8 5;-fx-font-weight:bold");
            tp.getChildren().add(lbTitle);
            
            TextField txq = null;
            txq = new TextField(Integer.toString(displayline.get(i).getQuantity()));
            txq.setPrefWidth(100.0);
            txq.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 5 8 50;-fx-font-weight:bold");
            // Buttons
            JFXButton btnd = new JFXButton("Delete");
//            Image img = new Image("img\\icons\\delete.png");
//            ImageView view = new ImageView(img);
//            btnd.setGraphic(view);
            //btnd.setGraphic(new ImageView("img/bin.png"));
            btnd.setId("d"+displayline.get(i).getId());
            
            btnd.setOnAction((ActionEvent event) -> {
                JFXButton btn = (JFXButton) event.getSource();
                System.out.println(btn.getId().substring(1));
            });
            
            btnd.setLayoutX(905);
            btnd.setLayoutY(20);
            btnd.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btnd.getStyleClass().add("button");
            Pane pbtn = new Pane();
            pbtn.setLayoutY(20);
            pbtn.getChildren().addAll(btnd);
            
            Pane pane = new Pane();
            HBox hbox = new HBox();
            pane.setPrefWidth(750);
            pane.setPrefHeight(70.0);
            pane.setStyle("-fx-background-color: #d9d9d9");
            pane.setStyle("-fx-border-color: #22577A");
            hbox.getChildren().addAll(lbRow, tp, txq, btnd);
            pane.getChildren().addAll(hbox);
            vbox.getChildren().addAll(pane);
        }
        idPane.getChildren().addAll(vbox);
        
    }
    
    public void getBooks(){
        ServiceBook sb = ServiceBook.getInstance();
        try {
            listbooks = sb.getBooks();
        } catch (SQLException e) {
            System.out.println("erreur getBooks");
        }
    }
    
    @FXML
    void BackAdmin(ActionEvent event) {
        Window currentWindow = this.buttonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "home");
    }

}
