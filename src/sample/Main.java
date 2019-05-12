package sample;

import Game.GamePlay;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    @FXML
    private Button okButton;
    @FXML
    private Text showScore;
    @FXML
    private Button stopRecordButton;
    @FXML
    private TextField answeringTextField;
    @FXML
    private Text textCardAt;
    @FXML
    private FlowPane desk;
    @FXML
    private Text dunghaysai;

    @FXML
    void initialize(){
        cardList.generateCardList(score+1);
        showAllCard();
        for (int i = 0; i < score + 1; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
    }

    CardList cardList = new CardList();
    int score = 0;
    ArrayList<Integer> arr = new ArrayList<Integer>();
    String playerAnswer;
    Boolean running = true;
    Boolean isUserClick = false;

    public void micOn(ActionEvent event){
        System.out.println("Clicked!");
        SpeechToText.startSpeechRecognition(answeringTextField,"vi-VN");
    }

    public void micOff(ActionEvent event){
        SpeechToText.stopSpeechRecognition();
    }

    public void showAllCard(){
        this.desk.getChildren().clear();
        for (Card c:cardList.getList()){
            this.desk.getChildren().add(c);
            System.out.println(c.toString());
        }
    }

    public void iRemembered(){
        textCardAt.setText(arr.get(0)+"");
        desk.setVisible(false);
    }

    public void checkAnswerOnClick(ActionEvent event){
        if (desk.isVisible()){

        } else {
            playerAnswer = answeringTextField.getText();
            int cardAt = arr.get(0);
            if (playerAnswer.indexOf(cardList.getList().get(cardAt).getValue().toString()) > -1
                    && playerAnswer.indexOf(cardList.getList().get(cardAt).getSuit().toString()) > -1
                    && playerAnswer.indexOf(cardList.getList().get(cardAt).getColor().toString()) > -1) {
                System.out.println("dung");
                dunghaysai.setText("dung");
                score++;
                answeringTextField.clear();
                textCardAt.setText("");
                running();
            } else {
                System.out.println("Sai");
                System.exit(0);
            }
        }
    }

    public void running(){
            /**
             *  Generate new cardList, number of card = score +1
             */
            cardList.generateCardList(score + 1);
            showAllCard();

            showScore.setText(score+"");

            /**
             * Choose card to open with arr element order
             */

            //ArrayList<Integer> arr = new ArrayList<Integer>();
            arr.clear();
            for (int i = 0; i < score + 1; i++) {
                arr.add(i);
            }
            Collections.shuffle(arr); //after this, arr elements have a random order
            //textCardAt.setText(arr.get(0)+"");
            System.out.println(arr.get(0));

        desk.setVisible(true);
            /**
            for (int e : arr) {
                System.out.println("Card at:" + e);
                textCardAt.setText(e + "");
                /**
                 * player answering
                 *


                Card _this = cardList.getList().get(e);

                okButton.setOnMouseClicked(event -> {
                    playerAnswer = answeringTextField.getText();
                    if (playerAnswer.indexOf(_this.getValue().toString())>-1
                            && playerAnswer.indexOf(_this.getSuit().toString())>-1
                            &&playerAnswer.indexOf(_this.getColor().toString())>-1
                    ) {
                        System.out.println("Correct !!!");
                        dunghaysai.setText("Dung");
                    } else {

                        /** Xu ly sai va thoat game*

                        System.out.println("Sai !!!");
                        System.out.println("Score: " + score);
                        System.exit(0);

                    }
                });



            }

            score++;*/


    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Game luyện trí nhớ");
        primaryStage.setScene(scene);
        //running();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

}
