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
import com.models.AuthorPrefer;
import com.services.ServicePreferAuthor;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
public class PrefredAuthorController implements Initializable {

    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXTextField textFieldSearch;
    @FXML
    private VBox list;
    List<Author> preferesAuthors;
    int idUser = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePreferAuthor spa = ServicePreferAuthor.getInstance();
        try {
            preferesAuthors = spa.getAuthorsPreferUser(idUser);
            refresh(preferesAuthors);
        } catch (SQLException ex) {
            Logger.getLogger(ChooseWishListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void back(ActionEvent event) {
        Window currentWindow = this.btnBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "home");
    }

    @FXML
    private void search() {
        refresh(searchPreferAuthors(textFieldSearch.getText()));
    }

    public void refresh(List<Author> authors) {
        list.getChildren().clear();
        if (authors.isEmpty()) {
            Label empty = new Label("No authors found");
            empty.setAlignment(Pos.CENTER);
            empty.setPadding(new Insets(20, 0, 0, 0));
            empty.setStyle("-fx-font-size:22");
            empty.setMinWidth(370);
            list.getChildren().add(empty);
        } else {
            boolean odd = true;
            for (Author author : authors) {
                HBox box = new HBox();

                Label name = new Label(author.getName());
                //JFXButton update=new JFXButton("*");
                //JFXButton delete=new JFXButton("-");
                name.setAlignment(Pos.CENTER);
                name.setStyle("-fx-font-size:14");

                name.setMinWidth(477);
                name.setMinHeight(50);
                Image imageDelete = new Image("/com/img/icons/delete.png");
                ImageView delete = new ImageView();
                delete.setPickOnBounds(true);
                delete.setImage(imageDelete);
                delete.setFitWidth(32);
                delete.setFitHeight(32);

                ServicePreferAuthor spa = ServicePreferAuthor.getInstance();

                delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Delete the author " + author.getName() + " ?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            try {
                                spa.deleteAuthorPrefer(author.getId(), idUser);
                                preferesAuthors.remove(author);
                                alert = new Alert(Alert.AlertType.INFORMATION, "Author deleted successfully !", ButtonType.OK);
                                alert.showAndWait();
                                search();
                            } catch (SQLException ex) {
                                Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }
                });
                if (odd) {
                    name.setStyle("-fx-background-color:#fff");
                } else {
                    name.setStyle("-fx-background-color: #d9d9d9");
                }
                odd = !odd;
                    box.getChildren().add(name);
                    box.getChildren().add(delete);
                list.getChildren().add(box);

            }

        }
    }

    public List<Author> searchPreferAuthors(String searchText) {
        return preferesAuthors.stream().filter((author) -> (author.getName().toUpperCase().indexOf(searchText.toUpperCase()) != -1)).collect(Collectors.toList());
    }

}
