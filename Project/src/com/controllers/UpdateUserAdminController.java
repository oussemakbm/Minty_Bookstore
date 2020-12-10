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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.stage.Window;

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
    private JFXButton btnBack;
    @FXML
    private JFXButton update;

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
                
                User u=new User();
                u.setId(ServiceUser.getuserToUpdate().getId());
                u.setName(tfname.getText());
                u.setEmail(tfemail.getText());
                u.setNumTel(tftel.getText());
                u.setPassword(tfpassword.getText());
                u.setAdresse(tfaddress.getText());
                u.setProfilePicture("");
                if (rbadmin.isSelected()){
                    u.setRole("ADMIN");
                }else{
                    u.setRole("MEMBER");
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
        btnBack.setOnAction((event) -> {
             Window currentWindow = this.btnBack.getScene().getWindow();
        SceneLoader.getInstance().NavigateTo(currentWindow, "DisplayUsersAdmin");
        });
       
    }    
    
}
