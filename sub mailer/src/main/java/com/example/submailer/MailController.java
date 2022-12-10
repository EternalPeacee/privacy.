package com.example.submailer;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;


public class MailController implements Initializable{


    char accountCheck = 1;

    @FXML
    private Label overviewEmail;

    public void displayUsername(String emailConnect){
        overviewEmail.setText(emailConnect);
        System.out.println(emailConnect);
    }

    @FXML
    private FontAwesomeIconView Menu;

    @FXML
    private FontAwesomeIconView MenuBack;

    @FXML
    private TextFlow account;

    @FXML
    private AnchorPane accountSlider;

    @FXML
    private AnchorPane emailSection;

    @FXML
    private TextFlow newEmailAdd;

    @FXML
    private FontAwesomeIconView search;

    @FXML
    private TextField serachInput;

    @FXML
    private AnchorPane slider;

    @FXML
    private AnchorPane fullEmailScene;



    @Override
    public void initialize(URL location, ResourceBundle resources) {




        fullEmailScene.setVisible(true);
        accountSlider.setVisible(true);

        //Close
        slider.setTranslateX(0);
        emailSection.setTranslateX(0);
        accountSlider.setTranslateX(1600);

        Menu.setOnMouseClicked(event -> {
            //Menu Fold Animation
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();

            //Email Width animation
            TranslateTransition email = new TranslateTransition();
            email.setDuration(Duration.seconds(0.4));
            email.setNode(emailSection);
            email.setToX(0);
            email.play();

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        //Open--------------------------------------------------------------------------------------

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-268);
            slide.play();

            TranslateTransition email = new TranslateTransition();
            email.setDuration(Duration.seconds(0.4));
            email.setNode(emailSection);
            email.setToX(-190);
            email.play();

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });


        //Account----------------------------------------------------------
        account.setOnMouseClicked(event -> {

            if (accountCheck == 1) {
                //Menu Fold Animation
                TranslateTransition accSlider = new TranslateTransition();
                accSlider.setDuration(Duration.seconds(0.4));
                accSlider.setNode(accountSlider);
                accSlider.setToX(-100);
                accSlider.play();
                accountCheck = 2;


            } else {

                TranslateTransition accSlider = new TranslateTransition();
                accSlider.setDuration(Duration.seconds(0.4));
                accSlider.setNode(accountSlider);
                accSlider.setToX(260);
                accSlider.play();
                accountCheck = 1;


            }
        });



}
}