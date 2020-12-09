/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.User;
import com.services.ServiceUser;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ihebf
 */
public class DisplayUsersAdminController implements Initializable {

    
     
    @FXML
    private ScrollPane sPaneUsersList;
    @FXML
    private VBox vBoxUsersList;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXTextField textFieldSearch;
    
    List<User> users;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceUser su=ServiceUser.getInstance();
         
        users=new ArrayList();
        try {
            users = su.getUsers();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh(users);

        // TODO
         //OptionalDouble average = employees.stream().filter((e)->{return (e.getName().charAt(0)=='s');}).mapToInt((e)->{return e.getSalary();}).average();
              
    }  
   
    
    
       @FXML
    void AddUser(ActionEvent event) {
        Window currentWindow = this.sPaneUsersList.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddUserAdmin");
    }
        @FXML
    void BackAdmin(ActionEvent event) {
        Window currentWindow = this.sPaneUsersList.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");

    }
    public void search(){
        refresh(searchUsers(textFieldSearch.getText()));
    }
    
    public List<User> searchUsers(String searchText){
        List<User> result=new ArrayList();
        for (User user : users){
            if ((user.getName().toUpperCase().indexOf(searchText.toUpperCase())!=-1) ||
                 (user.getEmail().toUpperCase().indexOf(searchText.toUpperCase()) !=-1) ||
                     (user.getRole().toUpperCase().indexOf(searchText.toUpperCase()) !=-1) 
                    
                    ){
                result.add(user);
            }
        }
        return result;
    }
    
    
    
    public void refresh(List<User> users){
     
        boolean odd=true;
        
        vBoxUsersList.getChildren().clear();
        for (User user : users){
            
        HBox box=new HBox();
        Label name=new Label(user.getName());
        Label email=new Label(user.getEmail());
        Label role=new Label(user.getRole());
        HBox actions=new HBox();
        File fileSettings = new File("C:\\Users\\ihebf\\OneDrive\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\icons\\Settings.png");
        Image image = new Image(fileSettings.toURI().toString());
        ImageView imageview=new ImageView();
        imageview.setImage(image);
        imageview.setFitWidth(40);
        imageview.setFitHeight(40);
        
         File fileDelete = new File("C:\\Users\\ihebf\\OneDrive\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\icons\\delete.png");
        Image imageDelete = new Image(fileDelete.toURI().toString());
        ImageView delete=new ImageView();
        delete.setImage(imageDelete);
        delete.setFitWidth(32);
        delete.setFitHeight(32);
        //JFXButton update=new JFXButton("*");
        //JFXButton delete=new JFXButton("-");
        
        actions.setSpacing(50);

        name.setAlignment(Pos.CENTER);
        email.setAlignment(Pos.CENTER);
        role.setAlignment(Pos.CENTER);
        actions.setAlignment(Pos.CENTER);
        
        name.setStyle("-fx-font-size:14");
        email.setStyle("-fx-font-size:14");
        role.setStyle("-fx-font-size:14");
        
        name.setMinWidth(255);
        actions.setMinWidth(255);
        email.setMinWidth(255);
        role.setMinWidth(255);
        
        name.setMinHeight(50);
        email.setMinHeight(50);
        actions.setMinHeight(50);
        role.setMinHeight(50);
        email.setWrapText(true);
        
        if (odd) box.setStyle("-fx-background-color:#fff");
        else box.setStyle("-fx-background-color: #d9d9d9");
        odd=!odd;

       
        actions.getChildren().add(imageview);
        actions.getChildren().add(delete);
        box.getChildren().add(name);
        box.getChildren().add(email);
        box.getChildren().add(role);
        box.getChildren().add(actions);
        
        vBoxUsersList.getChildren().add(box);
        }
    }
    
}
