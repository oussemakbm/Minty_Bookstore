/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.controllers.BookDetailsController;
import com.models.Book;
import com.models.CommandLine;
import com.models.CommandList;
import java.io.IOException;
import java.util.ArrayList;
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
    
    private int langueId;
    
    private int serieId;
    
    private CommandList cl;

    private SceneLoader() {}
    
    public void setCommande(){
        cl = new CommandList(1, "En Cours", 0 , 1);
        cl.addLines(new CommandLine(1, 1, 1, 4));
        cl.addLines(new CommandLine(1, 1, 1, 5));
        cl.addLines(new CommandLine(1, 1, 1, 6));
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
        this.langueId = id;
    }
    
    public int getSelectedBookId() {
        return this.langueId;
    }
    
    public void setLangueId(int id) {
        this.bookId = id;
    }
    
    public int getSelectedLangueId() {
        return this.bookId;
    }
    
    public void setSerieId(int id) {
        this.serieId = id;
    }
    
    public int getSelectedSerieId() {
        return this.serieId;
    }
    
    public void setList(CommandList x) {
        this.cl = x;
    }
    
    public CommandList getSelectedList() {
        return this.cl;
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
