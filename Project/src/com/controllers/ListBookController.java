/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import com.models.Author;
import com.models.Book;
import com.models.Category;
import com.models.Serie;
import com.services.ServiceAuthor;
import com.services.ServiceBook;
import com.services.ServiceCategory;
import com.services.ServiceSerie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TextField fcode;

    @FXML
    private TextField fname;

    @FXML
    private TextField fauthor;

    @FXML
    private TextField fcate;

    @FXML
    private TextField fserie;
    
    @FXML
    private JFXSlider sliderP;
    
    @FXML
    private JFXCheckBox cbAvail;
    
    @FXML
    private Pane idPane;
    
    private List<Book> displaylist;
    
    private List<Book> listbooks;
    
    private List<Author> listauthors;

    private List<Category> listcategories;

    private List<Serie> listseries;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getBooks();
        getAuthors();
        getCategories();
        getSeries();
        getListeners();
    }    
    
    public void afficher(){
        System.out.println("Afficher : \n"+displaylist);
        idPane.getChildren().clear();
        VBox vbox = new VBox();
        for (int i = 0; i < displaylist.size(); i++) {
            // Labels
            Label lbRow = new Label((i+1)+" - ");
            lbRow.setLayoutX(400);
            lbRow.setLayoutY(18);
            lbRow.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");
            
            Label lbId = new Label("MB"+Integer.toString(displaylist.get(i).getId()));
            lbId.setLayoutX(174);
            lbId.setLayoutY(18);
            lbId.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 20 14 10;-fx-font-weight:bold");
            Pane tp = new Pane();
            tp.setPrefWidth(300.0);
            tp.setPrefHeight(18.0);
            Label lbTitle = new Label(displaylist.get(i).getTitle()); 
            lbTitle.setPrefWidth(300.0);
            lbTitle.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 5 8 5;-fx-font-weight:bold");
            tp.getChildren().add(lbTitle);
            
            Label lbDate = new Label(displaylist.get(i).getPublishDate()); 
            lbDate.setLayoutX(400);
            lbDate.setLayoutY(18);
            lbDate.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbPrice = new Label(Float.toString(displaylist.get(i).getPrix())+ " DT"); 
            lbPrice.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 10 8 10;-fx-font-weight:bold");

            Label lbQuantity = new Label(Integer.toString(displaylist.get(i).getQuantity())); 
            lbQuantity.setStyle("-fx-text-fill : #a6a6a6; -fx-font-size: 24.0; -fx-padding:16 50 8 10;-fx-font-weight:bold");

            // Buttons
            JFXButton btnc = new JFXButton("Consult");
            btnc.setId("c"+displaylist.get(i).getId());
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
            btne.setId("e"+displaylist.get(i).getId());
            
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
            btnd.setId("d"+displaylist.get(i).getId());
            
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
    
    public void getBooks(){
        ServiceBook sb = ServiceBook.getInstance();
        try {
            listbooks = sb.getBooks();
            displaylist = listbooks;
            afficher();
        } catch (SQLException e) {
            System.out.println("erreur getBooks");
        }
    }
    
    public void getAuthors(){
        ServiceAuthor sb = ServiceAuthor.getInstance();
        try {
            listauthors = sb.getAuthors();
        } catch (SQLException e) {
            System.out.println("erreur getAuthors");
        }
    }
    
    public void getCategories(){
        ServiceCategory sc = ServiceCategory.getInstance();
        try {
            listcategories = sc.getCategories();
        } catch (SQLException e) {
            System.out.println("erreur getCategories");
        }
    }
    
    public void getSeries(){
        ServiceSerie sb = ServiceSerie.getInstance();
        try {
            listseries = sb.getSeries();
        } catch (SQLException e) {
            System.out.println("erreur getSeries");
        }
    }
    
    public void getListeners(){
        fcode.textProperty().addListener((observale, oldtext, newtext)->{
            System.out.println(" Text Changed from "+ oldtext +" to " + newtext.toUpperCase() + "\n");
            if(!(newtext == null || newtext.equals(""))){
                displaylist = null;
                displaylist = listbooks.stream()
                        .filter(i ->  ("MB"+Integer.toString(i.getId())).toUpperCase().indexOf(newtext.toUpperCase()) > -1)
                        .peek(System.out::println)
                        .collect(Collectors.toList());
            }else
                displaylist = listbooks;
            afficher();
        });
        
        fname.textProperty().addListener((observale, oldtext, newtext)->{
            System.out.println(" Text Changed from "+ oldtext +" to " + newtext + "\n");
            if(!(newtext == null || newtext.equals(""))){
                displaylist = listbooks.stream()
                        .filter(i ->  i.getTitle().toUpperCase().indexOf(newtext.toUpperCase()) >= 0)
                        .collect(Collectors.toList());
            }else
                displaylist = listbooks;
            afficher();
        });
        
        fauthor.textProperty().addListener((observale, oldtext, newtext)->{
            System.out.println(" Text Changed from "+ oldtext +" to " + newtext + "\n");    
            if(!(newtext == null || newtext.equals(""))){ 
                displaylist = listbooks.stream()
                    .filter(b -> {
                        return  listauthors.stream()
                            .filter(author -> author.getName().toUpperCase().indexOf(newtext.toUpperCase()) >= 0)
                            .anyMatch(author -> {
                                Integer a = new Integer(author.getId());
                                    return a.equals(new Integer(b.getIdAuthor()));
                            });
                    })
                    .collect(Collectors.toList());
            }else
                displaylist = listbooks;
            afficher();      
        });
        
        fcate.textProperty().addListener((observale, oldtext, newtext)->{
            System.out.println(" Text Changed from "+ oldtext +" to " + newtext + "\n");
            if(!(newtext == null || newtext.equals(""))){
                
                displaylist = listbooks.stream()
                    .filter(b -> {
                        return  listcategories.stream()
                            .filter(cat -> cat.getName().toUpperCase().indexOf(newtext.toUpperCase()) >= 0)
                            .anyMatch(cat -> {
                                Integer c = new Integer(cat.getId());
                                    return c.equals(new Integer(b.getIdCategory()));
                            });
                    })
                    .collect(Collectors.toList());
            }else
                displaylist = listbooks;
            afficher();
        });
        
        fserie.textProperty().addListener((observale, oldtext, newtext)->{
            System.out.println(" Text Changed from "+ oldtext +" to " + newtext + "\n");
            if(!(newtext == null || newtext.equals(""))){ 
                displaylist = listbooks.stream()
                    .filter(b -> {
                        return  listseries.stream()
                            .filter(serie -> serie.getName().toUpperCase().indexOf(newtext.toUpperCase()) >= 0)
                            .anyMatch(serie -> {
                                Integer c = new Integer(serie.getId());
                                    return c.equals(new Integer(b.getIdSerie()));
                            });
                    })
                    .collect(Collectors.toList());
            }else
                displaylist = listbooks;
            afficher();
        });
        
        sliderP.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(
                   ObservableValue<? extends Number> observableValue, 
                   Number oldValue, 
                   Number newValue) {
                        System.out.println(" Text Changed from "+ oldValue +" to " + newValue + "\n");
                        if(newValue.floatValue() < 200){
                            displaylist = listbooks.stream()
                                    .filter(i ->  i.getPrix() <= newValue.floatValue())
                                    .peek(System.out::println)
                                    .collect(Collectors.toList());
                        }else if( newValue.floatValue() == 200)
                            displaylist = listbooks;
                        afficher();
                  }
            });
        
        cbAvail.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    System.out.println("Select Changed from "+ old_val +" to " + new_val + "\n");
                    if(!new_val){
                        displaylist = listbooks.stream()
                                .filter(i ->  i.getQuantity() > 0 )
                                .collect(Collectors.toList());
                    }else
                        displaylist = listbooks;
                    afficher();
        });

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
