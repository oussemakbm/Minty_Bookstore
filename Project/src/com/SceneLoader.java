/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.controllers.BookDetailsController;
import com.models.Book;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author DellXPS
 */
public class SceneLoader {

    private static SceneLoader INSTANCE;
    
    private int bookId;

    private SceneLoader() {

    }

    public Parent GetParent(String fileName) {
        Parent view = null;
        try {
            String path = "views/" + fileName.concat(".fxml");
            System.out.println(path);
            return FXMLLoader.load(getClass().getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
    
    public void setBookId(int id) {
        this.bookId = id;
    }
    
    public int getSelectedBookId() {
        return this.bookId;
    }

    public VBox getDrawerContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("views/drawerContent.fxml"));
    }
    public VBox getDrawerContentAdmin() throws IOException{
        return FXMLLoader.load(getClass().getResource("views/drawerContentAdmin.fxml"));
    }

    public void NavigateTo(Window currentWindow, String viewName) {
//        1 - Hide current window
//        2 - create new Stage 
//        3 - Create a new Parent Object
//        4 - Create new Scene and inject Parent Object in it
//        5 - Stage.setScene(Parent Object Created)  
//        6 - Show Stage

        currentWindow.hide();
        Stage newStage = new Stage();
        Parent root = this.GetParent(viewName);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    public static SceneLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SceneLoader();
        }
        return INSTANCE;
    }
}
