/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.models.Book;
import com.services.ServiceBook;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Pane idPane;
    
    private List<Book> displaylist;

    
    private List<Book> listbooks;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getBooks();
    }    
    
    public void getBooks(){
        ServiceBook sb = ServiceBook.getInstance();
        try {
            listbooks = sb.getBooks();
        } catch (SQLException e) {
            System.out.println("erreur getBooks");
        }
        //System.out.println(listbooks);
        displaylist = listbooks;
        VBox vbox = new VBox();
        for (int i = 0; i < displaylist.size(); i++) {
            // Labels
            Label lbRow = new Label((i+1)+" - ");
            lbRow.setLayoutX(400);
            lbRow.setLayoutY(18);
            lbRow.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");
            
            Label lbId = new Label("MB"+Integer.toString(listbooks.get(i).getId()));
            lbId.setLayoutX(174);
            lbId.setLayoutY(18);
            lbId.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 20 14 10;-fx-font-weight:bold");
            Pane tp = new Pane();
            tp.setPrefWidth(300.0);
            tp.setPrefHeight(18.0);
            Label lbTitle = new Label(listbooks.get(i).getTitle()); 
            lbTitle.setPrefWidth(300.0);
            lbTitle.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 5 8 5;-fx-font-weight:bold");
            tp.getChildren().add(lbTitle);
            
            Label lbDate = new Label(listbooks.get(i).getPublishDate()); 
            lbDate.setLayoutX(400);
            lbDate.setLayoutY(18);
            lbDate.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbPrice = new Label(Float.toString(listbooks.get(i).getPrix())+ " DT"); 
            lbPrice.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbQuantity = new Label(Integer.toString(listbooks.get(i).getQuantity())); 
            lbQuantity.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 50 8 10;-fx-font-weight:bold");

            // Buttons
            JFXButton btnc = new JFXButton("Consult");
            btnc.setId("c"+listbooks.get(i).getId());
            btnc.setOnAction((ActionEvent event) -> {
                JFXButton btn = (JFXButton) event.getSource();
                String id = btn.getId();
                System.out.println(id);
            });
            
            btnc.setLayoutX(772);
            btnc.setLayoutY(20);
            btnc.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btnc.getStyleClass().add("button");
            JFXButton btne = new JFXButton("Edit");
            btne.setId("e"+listbooks.get(i).getId());
            
            btne.setOnAction((ActionEvent event) -> {
                JFXButton btn = (JFXButton) event.getSource();
                String id = btn.getId();
                System.out.println(id);
            });
            
            btne.setLayoutX(845);
            btne.setLayoutY(20);
            btne.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btne.getStyleClass().add("button");
            JFXButton btnd = new JFXButton("Delete");
            //btnd.setGraphic(new ImageView("img/bin.png"));
            btnd.setId("d"+listbooks.get(i).getId());
            
            btnd.setOnAction((ActionEvent event) -> {
                JFXButton btn = (JFXButton) event.getSource();
                String id = btn.getId();
                System.out.println(id);
            });
            
            btne.setLayoutX(905);
            btne.setLayoutY(20);
            btnd.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btnd.getStyleClass().add("button");
            Pane pbtn = new Pane();
            pbtn.setLayoutY(20);
            pbtn.getChildren().addAll(btnc, btne, btnd);
            
            Pane pane = new Pane();
            HBox hbox = new HBox();
            pane.setPrefWidth(975.0);
            pane.setPrefHeight(70.0);
            pane.setStyle("-fx-background-color: #d9d9d9");
            pane.setStyle("-fx-border-color: #22577A");
            hbox.getChildren().addAll(lbRow, lbId, tp, lbDate, lbPrice, lbQuantity, btnc, btne, btnd);
            pane.getChildren().addAll(hbox);
            vbox.getChildren().addAll(pane);
        }
        idPane.getChildren().addAll(vbox);
    }
    
    @FXML
    public void AddBookAction(ActionEvent e){
        //SceneLoader.getInstance().NavigateTo(BtnAdd.getScene().getWindow(), "addBook");
        System.out.println("Add Book");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/addBook.fxml"));
            Parent root = loader.load();
            
            //Get controller
            AddBookController addBookController = loader.getController();
            addBookController.transferMessage("Find");
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Book");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void ConsultBookAction(ActionEvent e){
        //SceneLoader.getInstance().NavigateTo(BtnConsult.getScene().getWindow(), "consultBook");
    }
    
    public void EditBookAction(ActionEvent e){  
        //SceneLoader.getInstance().NavigateTo(BtnEdit.getScene().getWindow(), "editBook");
    }
    
    public void DeleteBookAction(ActionEvent e){
        ServiceBook sb    = ServiceBook.getInstance();
         int x = Integer.parseInt("5");
         try {
             sb.deleteBook(x);
             
         } catch (SQLException e1) {
             System.out.println("erreor add author");
        }
    }
}
