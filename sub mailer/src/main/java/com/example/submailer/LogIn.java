package com.example.submailer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class LogIn {

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Label registerLabel;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Label mailLabel;


    Connection conn = null;

    //Open Register Form Function

    @FXML
    void OpenRegisterForm(MouseEvent event) {
        try {
        Stage stage = (Stage) registerLabel.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
        Stage RegisterScene = new Stage();
        RegisterScene.setScene(new Scene(root, 1100, 700));
        RegisterScene.show();
        RegisterScene.setResizable(false);
    } catch(Exception e) {
        e.printStackTrace();
        e.getCause();
    }
    }


    //Log In Form


    @FXML
    private void LogIn(MouseEvent event) throws Exception{

        if(!email.getText().isBlank() && !password.getText().isBlank()) {
            conn = DbConnection.ConnectDB();



            String sql = "SELECT count(1) FROM users WHERE email = '" + email.getText() + "' AND password = '" + password.getText() + "'";

            try {

                Statement statement = conn.createStatement();
                ResultSet queryResult = statement.executeQuery(sql);

                while(queryResult.next())
                {

                    if(queryResult.getInt(1)==1) {
                        loginMessageLabel.setTextFill(Color.web("#00FF00"));
                        loginMessageLabel.setText("Connected!");





                        //New State
                        Stage stage = (Stage) registerLabel.getScene().getWindow();
                        stage.close();
                        Parent root = FXMLLoader.load(getClass().getResource("Mail.fxml"));

                        FXMLLoader loaderA = new FXMLLoader(getClass().getResource("Mail.fxml"));
                        Parent sceneA = loaderA.load();
                        MailController sceneAController = loaderA.getController();

                        //Global Email Input Variable
                        String emailConnect = email.getText();
                        MailController mailController = loaderA.getController();
                        mailController.displayUsername(emailConnect);


                        Stage registerScene = new Stage();
                        registerScene.setScene(new Scene(root, 1300, 700));
                        registerScene.show();
                        registerScene.setResizable(false);

                    }else {
                        loginMessageLabel.setTextFill(Color.web("#ff0000"));
                        loginMessageLabel.setText("Invalid Log In");
                        System.out.println("Invalid Log in");
                    }

                }

            }catch(Exception e) {
                System.out.println("Exception in Log In Controller " + e);
            }
        }
        else {
            loginMessageLabel.setTextFill(Color.web("#ff0000"));
            loginMessageLabel.setText("Please type your username and password.");
        }

    }






}