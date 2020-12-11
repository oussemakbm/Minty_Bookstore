/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.SceneLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.models.User;
import com.services.ServiceUser;
import com.util.HttpPost;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author ihebf
 */
public class UpdateUserAdminController implements Initializable {

    @FXML
    private JFXTextField tfname;
    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXTextField tftel;
    @FXML
    private JFXTextField tfaddress;
    @FXML
    private JFXRadioButton rbadmin;
    @FXML
    private JFXRadioButton rbmember;
    @FXML
    private Label erreurName;
    @FXML
    private Label erreurEmail;
    @FXML
    private Label erreurPassword;
    @FXML
    private Label erreurTel;
    @FXML
    private Label erreurAddress;
    @FXML
    private Label lresultat;
    @FXML
    private JFXPasswordField tfpassword;
    @FXML
    private JFXButton reset;
    @FXML
    private JFXButton update;
    @FXML
    private ImageView avatar;
    @FXML
    private VBox container;
    @FXML
    private ImageView ButtonBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        rbadmin.setToggleGroup(group);
        rbadmin.setSelected(true);
        rbmember.setToggleGroup(group);
        User user=ServiceUser.getuserToUpdate();
        tfname.setText(user.getName());
        tfemail.setText(user.getEmail());
        tftel.setText(user.getNumTel());
        tfpassword.setText(user.getPassword());
        avatar.setImage(new Image(user.getProfilePicture()));
        if (user.getRole().equalsIgnoreCase("ADMIN")){
            rbadmin.setSelected(true);
            rbmember.setSelected(false);
        }else{
             rbadmin.setSelected(false);
            rbmember.setSelected(true);
        }
        tfaddress.setText(user.getAdresse());
        
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                erreurEmail.setText("");
                erreurName.setText("");
                erreurPassword.setText("");
                erreurTel.setText("");
                erreurAddress.setText("");
                
                User u=ServiceUser.getuserToUpdate();
                u.setName(tfname.getText());
                u.setEmail(tfemail.getText());
                u.setNumTel(tftel.getText());
                u.setPassword(tfpassword.getText());
                u.setAdresse(tfaddress.getText());
                
                if (rbadmin.isSelected()){
                    u.setRole("ADMIN");
                }else{
                    u.setRole("CLIENT");
                }
                ServiceUser su=ServiceUser.getInstance();

                String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                boolean formValid=true;
 
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tfemail.getText());
                if (!(matcher.matches())){
                    erreurEmail.setText("Invalid email format");
                    formValid=false;
                }
                if (tfname.getText().length()<7 || !tfname.getText().matches("\\D+")){
                    erreurName.setText("Name must have more than 7 characters");
                    formValid=false;
                }
                
                
                pattern = Pattern.compile("^\\d{10}$");
                matcher = pattern.matcher(tftel.getText());
                if (matcher.matches() || tftel.getText().length()!=8){
                    erreurTel.setText("Invalid phone number");
                    formValid=false;
                }
                if (tfpassword.getText().length()<10){
                    erreurPassword.setText("Password must have more than 10 characters");
                    formValid=false;
                }
               try {
                   if (formValid){
                    su.updateUser(u);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION, "User updated successfully !", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        Window currentWindow = update.getScene().getWindow();
                         SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayUsersAdmin");
                    }
                   }
                } catch (SQLException ex) {
                    lresultat.setText("Error");
                }
            }
        });
        
        
        
          reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfname.setText("");
                tfemail.setText("");
                tftel.setText("");
                tfpassword.setText("");
                tfaddress.setText("");
            }
        });
      
       
    } 
    
     @FXML
    void BackAdmin(MouseEvent event) {
        Window currentWindow = this.ButtonBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayUsersAdmin");

    }
    
    @FXML
    void onClickUpload(MouseEvent event) throws IOException{
        User currentUser = ServiceUser.getuserToUpdate();
        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        File selectedFile = fc.getSelectedFile();
        String path = selectedFile.getAbsolutePath();
        System.out.println(selectedFile.getAbsolutePath());
        String filename = "file:///" + path;
        Image img = new Image(filename);
        avatar.setImage(img);
        
        try {
            HttpPost httpPost = new HttpPost(new URL("http://localhost/bookstore/upload_profile_img.php"));
            httpPost.setFileNames(new String[]{ selectedFile.getAbsolutePath() });
            httpPost.post();
            User newUser = currentUser;
            newUser.setProfilePicture("http://localhost/bookstore/profileImages/"+selectedFile.getName());
            
                ServiceUser.setuserToUpdate(newUser);
            
            System.out.println(httpPost.getOutput());
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpPost.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }        
    }
    
}
