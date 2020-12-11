/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.models.Book;
import com.models.WishList;
import com.services.ServiceBook;
import com.services.ServiceUser;
import com.services.ServiceWishList;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
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
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ihebf
 */
public class DisplayWishListsController implements Initializable {

    @FXML
    private VBox wishlists;
    @FXML
    private Label title;
    @FXML
    private VBox booksList;

    List<WishList> allWishLists;
    List<Book> allBook;
    int idUser;
    WishList selectedWishList;
    @FXML
    private ImageView backArrow;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXButton btnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceWishList sw = ServiceWishList.getInstance();
        idUser=ServiceUser.getConnectedUser().getId();
        try {
            allWishLists = sw.getUserWishLists(idUser);
            System.out.println(allWishLists);
            refreshWishlists(allWishLists);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayWishListsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*   WishList ws=new WishList();
        WishList ws3=new WishList();
        WishList ws4=new WishList();
        Book b=new Book();
        Book b2=new Book();
        Book b3=new Book();
        
        ws.setId(46);
        //ws3.setId(3);
        //ws4.setId(5);
        
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

    }

    public void refreshWishlists(List<WishList> list) {
        wishlists.getChildren().clear();
        boolean odd = true;

        if (list.size() == 0) {
            Label empty = new Label("You have no wishlists");
            empty.setStyle("-fx-font-size:20");
            wishlists.getChildren().add(empty);
        } else {
            for (WishList wishlist : list) {
                HBox box = new HBox();
                box.setMinHeight(50);
                Label name = new Label(wishlist.getName());
                name.setStyle("-fx-font-size:18");
                ImageView delete = new ImageView(new Image("/com/img/icons/delete.png"));
                delete.setFitWidth(32);
                delete.setFitHeight(32);
                delete.setPickOnBounds(true);
                box.setOnMouseClicked((event) -> {
                    try {
                        selectedWishList = wishlist;
                        title.setText(wishlist.getName());
                        allBook = ServiceWishList.getInstance().getBooksInWishList(wishlist);
                        refreshBooks(allBook);
                    } catch (SQLException ex) {
                        Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                delete.setOnMouseClicked((event) -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Delete the wishlist " + wishlist.getName() + " ?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        try {
                            ServiceWishList.getInstance().deleteWishList(wishlist.getId());
                            allWishLists.remove(wishlist);
                            alert = new Alert(Alert.AlertType.INFORMATION, "WishList deleted successfully !", ButtonType.OK);
                            alert.showAndWait();
                            refreshWishlists(allWishLists);
                            title.setText("Choose a wishlist");
                            refreshBooks(new ArrayList<Book>());
                        } catch (SQLException ex) {
                            Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                
                if (odd) {
                    box.setStyle("-fx-background-color:#c9e265");
                } else {
                    box.setStyle("-fx-background-color: #fff");
                }
                odd = !odd;
                name.setMinWidth(160);
                name.setAlignment(Pos.CENTER);
                box.setAlignment(Pos.CENTER);

                box.setSpacing(40);
                box.getChildren().add(name);
                box.getChildren().add(delete);
                wishlists.getChildren().add(box);

            }

        }

    }

    public void refreshBooks(List<Book> list) {
        booksList.getChildren().clear();
        booksList.setSpacing(30);
        booksList.setPadding(new Insets(20, 20, 20, 20));
        if (list.size() == 0) {
            Label empty = new Label("You have no books in this wishlist");
            empty.setMinWidth(700);
            empty.setMinHeight(520);
            empty.setAlignment(Pos.CENTER);
            // empty.setPadding(new Insets(100,100,100,100));
            empty.setStyle("-fx-font-size:30");
            booksList.getChildren().add(empty);
        } else {
            for (Book book : list) {
                HBox row = new HBox();
                HBox box = new HBox();
                box.setStyle("-fx-background-color:white;-fx-background-radius:10 10 10 10");
                box.setPadding(new Insets(20, 20, 20, 20));

                VBox details = new VBox();
                HBox actions = new HBox();
                // ImageView bookImage = new ImageView(new Image(book.getImageUrl()));
                ImageView bookImage = new ImageView(new Image("/com/img/Exemplebook.png"));
                Label lbtitle = new Label(book.getTitle());

                HBox title = new HBox();
                Label t = new Label("Title : ");
                t.setStyle("-fx-font-weight:bold;-fx-font-size:20");
                title.getChildren().add(t);
                t.setMinWidth(60);

                title.getChildren().add(lbtitle);

                HBox price = new HBox();
                Label lbprice = new Label(Float.toString(book.getPrix()) + " DT");
                Label p = new Label("Price : ");
                p.setStyle("-fx-font-weight:bold;-fx-font-size:20");
                price.getChildren().add(p);
                price.getChildren().add(lbprice);

                Image imageDelete = new Image("/com/img/icons/delete.png");
                ImageView delete = new ImageView();
                delete.setPickOnBounds(true);
                delete.setImage(imageDelete);
                delete.setFitWidth(50);
                delete.setFitHeight(50);

                actions.getChildren().add(delete);
                actions.setAlignment(Pos.CENTER_RIGHT);

                delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Delete the book " + book.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            allBook.remove(book);
                            alert = new Alert(Alert.AlertType.INFORMATION, "Book deleted successfully from your cart!", ButtonType.OK);
                            alert.showAndWait();

                            refreshBooks(allBook);

                        }

                    }
                });

                lbtitle.setAlignment(Pos.CENTER_LEFT);
                lbprice.setAlignment(Pos.CENTER_LEFT);

                row.setAlignment(Pos.CENTER_LEFT);

                lbtitle.setStyle("-fx-font-size:20");
                lbprice.setStyle("-fx-font-size:20");
                row.setStyle("-fx-background-color:#e5e5e5;-fx-background-radius:10 10 10 10");

                bookImage.setFitHeight(120);
                bookImage.setFitWidth(100);
                lbtitle.setMinWidth(377);
                //lbtitle.setMinHeight(50);
                //lbprice.setMinHeight(50);
                //lbquantity.setMinHeight(50);

                box.getChildren().add(details);
                box.getChildren().add(actions);

                row.setSpacing(40);
                row.setMinHeight(130);
                row.setMinWidth(700);
                row.setPadding(new Insets(30, 10, 30, 10));

                row.setOnMouseEntered((event) -> {
                    row.setStyle("-fx-background-color:#e5e5e5;-fx-background-radius:10 10 10 10;-fx-effect: dropshadow(three-pass-box, green, 10, 0, 0, 0);");
                });
                row.setOnMouseExited((event) -> {
                    row.setStyle("-fx-background-color:#e5e5e5;-fx-background-radius:10 10 10 10");
                });

                details.setSpacing(10);
                details.getChildren().add(title);
                details.getChildren().add(price);

                row.getChildren().add(bookImage);

                row.getChildren().add(box);
                booksList.getChildren().add(row);
            }
        }
    }

    @FXML
    public void back(MouseEvent event) {
        Window currentWindow = this.backArrow.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "profile");

    }

    public void add(ActionEvent event) {
        try {

            if (tfName.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wishlist name cannot be empty !", ButtonType.OK);
                alert.showAndWait();
            } else if (ServiceWishList.getInstance().wishListExist(idUser, tfName.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You already have a wishlist with this name !", ButtonType.OK);
                alert.showAndWait();
            } else {
                WishList w = new WishList();
                w.setName(tfName.getText());
                w.setIdUser(idUser);
                ServiceWishList.getInstance().addWishList(w);
                allWishLists.add(w);
                refreshWishlists(allWishLists);
                tfName.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayWishListsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
