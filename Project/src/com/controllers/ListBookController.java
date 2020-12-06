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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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
    private JFXButton BtnAdd;
    
    @FXML
    private JFXButton BtnConsult;
    
    @FXML
    private JFXButton BtnEdit;
    
    @FXML
    private JFXButton BtnDelete;
    
    @FXML
    private Pane pane;
    
    @FXML
    private Label lbid;
    
    @FXML
    private Label lbtitle;
    
    @FXML
    private Label lbdate;
    
    @FXML
    private Label lbprice;
    
    @FXML
    private Label lbquantity;
    
    private List<Book> listbooks;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getBooks();
    }    
    
    public void getBooks(){
        ServiceBook sb    = ServiceBook.getInstance();
        try {
            listbooks = sb.getBooks();
        } catch (SQLException e) {
            System.out.println("erreur getBooks");
        }
        
        for (int i = 1; i < listbooks.size(); i++) {
            lbid = new Label(Integer.toString(listbooks.get(i).getId()));
            lbtitle = new Label(listbooks.get(i).getTitle()); 
            lbdate = new Label(listbooks.get(i).getPublishDate()); 
            lbprice = new Label(Float.toString(listbooks.get(i).getPrix())); 
            lbquantity = new Label(Integer.toString(listbooks.get(i).getQuantity())); 
        }
        pane = new Pane();
        pane.setLayoutX(14.0);
                    pane.setLayoutY(137.0);
                    pane.setPrefHeight(200.0);
                   pane.setPrefWidth(705.0);
                    pane.setStyle("-fx-background-color: darkgrey");

//        pane.getChildren().addAll(lblusername,lbldescription);
    }
    
    public void AddBookAction(ActionEvent e){
        SceneLoader.getInstance().NavigateTo(BtnAdd.getScene().getWindow(), "addBook");
    }
    
    public void ConsultBookAction(ActionEvent e){
        SceneLoader.getInstance().NavigateTo(BtnConsult.getScene().getWindow(), "consultBook");
    }
    
    public void EditBookAction(ActionEvent e){  
        SceneLoader.getInstance().NavigateTo(BtnEdit.getScene().getWindow(), "editBook");
    }
    
    public void DeleteBookAction(ActionEvent e){
        ServiceBook sb    = ServiceBook.getInstance();
         int  x = Integer.parseInt(this.lbquantity.getText());
         try {
             sb.deleteBook(x);
             
         } catch (SQLException e1) {
             System.out.println("erreor add author");
         }
    }
    
}
