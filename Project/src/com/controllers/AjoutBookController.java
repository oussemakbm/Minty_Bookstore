/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Book;
import com.services.ServiceBook;
//import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */


public class AjoutBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton jbtnAddBook;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField desc;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField img_url;

    @FXML
    private JFXTextField quantity;

    @FXML
    private JFXTextField pub_date;

    @FXML
    private JFXTextField nbr_page;

    @FXML
    private ComboBox author;

    @FXML
    private ComboBox langue;

    @FXML
    private ComboBox category;

    @FXML
    private ComboBox serie;
    
    
     @FXML
    private ImageView gif;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gif.setVisible(false);
         ObservableList<String> listAuthor = FXCollections.observableArrayList("Alain-Fournie","Anouilh. Apollinaire","Barthes","Desnos");
         author.setItems(listAuthor);
         ObservableList<String> listLangue = FXCollections.observableArrayList("Anglais","Français","Arabe","Espagnol");
         langue.setItems(listLangue);
         ObservableList<String> listCategory = FXCollections.observableArrayList("dramatique","comédie","history","cuisine");
         category.setItems(listCategory);
         ObservableList<String> listSerie = FXCollections.observableArrayList("Hunters","Les Œufs verts au jambon","L'Aliéniste");
         serie.setItems(listSerie);
    
    }    
    @FXML
    public void AddBookAction(ActionEvent e){
        gif.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        ServiceBook sb = ServiceBook.getInstance();
//            Book b = new Book(1, 1, 1, 1, Integer.parseInt(quantity.getText()), Integer.parseInt(nbr_page.getText()), 1, title.getText(), desc.getText(), img_url.getText(), pub_date.getText(), Float.parseFloat(price.getText()));
//                            System.out.println(b);
           
            try {
                
                sb.addBook(new Book(1, 1, 1, 1, Integer.parseInt(quantity.getText()), Integer.parseInt(nbr_page.getText()), 1, title.getText(), desc.getText(), img_url.getText(), pub_date.getText(), Float.parseFloat(price.getText())));
                System.out.println("aaaa");
            } catch (SQLException ex) {
                System.out.println("Error Add Book");
            }
            
            System.out.println("Book Added Successfully");
            pt.setOnFinished(ev -> {
            
            
            
            
            
            gif.setVisible(false);
        });
        pt.play();
    }
    
}
