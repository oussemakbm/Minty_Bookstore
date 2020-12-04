/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Author;
import com.models.Book;
import com.models.Category;
import com.models.Langue;
import com.models.Serie;
import com.services.ServiceAuthor;
import com.services.ServiceBook;
import com.services.ServiceCategory;
import com.services.ServiceLangue;
import com.services.ServiceSerie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import java.net.URL;

import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */


public class addBookController implements Initializable {

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
    private ComboBox author ;

    @FXML
    private ComboBox langue;

    @FXML
    private ComboBox category;

    @FXML
    private ComboBox serie;
    
    
     @FXML
    private ImageView gif;
     @FXML
    private ImageView imgGridView;
     ObservableList<Author> listAuthor=null;
     ObservableList<Langue> listLangue = null;
     ObservableList<Category> listCategorys = null;
     ObservableList<Serie> listSeries = null;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gif.setVisible(false);
        getAuthors();
        getLangues();
        getCategory();
        getSerie();

    
    }
    public void getCategory() 
    {
        ServiceCategory sc  = ServiceCategory.getInstance();
        try{
            ArrayList<Category> ac  = sc.getCategories();
            listCategorys = FXCollections.observableArrayList(ac);
            StringConverter<Category> converter = new StringConverter<Category>() {
                @Override
                public String toString(Category object) {
                    return object.getName();
                }

                @Override
                public Category fromString(String string) {
                    return null;
                }
            };
        category.setConverter(converter);
        category.setItems(listCategorys);
            
            
        } catch (Exception e) {
            System.out.println("error category list");
        }
    }
    
    
    public void getSerie() 
    {
        ServiceSerie ss  = ServiceSerie.getInstance();
        try{
            ArrayList<Serie> as  = ss.getSeries();
            listSeries = FXCollections.observableArrayList(as);
            StringConverter<Serie> converter = new StringConverter<Serie>() {
                @Override
                public String toString(Serie object) {
                    return object.getName();
                }

                @Override
                public Serie fromString(String string) {
                    return null;
                }
            };
        serie.setConverter(converter);
        serie.setItems(listSeries);
            
            
        } catch (Exception e) {
            System.out.println("error category list");
        }
    }
    
    public void getLangues() 
    {
        ServiceLangue sl  = ServiceLangue.getInstance();
        try{
            ArrayList<Langue> al  = sl.getLangues();
            listLangue = FXCollections.observableArrayList(al);
            StringConverter<Langue> converter = new StringConverter<Langue>() {
                @Override
                public String toString(Langue object) {
                    return object.getName();
                }

                @Override
                public Langue fromString(String string) {
                    return null;
                }
            };
        langue.setConverter(converter);
        langue.setItems(listLangue);
           
            
           
            
        } catch (Exception e) {
        }
    }
    
    public void getAuthors(){
        
        ServiceAuthor sa = ServiceAuthor.getInstance();
        try {
            ArrayList<Author> al =sa.getAuthors();
            listAuthor = FXCollections.observableArrayList(al);
            StringConverter<Author> converter = new StringConverter<Author>() {
                @Override
                public String toString(Author object) {
                    return object.getName();
                }

                @Override
                public Author fromString(String string) {
                    return null;
                }
        };
        author.setConverter(converter);
        author.setItems(listAuthor);
            
           
            
        } catch (Exception e) {
        }
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
    public void AddBookAction(ActionEvent e) throws SQLException{
        gif.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        ServiceBook sb = ServiceBook.getInstance();
        
        //get id langue choisit
        ServiceLangue sl = ServiceLangue.getInstance();
        String langue_choisit = langue.getValue().toString();
        int id_langue=  sl.getLangues().stream().filter(l->l.toString().equals(langue_choisit)).mapToInt(l->l.getId()).findFirst().getAsInt();
        
        //get id autheur choisit
        ServiceAuthor sau = ServiceAuthor.getInstance();
        String autheur_choist = author.getValue().toString();
        int id_autheur = sau.getAuthors().stream().filter(l->l.toString().equals(autheur_choist)).mapToInt(l->l.getId()).findFirst().getAsInt();
        //get id serie choisit
        
        ServiceSerie ss = ServiceSerie.getInstance();
        String serie_choist = serie.getValue().toString();
        int id_serie = ss.getSeries().stream().filter(l->l.toString().equals(serie_choist)).mapToInt(l->l.getId()).findFirst().getAsInt();
        
        //getget id categorie choisit
        
        ServiceCategory sc = ServiceCategory.getInstance();
        String category_choisit = category.getValue().toString();
        int id_category = sc.getCategories().stream().filter(l->l.toString().equals(category_choisit)).mapToInt(l->l.getId()).findFirst().getAsInt();

            try {
                
                sb.addBook(new Book(id_serie, id_category, id_autheur, id_langue, Integer.parseInt(quantity.getText()), Integer.parseInt(nbr_page.getText()), 1, title.getText(), desc.getText(), img_url.getText(), pub_date.getText(), Float.parseFloat(price.getText())));
               
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
