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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        
        VBox vbox = new VBox();
        for (int i = 0; i < listbooks.size(); i++) {
            // Labels
            Label lbRow = new Label((i+1)+" - ");
            lbRow.setLayoutX(400);
            lbRow.setLayoutY(18);
            lbRow.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 25 8 10;-fx-font-weight:bold");
            
            Label lbId = new Label("MB"+Integer.toString(listbooks.get(i).getId()));
            lbId.setLayoutX(174);
            lbId.setLayoutY(18);
            lbId.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 40 14 10;-fx-font-weight:bold");
            
            Label lbTitle = new Label(listbooks.get(i).getTitle()); 
            lbTitle.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbDate = new Label(listbooks.get(i).getPublishDate()); 
            lbDate.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbPrice = new Label(Float.toString(listbooks.get(i).getPrix())+ " DT"); 
            lbPrice.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbQuantity = new Label(Integer.toString(listbooks.get(i).getQuantity())); 
            lbQuantity.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 8 8 10;-fx-font-weight:bold");

            // Buttons
            JFXButton btnc = new JFXButton("Consult");
            btnc.setLayoutX(772);
            btnc.setLayoutY(20);
            btnc.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btnc.getStyleClass().add("button");
            JFXButton btne = new JFXButton("Edit");
            btne.setLayoutX(845);
            btne.setLayoutY(20);
            btne.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btne.getStyleClass().add("button");
            JFXButton btnd = new JFXButton("Delete");
            btne.setLayoutX(905);
            btne.setLayoutY(20);
            btnd.setStyle("-fx-background-color: #a6a6a6; -fx-padding:16 10 10 10;");
            //btnd.getStyleClass().add("button");
                    
            Pane pane = new Pane();
            HBox hbox = new HBox();
            pane.setPrefWidth(975.0);
            pane.setPrefHeight(70.0);
            pane.setStyle("-fx-background-color: #d9d9d9");
            pane.setStyle("-fx-border-color: #22577A");
            hbox.getChildren().addAll(lbRow, lbId, lbTitle, lbDate, lbPrice, lbQuantity, btnc, btne, btnd);
            pane.getChildren().add(hbox);
            vbox.getChildren().add(pane);
            idPane.getChildren().add(vbox);
        }
    }
    
    @FXML
    public void AddBookAction(ActionEvent e){
        //SceneLoader.getInstance().NavigateTo(BtnAdd.getScene().getWindow(), "addBook");
        System.out.println("Add Book");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/addBook.fxml"));
            Parent root = loader.load();
            
            //Get controller
            addBookController addBookController = loader.getController();
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
