/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.models.Book;
import com.models.Comment;
import com.models.Interaction;
import com.services.ServiceAuthor;
import com.services.ServiceBook;
import com.services.ServiceCart;
import com.services.ServiceComment;
import com.services.ServiceCommentProfanity;
import com.services.ServiceInteraction;
import com.services.ServiceUser;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Rating;

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

    @FXML
    private ImageView likeIcon;

    @FXML
    private JFXListView<Label> commentsList;

    @FXML
    private JFXButton submitRatingBtn;

    HamburgerBackArrowBasicTransition burgerTask;

    @FXML
    private Rating rating;

    @FXML
    private JFXTextArea commentBody;
    @FXML
    private JFXButton likeBtn;
    @FXML
    private JFXButton pickWishlist;
    @FXML
    private JFXButton addToCart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initDrawerContent();
        this.initBookDetails();
        this.displayComments();
        System.out.println("User liked book :" + userLikedBook());
        String likeIconUrl = userLikedBook() ? "http://localhost/bookstore/icons/heart-solid_liked.png" : "http://localhost/bookstore/icons/heart-solid_unliked.png";
        likeIcon.setImage(new Image(likeIconUrl));

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

    private boolean userLikedBook() {
        int bookId = SceneLoader.getInstance().getSelectedBookId();
        int userId = ServiceUser.getConnectedUser().getId();

        try {
            ArrayList<Interaction> bookInteracations = ServiceInteraction.getInstance().getBookInteractions(bookId);
            for (Interaction i : bookInteracations) {
                if (i.getIdUser() == userId && i.getLiked() == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    void onLike(ActionEvent event) {
        int bookId = SceneLoader.getInstance().getSelectedBookId();
        int userId = ServiceUser.getConnectedUser().getId();
        String likeIconUrl = userLikedBook() ? "/com/img/icons/heart-solid_unliked.png" : "/com/img/icons/heart-solid_liked.png";
        likeIcon.setImage(new Image(likeIconUrl));
        if (userLikedBook()) {

            Interaction i = new Interaction(userId, bookId, 3, 0);
            i.setId(1);
            try {
                ServiceInteraction.getInstance().updateInteraction(i);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Interaction i = new Interaction(userId, bookId, 3, 1);
            i.setId(1);
            try {
                ServiceInteraction.getInstance().addInteraction(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleRatingDone(ActionEvent event) {
        double ratingValue = rating.getRating();
        System.out.println("Rating value: " + ratingValue);
    }

    @FXML
    void submitComment(ActionEvent event) {
        int userId = ServiceUser.getConnectedUser().getId();
        int bookId = SceneLoader.getInstance().getSelectedBookId();
        String body = commentBody.getText();

        try {
            String result = ServiceCommentProfanity.getInstance().getCleanComment(body);
            
            if (result != "REQUEST_ERROR") {
                Comment comment = new Comment(userId, bookId, result);
                ServiceComment.getInstance().addComment(comment, userId, bookId);
                this.commentBody.setText("");
                this.displayComments();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayComments() {

        int bookId = SceneLoader.getInstance().getSelectedBookId();

        ArrayList<Comment> comments = ServiceComment.getInstance().getComments(bookId);

        comments.forEach(comment -> {
            commentsList.getItems().add(new Label(comment.getBody()));
        });

    }

    @FXML
    private void handleRatingDone(DragEvent event) {
    }

    @FXML
    private void handleRatingDone(MouseEvent event) {
    }

    @FXML
    private void displayWishLists(ActionEvent event) {
         Window currentWindow = this.pickWishlist.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "ChooseWishList");
    }

    @FXML
    private void addBookToCart(ActionEvent event) {
        try {
            Book b=ServiceBook.getInstance().getBook(SceneLoader.getInstance().getSelectedBookId());
            ServiceCart.getInstance().getBooks().put(b, 1);
            addToCart.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(BookDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
