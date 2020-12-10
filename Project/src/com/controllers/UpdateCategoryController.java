/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.models.Category;
import com.services.ServiceCategory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ali
 */
public class UpdateCategoryController implements Initializable {

    @FXML
    private Button btnModify;
    @FXML
    private ListView lvCategories;
    @FXML
    private TextField tfCategoryName;
    @FXML
    private JFXButton buttonBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ServiceCategory sc = ServiceCategory.getInstance();

        try {
            ArrayList<Category> categories = sc.getCategories();
            lvCategories.getItems().clear();
            for (Category category : categories) {
                lvCategories.getItems().add(category);
            }
            lvCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
                @Override
                public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {

                    tfCategoryName.setText(newValue.getName());

                    btnModify.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            lvCategories.getItems().clear();
                            try {

                                Category c = new Category();
                                c.setId(newValue.getId());
                                c.setName(tfCategoryName.getText());
                                sc.updateCategory(c);

                                for (Category category : sc.getCategories()) {
                                    lvCategories.getItems().add(category);
                                }

                            } catch (SQLException ex) {
                                // Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (java.lang.NullPointerException e) {
                                e.printStackTrace();
                            }
                        }

                    });
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void BackCategoryManager(ActionEvent e1) throws IOException {
        Window currentWindow = this.buttonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "CategoryManager");
    }

    


}
