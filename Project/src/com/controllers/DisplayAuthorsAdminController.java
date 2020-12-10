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
import com.models.User;
import com.services.ServiceAuthor;
import com.services.ServiceUser;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class DisplayAuthorsAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane sPaneUsersList;
    @FXML
    private VBox vBoxAuthorList;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXTextField textFieldSearch;
    
    
    List<Author> authors;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceAuthor sa = ServiceAuthor.getInstance();
        
         
        authors=new ArrayList();
        try {
            authors = sa.getAuthors();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh(authors);
    }    
    public void search(){
        refresh(searchUsers(textFieldSearch.getText()));
    }
    
        public List<Author> searchUsers(String searchText){
        List<Author> result=new ArrayList();
        for (Author author : authors){
            if ((author.getName().toUpperCase().indexOf(searchText.toUpperCase())!=-1) ){
                result.add(author);
            }
        }
        return result;
    }
        public void delete (int id){
            ServiceAuthor sa = ServiceAuthor.getInstance();
                try {
                    Alert alert=new Alert(AlertType.ERROR, "Delete this author ?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                    sa.deleteAuthor(id);
                    authors = sa.getAuthors();
                    refresh(authors);}
                    

                } catch (SQLException ex) {
                    Logger.getLogger(DisplayAuthorsAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                
            
            
        }
            @FXML
        void BackAdmin(ActionEvent event) {
            Window currentWindow = this.buttonAdd.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");
            

        }
         @FXML
    void addAuthor(ActionEvent event) {
        Window currentWindow = this.buttonAdd.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddAuthorAdmin");

    }

    
    public void refresh(List<Author> authors){
     
        boolean odd=true;
        
        vBoxAuthorList.getChildren().clear();
        for (Author author : authors){
            
        HBox box=new HBox();
        Label name=new Label(author.getName());
        Label desc=new Label(author.getDescription());
        Label pic=new Label(author.getPicUrl());
//        ImageView view3 = new ImageView(pic);
//        view3.setFitHeight(32);
//        view3.setFitWidth(32);
//        view3.setPreserveRatio(true);
        
        
        
        
        HBox actions=new HBox();
        File fileSettings = new File("C:\\Users\\MediaStudio\\Desktop\\java Project\\Minty_Bookstore\\Project\\src\\com\\img\\settings.png");
        Image image = new Image(fileSettings.toURI().toString());
        ImageView imageview=new ImageView();
        imageview.setImage(image);
        imageview.setFitWidth(40);
        imageview.setFitHeight(40);
        
        
        imageview.setOnMouseClicked((ActionEvent ) -> {
            ServiceAuthor.setThisAuthor(author);
            Window currentWindow = this.vBoxAuthorList.getScene().getWindow();
            SceneLoader.getInstance().NavigateTo(currentWindow, "EditAuthorAdmin");
        });
        
        
         File fileDelete = new File("C:\\Users\\MediaStudio\\Desktop\\java Project\\Minty_Bookstore\\Project\\src\\com\\img\\bin.png");
        Image imageDelete = new Image(fileDelete.toURI().toString());
        ImageView delete=new ImageView();
        delete.setImage(imageDelete);
        delete.setFitWidth(32);
        delete.setFitHeight(32);
        delete.setOnMouseClicked(new EventHandler(){           
            @Override
            public void handle(Event event) {
                delete(author.getId() );

                
                
            
            }
        });
        //JFXButton update=new JFXButton("*");
        //JFXButton delete=new JFXButton("-");
        
        actions.setSpacing(50);

        name.setAlignment(Pos.CENTER);
        desc.setAlignment(Pos.CENTER);
        pic.setAlignment(Pos.CENTER);
        actions.setAlignment(Pos.CENTER);
       
        
        name.setStyle("-fx-font-size:14");
        desc.setStyle("-fx-font-size:14");
        pic.setStyle("-fx-font-size:14");
        
        name.setMinWidth(255);
        actions.setMinWidth(255);
        desc.setMinWidth(300);
        pic.setMinWidth(255);
        
        name.setMinHeight(50);
        desc.setMinHeight(50);
        actions.setMinHeight(50);
        pic.setMinHeight(50);
        desc.setWrapText(true);
        
        if (odd) box.setStyle("-fx-background-color:#fff");
        else box.setStyle("-fx-background-color: #d9d9d9");
        odd=!odd;

       
        actions.getChildren().add(imageview);
        actions.getChildren().add(delete);
        box.getChildren().add(name);
        box.getChildren().add(desc);
        box.getChildren().add(pic);
        box.getChildren().add(actions);
        
        vBoxAuthorList.getChildren().add(box);
        }
    }
    
}
