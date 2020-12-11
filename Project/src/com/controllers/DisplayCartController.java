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
import com.services.ServiceCart;
import com.models.CommandLine;
import com.models.CommandList;
import com.models.WishList;
import com.services.ServiceCommandLine;
import com.services.ServiceCommandList;
import com.services.ServiceUser;
import com.services.ServiceWishList;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ihebf
 */
public class DisplayCartController implements Initializable {

    @FXML
    private JFXButton btnBack;
    @FXML
    private VBox booksList;
    @FXML
    private Label lbTotal;
    @FXML
    private JFXButton btnOrder;

    ServiceCart cart;
    int idUser;  // ConnectedUser

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        booksList.setSpacing(40);
        booksList.setPadding(new Insets(30, 30, 30, 30));
        cart = ServiceCart.getInstance();
        idUser = ServiceUser.getConnectedUser().getId();

        lbTotal.setText(cart.getTotal() + " DT");

        refresh(cart.getBooks());
        // TODO
    }

    public void refresh(Map<Book, Integer> books) {
        if (books.size() == 0) {
            btnOrder.setDisable(true);
        } else {
            btnOrder.setDisable(false);
        }
        booksList.getChildren().clear();
        if (books.isEmpty()) {
            Label empty = new Label("No books found in your cart !");
            empty.setAlignment(Pos.CENTER);
            empty.setPadding(new Insets(20, 0, 0, 0));
            empty.setStyle("-fx-font-size:22");
            empty.setMinWidth(370);
            booksList.getChildren().add(empty);
        } else {
            for (Map.Entry<Book, Integer> element : books.entrySet()) {
                Book book = element.getKey();
                int quantity = element.getValue();

                HBox row = new HBox();
                VBox details = new VBox();
                HBox actions = new HBox();
                HBox boxQuantity = new HBox();
                ImageView bookImage = new ImageView(new Image(book.getImageUrl()));
                Label lbtitle = new Label(book.getTitle());
                Label lbprice = new Label("Price : " + Float.toString(book.getPrix()) + " DT");

                Label lbquantity = new Label("Quantity : ");
                TextField tfQuantity = new TextField(Integer.toString(quantity));

                tfQuantity.setStyle("-fx-font-size:14");

                Image imageDelete = new Image("/com/img/icons/delete.png");
                ImageView delete = new ImageView();
                delete.setPickOnBounds(true);
                delete.setImage(imageDelete);
                delete.setFitWidth(50);
                delete.setFitHeight(50);

                //tfQuantity.setMinSize(10,10);
                tfQuantity.setPrefWidth(50);
                tfQuantity.setAlignment(Pos.CENTER);
                //tfQuantity.setPrefWidth();

                tfQuantity.setPrefHeight(20);

                tfQuantity.setOnKeyReleased((event) -> {
                    if (tfQuantity.getText().equals("")) {
                        cart.getBooks().replace(book, 1);
                    } else {
                        cart.getBooks().replace(book, Integer.parseInt(tfQuantity.getText()));
                    }
                    lbTotal.setText(Float.toString(cart.getTotal()) + " DT");
                });

                boxQuantity.getChildren().add(lbquantity);
                boxQuantity.getChildren().add(tfQuantity);
                actions.getChildren().add(delete);
                actions.setAlignment(Pos.CENTER_RIGHT);
                tfQuantity.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {
                        if (!newValue.matches("\\d*")) {
                            tfQuantity.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }

                });
                delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Delete the book " + book.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {

                            cart.getBooks().remove(book);
                            alert = new Alert(Alert.AlertType.INFORMATION, "Book deleted successfully from your cart!", ButtonType.OK);
                            alert.showAndWait();
                            lbTotal.setText(Float.toString(cart.getTotal()) + " DT");

                            refresh(cart.getBooks());

                        }

                    }
                });

                lbtitle.setAlignment(Pos.CENTER_LEFT);
                lbprice.setAlignment(Pos.CENTER_LEFT);
                boxQuantity.setAlignment(Pos.CENTER_LEFT);

                row.setAlignment(Pos.CENTER_LEFT);

                lbtitle.setStyle("-fx-font-size:20");
                lbprice.setStyle("-fx-font-size:20");
                lbquantity.setStyle("-fx-font-size:20");
                row.setStyle("-fx-background-color:#e5e5e5;-fx-background-radius:10 10 10 10");

                bookImage.setFitHeight(120);
                bookImage.setFitWidth(100);
                lbtitle.setMinWidth(377);
                //lbtitle.setMinHeight(50);
                //lbprice.setMinHeight(50);
                //lbquantity.setMinHeight(50);
                row.setSpacing(70);
                row.setMinHeight(130);
                row.setMinWidth(750);
                row.setPadding(new Insets(30, 10, 30, 10));
                details.setSpacing(10);

                details.getChildren().add(lbtitle);
                details.getChildren().add(lbprice);
                details.getChildren().add(boxQuantity);
                row.getChildren().add(bookImage);
                row.getChildren().add(details);
                row.getChildren().add(actions);
                booksList.getChildren().add(row);
            }
        }
    }

    @FXML
    public void order() {

        ServiceCommandLine scline = ServiceCommandLine.getInstance();
        ServiceCommandList sclist = ServiceCommandList.getInstance();
        CommandList cmd = new CommandList();
        cmd.setIdUser(idUser);
        cmd.setStatus("En cours");
        cmd.setTotalprice(cart.getTotal());
        try {
            int idCmdList = sclist.addCommandList(cmd);

            for (Map.Entry<Book, Integer> element : cart.getBooks().entrySet()) {
                Book book = element.getKey();
                int quantity = element.getValue();
                CommandLine cmdLine = new CommandLine();
                cmdLine.setIdBook(book.getId());
                cmdLine.setIdCommandList(idCmdList);
                cmdLine.setQuantity(quantity);
                scline.addCommandLine(cmdLine);
                cart.getBooks().clear();
                refresh(cart.getBooks());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void back(ActionEvent event) {
        Window currentWindow = this.btnBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "profile");

    }

}
