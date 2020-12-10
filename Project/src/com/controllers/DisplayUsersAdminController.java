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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private VBox vBoxUsersList;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXTextField textFieldSearch;
    List<User> allUsers;
    @FXML
    private JFXButton buttonBack;
    @FXML
    private Pagination pagination;

    private final int MAX_ELEMENT_PER_PAGE = 10;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser su = ServiceUser.getInstance();

        allUsers = new ArrayList();
        try {
            allUsers = su.getUsers();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        pagination.setMaxPageIndicatorCount(5);

        pagination.setPageCount(calculPageCount(allUsers.size()));
        pagination.setPageFactory((pageIndex) -> {
            return refresh(searchUsers(textFieldSearch.getText()), pageIndex);
        });

        // refresh(users);
        // TODO
        //OptionalDouble average = employees.stream().filter((e)->{return (e.getName().charAt(0)=='s');}).mapToInt((e)->{return e.getSalary();}).average();
    }

    @FXML
    void AddUser(ActionEvent event) {
        Window currentWindow = this.buttonAdd.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "AddUserAdmin");
    }

    @FXML
    void BackAdmin(ActionEvent event) {
        Window currentWindow = this.buttonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "homeAdmin");

    }

    public int calculPageCount(int size) {
        if (size % MAX_ELEMENT_PER_PAGE == 0) {
            return (size / MAX_ELEMENT_PER_PAGE);
        } else {
            return (size / MAX_ELEMENT_PER_PAGE) + 1;
        }
    }

    @FXML
    public void search() {

        pagination.setPageCount(calculPageCount(searchUsers(textFieldSearch.getText()).size()));
        pagination.setPageFactory((pageIndex) -> {
            return refresh(searchUsers(textFieldSearch.getText()), pageIndex);
        });

    }

    public List<User> searchUsers(String searchText) {
        List<User> result = new ArrayList();

        for (User user : allUsers) {
            if ((user.getName().toUpperCase().indexOf(searchText.toUpperCase()) != -1)
                    || (user.getEmail().toUpperCase().indexOf(searchText.toUpperCase()) != -1)
                    || (user.getRole().toUpperCase().indexOf(searchText.toUpperCase()) != -1)) {
                result.add(user);
            }
        }
        return result;
    }

    public VBox refresh(List<User> users, int pageIndex) {
        VBox vBoxUsersList = new VBox();
        boolean odd = true;

        vBoxUsersList.getChildren().clear();
        int i = 1;
        for (User user : users) {
            if ((i <= (pageIndex + 1) * MAX_ELEMENT_PER_PAGE) && (i > ((pageIndex + 1) * MAX_ELEMENT_PER_PAGE - MAX_ELEMENT_PER_PAGE ))) {
                HBox box = new HBox();
                Label name = new Label(user.getName());
                Label email = new Label(user.getEmail());
                Label role = new Label(user.getRole());
                HBox actions = new HBox();
                //File fileSettings = new File("C:\\Users\\ihebf\\OneDrive\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\icons\\Settings.png");
                Image image = new Image("/com/img/icons/Settings.png");
                ImageView imageview = new ImageView();
                imageview.setPickOnBounds(true);
                imageview.setImage(image);
                imageview.setFitWidth(40);
                imageview.setFitHeight(40);

                //File fileDelete = new File("C:\\Users\\ihebf\\OneDrive\\Documents\\NetBeansProjects\\Minty_Bookstore\\Project\\src\\com\\img\\icons\\delete.png");
                Image imageDelete = new Image("/com/img/icons/delete.png");
                ImageView delete = new ImageView();
                delete.setPickOnBounds(true);
                delete.setImage(imageDelete);
                delete.setFitWidth(32);
                delete.setFitHeight(32);

                ServiceUser su = ServiceUser.getInstance();

                delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Alert alert = new Alert(AlertType.WARNING, "Delete the user " + user.getName() + " ?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            try {
                                su.deleteUser(user.getId());
                                allUsers.remove(user);

                                alert = new Alert(Alert.AlertType.INFORMATION, "User deleted successfully !", ButtonType.OK);
                                alert.showAndWait();
                                search();
                            } catch (SQLException ex) {
                                Logger.getLogger(DisplayUsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }
                });
                imageview.setOnMouseClicked((MouseEvent event) -> {
                    ServiceUser.setuserToUpdate(user);
                    Window currentWindow = this.buttonAdd.getScene().getWindow();
                    SceneLoader.getInstance().NavigateTo(currentWindow, "UpdateUserAdmin");

                });

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

                if (odd) {
                    box.setStyle("-fx-background-color:#fff");
                } else {
                    box.setStyle("-fx-background-color: #d9d9d9");
                }
                odd = !odd;

                actions.getChildren().add(imageview);
                actions.getChildren().add(delete);
                box.getChildren().add(name);
                box.getChildren().add(email);
                box.getChildren().add(role);
                box.getChildren().add(actions);

                vBoxUsersList.getChildren().add(box);
            }
            i++;
        }
        return vBoxUsersList;
    }

    public void changePage(int pageIndex) {

    }

}
