/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Category;
import com.services.ServiceCategory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ali
 */
public class CategoryManagerController implements Initializable {

    @FXML
    private VBox vBoxCategoriesList;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXTextField textFieldSearch;
    List<Category> categories;
    @FXML
    private ScrollPane sPaneCategoriesList;
    @FXML
    private JFXButton buttonBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceCategory sc= ServiceCategory.getInstance();
        
        categories=new ArrayList();
        
        try {
            categories = sc.getCategories();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh(categories);
       

        
    }    

    @FXML
    private void search(KeyEvent event) {
refresh(SearchCategories(textFieldSearch.getText()));
    }
    public List<Category> SearchCategories(String searchText){
        List<Category> result=new ArrayList();
        for (Category category : categories){
            if ((category.getName().toUpperCase().indexOf(searchText.toUpperCase())!=-1)){
                result.add(category);
            }
            
    }
         return result;

    }
    public void refresh(List<Category> categories){
        boolean odd=true;
        vBoxCategoriesList.getChildren().clear();
        
        for (Category category : categories){
            HBox box=new HBox();
            box.setAlignment(Pos.CENTER);
            Label name=new Label(category.getName());
            HBox actions=new HBox();
            File fileSettings = new File("C:\\Users\\ali\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\icons\\Settings.png");
            Image imageSettings = new Image(fileSettings.toURI().toString());
            ImageView update=new ImageView();
            update.setImage(imageSettings);
        update.setFitWidth(40);
        update.setFitHeight(40);
        File fileDelete = new File("C:\\Users\\ali\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\icons\\delete.png");
        Image imageDelete = new Image(fileDelete.toURI().toString());
        ImageView delete=new ImageView();
        delete.setImage(imageDelete);
        delete.setFitWidth(32);
        delete.setFitHeight(32);
        actions.setSpacing(50);
        
        
        name.setAlignment(Pos.CENTER);
        actions.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font-size:14");
        
        
        name.setMinWidth(255);
        actions.setMinWidth(255);
        
        name.setMinHeight(50);
        
        if (odd) box.setStyle("-fx-background-color:#fff");
        else box.setStyle("-fx-background-color: #d9d9d9");
        odd=!odd;
        
        actions.getChildren().add(update);
        actions.getChildren().add(delete);
        box.getChildren().add(name);
        
        box.getChildren().add(actions);
        
        vBoxCategoriesList.getChildren().add(box);
        
                  ServiceCategory sc= ServiceCategory.getInstance();
                  
                 /* update.setOnMouseClicked((MouseEvent e)->{
              try {
                  System.out.println("fddsffds");
                    sc.updateCategory(category);
                    System.out.println(sc.getCategories());
                    refresh(sc.getCategories());
                } catch (SQLException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
          });
                  */
                  
          delete.setOnMouseClicked((MouseEvent e)->{
              try {
                  System.out.println("fddsffds");
                    sc.deleteCategory(category.getId());
                    System.out.println(sc.getCategories());
                    refresh(sc.getCategories());
                } catch (SQLException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
          });
          update.setOnMouseClicked((MouseEvent e)->{
               Window currentWindow = this.buttonAdd.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "UpdateCategory");
             
          });
         
          
    }
 }
        @FXML
    public void AddCategoryAction(ActionEvent e1) throws IOException {
        Window currentWindow = this.buttonAdd.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddCategory");
    }

    @FXML
    private void BackHomeAdmin(ActionEvent event)throws IOException  {
        Window currentWindow = this.buttonAdd.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");
    }

            
}
