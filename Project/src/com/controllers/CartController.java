/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.Book;
import com.models.CommandList;
import com.services.ServiceCommandLine;
import com.services.ServiceCommandList;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
/**
 * FXML Controller class
 *
 * @author ali
 */
public class CartController implements Initializable {


    @FXML
    private ListView lvBooks;
    @FXML
    private ListView lvCommandlist;
    @FXML
    private Button btnDelete;
    int id=0;
    @FXML
    private Button btnDeleteBook;
    @FXML
    private TextField tfAmount;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCommandLine scl = ServiceCommandLine.getInstance();
        ServiceCommandList sc = ServiceCommandList.getInstance();
               
             
        try {
           
                  

            ArrayList<CommandList> commandLists = sc.getCommandListUser(1);
            
            for ( CommandList cmlist : commandLists){
                lvCommandlist.getItems().add(cmlist);
            }
            lvCommandlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CommandList>(){
                @Override
                public void changed(ObservableValue<? extends CommandList> observable, CommandList oldValue, CommandList newValue) {
                    ArrayList<Book> Books;
                    lvBooks.getItems().clear();
                     try {
                         tfAmount.setText(Float.toString(scl.getPriceTotalBooks(newValue.getId())));
                         if (newValue!=null){
                        Books = scl.getBookList(newValue.getId());
                       /* StringConverter<Book> converter = new StringConverter<Book>(){
                            @Override
                            public String toString(Book object) {
                                return object.getTitle();
                            }

                            @Override
                            public Book fromString(String string) {
                                return null;
                            }
                            
                        };*/
                           for (Book book : Books){
                               
                               //book.setConverter(converter);
                lvBooks.getItems().add(book);
            }}
                    } catch (SQLException ex) {
                        Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      btnDelete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            
                try {
                    sc.deleteCommandList(newValue.getId());
                    lvCommandlist.getItems().remove( lvCommandlist.getSelectionModel().getSelectedIndex());
                    System.out.println(newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
                   
                             }
            
            });
            lvBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>(){
                @Override
                public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                    btnDeleteBook.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            
                try {
                    scl.deleteCommandLine(newValue.getId());
                    lvBooks.getItems().remove( lvBooks.getSelectionModel().getSelectedIndex());
                    System.out.println(newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
                }
            
            });
                    
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
}
