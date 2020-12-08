/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author MediaStudio
 */
public class ContactUsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXTextField Email;

    @FXML
    private JFXButton jbtnSent;

    @FXML
    private JFXTextField subject;

    @FXML
    private ImageView gifLoading;

    @FXML
    private JFXTextArea contectText;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gifLoading.setVisible(false);
        
        
        
    } 
    public static void sendMail(String recepient) throws Exception {
        
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "mintyBookStore@gmail.com";
        //Your gmail password
        String password = "mna3na3Team";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    
   
     
     
     

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        
        
        try {
            
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Email from minty Book STORE");
            String htmlCode = "<h1> Minty Book store </h1> <br/> <h2><b>JavaFX </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return null;
    }
      @FXML
     void  SendMailAction(ActionEvent event) throws Exception {
        String  UserEmail =Email.getText();
        String subjectMail = subject.getText();
        String contect = contectText.getText();
        
        gifLoading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try {
                sendMail(UserEmail);
            } catch (Exception ex) {
                System.out.println("ERROR");;
             }
            gifLoading.setVisible(false);
        });
        pt.play();
        
        
        

    }
    

   
    
    
    
}
