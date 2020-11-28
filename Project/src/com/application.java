/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.models.Author;
import com.models.AuthorPrefer;
import com.models.Book;
import com.models.Category;
import com.models.CommandLine;
import com.models.CommandList;
import com.models.Comment;
import com.models.Interaction;
import com.models.User;
import com.models.WishList;
import com.services.ServiceInteraction;
import com.services.ServiceAuthor;
import com.services.ServiceBook;
import com.services.ServiceCategory;
import com.services.ServiceCommandLine;
import com.services.ServiceCommandList;
import com.services.ServiceComment;
import com.services.ServicePreferAuthor;
import com.services.ServiceUser;
import com.services.ServiceWishList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DellXPS
 */
public class application extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
        Parent root = FXMLLoader.load(getClass().getResource("views/home.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       launch(args);
    }

}
