/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.Book;
import com.models.User;
import com.models.WishList;
import com.services.ServiceBook;
import com.services.ServiceUser;
import com.services.ServiceWishList;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ihebf
 */
public class ChooseWishListController implements Initializable {

    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private VBox list;

    List<WishList> allWishlists;
    int idUser; // User used for testing
    @FXML
    private JFXTextField textFieldSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceWishList sw = ServiceWishList.getInstance();
        idUser = ServiceUser.getConnectedUser().getId();
        try {
            allWishlists = sw.getUserWishLists(idUser);
            refresh(allWishlists);
        } catch (SQLException ex) {
            Logger.getLogger(ChooseWishListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }

    @FXML
    public void search() {

        refresh(searchWishlists(textFieldSearch.getText()));

    }

    public List<WishList> searchWishlists(String searchText) {
        /* List<User> result = new ArrayList();
        for (User user : allUsers) {
            if ((user.getName().toUpperCase().indexOf(searchText.toUpperCase()) != -1)
                    || (user.getEmail().toUpperCase().indexOf(searchText.toUpperCase()) != -1)
                    || (user.getRole().toUpperCase().indexOf(searchText.toUpperCase()) != -1)) {
                result.add(user);
            }
        }*/
        return allWishlists.stream().filter((wishlist) -> (wishlist.getName().toUpperCase().indexOf(searchText.toUpperCase()) != -1)).collect(Collectors.toList());
    }

    public void refresh(List<WishList> wishLists) {
        list.getChildren().clear();
        if (wishLists.isEmpty()) {
            Label empty = new Label("No wishlists found");
            empty.setAlignment(Pos.CENTER);
            empty.setPadding(new Insets(20, 0, 0, 0));
            empty.setStyle("-fx-font-size:22");
            empty.setMinWidth(370);
            list.getChildren().add(empty);
        } else {
            boolean odd = true;
            for (WishList wishList : wishLists) {
                Label name = new Label(wishList.getName());
                //JFXButton update=new JFXButton("*");
                //JFXButton delete=new JFXButton("-");
                name.setAlignment(Pos.CENTER);
                name.setStyle("-fx-font-size:14");

                name.setMinWidth(377);
                name.setMinHeight(50);
                name.setOnMouseClicked((MouseEvent e) -> {
                    try {
                        ServiceWishList.setSelectedWishList(wishList);
                        Book b = ServiceBook.getInstance().getBook(SceneLoader.getInstance().getSelectedBookId());
                        if (ServiceWishList.getInstance().checkBookInWishList(wishList, b)) {
                            ServiceWishList.getInstance().addBookToWishList(wishList, b);
                            Window currentWindow = this.btnBack.getScene().getWindow();
                            SceneLoader.getInstance().NavigateTo(currentWindow, "bookDetails");
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Book already exists ", ButtonType.OK);
                            alert.showAndWait();
                        }
                    } catch (SQLException e1) {
                    }

                });
                if (odd) {
                    name.setStyle("-fx-background-color:#fff");
                } else {
                    name.setStyle("-fx-background-color: #d9d9d9");
                }
                odd = !odd;
                list.getChildren().add(name);
            }
        }
    }

    @FXML
    void addWishList(ActionEvent event) {
        WishList w = new WishList();
        ServiceWishList sw = ServiceWishList.getInstance();
        w.setIdUser(idUser);
        w.setName(textFieldSearch.getText());
        try {
            if (sw.wishListExist(idUser, w.getName())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You already have a wishlist with this name !", ButtonType.OK);
                alert.showAndWait();
            } else {
                sw.addWishList(w);
                allWishlists.add(w);
                search();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChooseWishListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void back(ActionEvent event) {
        Window currentWindow = this.btnBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "bookDetails");

    }

}
