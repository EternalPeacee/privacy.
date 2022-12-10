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
import java.sql.Statement;

public class Register {

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button register;

    @FXML
    private TextField username;

    @FXML
    private Label loginLabel;

    @FXML
    private Label InformationLabel;

    Connection conn = null;



    //Open Log In Form Function

    public void OpenLogInForm(MouseEvent event) {
        try {
            Stage stage = (Stage) loginLabel.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Stage LogInScene = new Stage();
            LogInScene.setScene(new Scene(root, 1100, 700));
            LogInScene.show();
            LogInScene.setResizable(false);
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    //Register Form

    public void SignUpButtonAction(MouseEvent event) {

        if(!firstName.getText().isBlank() && !lastName.getText().isBlank() && !username.getText().isBlank()
                && !email.getText().isBlank() && !phoneNumber.getText().isBlank() && !password.getText().isBlank()
                && !confirmPassword.getText().isBlank()
        ) {

            if(password.getText().equals(confirmPassword.getText())) {

                conn = DbConnection.ConnectDB();

                String sql = "INSERT INTO users (firstName, lastName, username, email, phoneNumber, password) VALUES ('" + firstName.getText() +
                        "','" + lastName.getText() + "','" + username.getText() + "','" + email.getText() + "','" + phoneNumber.getText()
                        + "','" + password.getText()  + "')";

                try {

                    Statement statement = conn.createStatement();
                    statement.executeUpdate(sql);

                    System.out.println("The user is register");
                    InformationLabel.setTextFill(Color.web("#00FF00"));
                    InformationLabel.setText("The user has been registered successfully!");
                    firstName.setText("");
                    lastName.setText("");
                    username.setText("");
                    email.setText("");
                    phoneNumber.setText("");
                    password.setText("");
                    confirmPassword.setText("");

                }catch(Exception e) {
                    System.out.println("Exception in LogIn Controller " + e);
                }

            }else {
                InformationLabel.setTextFill(Color.web("#ff0000"));
                InformationLabel.setText("Passwords does not match!");
            }

        }else {
            InformationLabel.setTextFill(Color.web("#ff0000"));
            InformationLabel.setText("Please fill all the fields");
        }


    }


}

