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
import com.services.ServiceAuthor;
import com.services.ServiceBook;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author DellXPS
 */
public class BookDetailsController implements Initializable {

    private int bookId;


    @FXML
    private ImageView bookCover;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Text descriptionTxt;

    @FXML
    private Text priceTxt;

    @FXML
    private Text titleTxt;

    @FXML
    private Text nbpageTxt;

    @FXML
    private Text authorTxt;

    HamburgerBackArrowBasicTransition burgerTask;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDrawerContent();
        initBookDetails();
//        try {
//            System.out.println("Setting id " + SceneLoader.getInstance().getSelectedBookId() + " to get book by id method ");
//            Book b = ServiceBook.getInstance().getBook(SceneLoader.getInstance().getSelectedBookId());
//            System.out.println("Selected Book : " + b.toString());
//            
//        } catch (Exception e) {
//        }
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

    private void initBookDetails() {
        ServiceBook sb = ServiceBook.getInstance();
        ServiceAuthor sa = ServiceAuthor.getInstance();
        try {
            Book b = sb.getBook(SceneLoader.getInstance().getSelectedBookId());
            this.bookCover.setImage(new Image(b.getImageUrl()));
            this.titleTxt.setText(b.getTitle());
            this.descriptionTxt.setText(b.getDescription());
            this.priceTxt.setText(Float.toString(b.getPrix()) + " DT");
            this.nbpageTxt.setText(Integer.toString(b.getNbrPages()));
            this.authorTxt.setText(sa.getAuthor(b.getIdAuthor()).getName());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initDrawerContent() {
        try {
            VBox box = SceneLoader.getInstance().getDrawerContent();
            this.drawer.setSidePane(box);
            burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> this.burgerClick(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
