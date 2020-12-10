/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.models.Book;
import com.models.WishList;
import com.services.ServiceWishList;
import java.io.File;
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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ihebf
 */
public class DisplayWishListsController implements Initializable {

    @FXML
    private JFXListView wishlists;
    @FXML
    private VBox wishlistsbooks;
    @FXML
    private Label title;
    @FXML
    private Label empty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceWishList sw = ServiceWishList.getInstance();
        /*  WishList ws=new WishList();
        WishList ws3=new WishList();
        WishList ws4=new WishList();
        Book b=new Book();
        Book b2=new Book();
        Book b3=new Book();
        
        ws.setId(1);
        ws3.setId(3);
        ws4.setId(5);
        
        b.setId(4);
        b2.setId(2);
        b3.setId(1);
        
        try {
            sw.addBookToWishList(ws, b);
            sw.addBookToWishList(ws, b2);
            sw.addBookToWishList(ws, b3);
            
            sw.addBookToWishList(ws3, b);
            sw.addBookToWishList(ws3, b3);
            sw.addBookToWishList(ws4, b2);
            sw.addBookToWishList(ws4, b3);

        } catch (SQLException ex) {
            Logger.getLogger(DisplayWishListsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        wishlists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WishList>() {
            @Override
            public void changed(ObservableValue<? extends WishList> observable, WishList oldValue, WishList newValue) {
                wishlistsbooks.getChildren().clear();
                title.setText("Books in " + newValue);
                ArrayList<Book> list;
                try {
                    list = sw.getBooksInWishList(newValue);
                    if (list.size() == 0) {
                        empty.setVisible(true);
                        title.setVisible(false);
                    } else {
                        title.setVisible(true);
                        empty.setVisible(false);

                        for (Book book : list) {
                            wishlistsbooks.getChildren().add(convert(book, newValue));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DisplayWishListsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        try {
            ArrayList<WishList> list = sw.getUserWishLists(1);  // User ID = 1
            for (WishList wishList : list) {
                wishlists.getItems().add(wishList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayWishListsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HBox convert(Book book, WishList wishlist) {
        HBox box = new HBox();
        ImageView imageview = new ImageView();

        // Quelques modifications seront apportées à l'importation des images
        //File file = new File("C:\\Users\\ihebf\\OneDrive\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\Exemplebook.png");
        //File fileBin = new File("C:\\Users\\ihebf\\OneDrive\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\bin.png");
        //********************************************************************
        Image image = new Image("/com/img/Exemplebook.png");
        imageview.setImage(image);
        imageview.setFitHeight(100);
        imageview.setFitWidth(100);

        Label title = new Label(book.getTitle());
        title.setPadding(new Insets(0, 30, 0, 30));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        Label prix = new Label(Float.toString(book.getPrix()));
        prix.setPadding(new Insets(0, 30, 0, 30));

        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        ImageView bin = new ImageView(new Image("/com/img/icons/delete.png"));
        bin.setFitHeight(50);
        bin.setFitWidth(50);
        bin.setOnMouseClicked((MouseEvent e) -> {
            ServiceWishList sw = ServiceWishList.getInstance();
            try {
                sw.deleteBookFromWishList(wishlist, book);
                wishlistsbooks.getChildren().clear();
                title.setText("Books in " + wishlist);

                ArrayList<Book> list = sw.getBooksInWishList(wishlist);
                if (list.size() == 0) {
                    title.setVisible(false);
                    empty.setVisible(true);
                } else {
                    title.setVisible(true);
                    empty.setVisible(false);

                    for (Book b : list) {
                        wishlistsbooks.getChildren().add(convert(b, wishlist));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DisplayWishListsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Label rating = new Label(Float.toString(book.getRating()));
        rating.setPadding(new Insets(0, 30, 0, 30));
        box.getChildren().add(imageview);

        box.getChildren().add(title);
        box.getChildren().add(spacer);
        box.getChildren().add(bin);

        box.setPadding(new Insets(30, 0, 30, 0));
        return box;
    }

}
