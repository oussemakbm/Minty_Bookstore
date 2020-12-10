/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.models.Book;
import com.services.ServiceBook;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author DellXPS
 */
public class HomeController implements Initializable {

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    HamburgerBackArrowBasicTransition burgerTask;

    ArrayList<Book> bookList;

    @FXML
    AnchorPane booksContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = SceneLoader.getInstance().getDrawerContent();
            this.drawer.setSidePane(box);
            burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> this.burgerClick(e));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bookList = ServiceBook.getInstance().getBooks();
            if (bookList.size() > 0) {
                displayBooks(bookList);
            } else {
                Label nobooksFoundTxt = new Label("No books found !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void burgerClick(MouseEvent event) {
        burgerTask.setRate(burgerTask.getRate() * -1);
        burgerTask.play();
        if (this.drawer.isShown()) {
            this.drawer.close();
        } else {
            this.drawer.open();
        }
    }

    private void displayBooks(ArrayList<Book> bookList) {
        GridPane root = new GridPane();
//        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(10);
        root.setVgap(30);
        int cols = 2, colCnt = 0, rowCnt = 0;
        for (int i = 0; i < bookList.size(); i++) {
//            Construct and element 
            root.add(this.getButtonFromBook(bookList.get(i)), colCnt, rowCnt);
            colCnt++;
            if (colCnt > cols) {
                rowCnt++;
                colCnt = 0;
            }
        }
        booksContainer.setPadding(new Insets(0, 0, 0, 0));
        booksContainer.getChildren().add(root);
    }

    private Button getButtonFromBook(Book b) {
//        Creating BookButton Element START
        //Creating a graphic (image)
        Image img = new Image(b.getImageUrl());
        ImageView view = new ImageView(img);
        view.setFitHeight(100);
        view.setFitWidth(100);
        view.setPreserveRatio(true);
        //Creating a Button
        Button button = new Button();
        //Setting the location of the button
        button.setTranslateX(120);
        
        //Setting the size of the button
        button.setPrefSize(120, 120);
        //Setting a graphic to the button
        button.setGraphic(view);
        button.setText(b.getTitle());
        button.setBackground(Background.EMPTY);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                System.out.println("Setting BookId: "+ b.getId() + "To Scene Loader setBookId method");
                SceneLoader.getInstance().setBookId(b.getId());
                SceneLoader.getInstance().NavigateTo(this.drawer.getScene().getWindow(), "bookDetails");
                
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
//        Creating BookButton Element END
        return button;
    }

}
