package sample;

import Game.GamePlay;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
    /*
    @FXML
    void initialize(){
        cardList.generateCardList(score+1);
        showAllCard();
        textCardAt.setText("1");
    }*/

    CardList cardList = new CardList();
    int score = 0;
    String playerAnswer;
    Boolean running = true;


    public void micOn(ActionEvent event){
        System.out.println("Clicked!");
        SpeechToText.startSpeechRecognition(answeringTextField,"vi-VN");
    }

    public void micOff(ActionEvent event){
        SpeechToText.stopSpeechRecognition();
    }

    public void showAllCard(){
        for (Card c:cardList.getList()){
            this.desk.getChildren().add(c);
            System.out.println(c.toString());
        }
    }

    public void checkAnswer(Card c, String playerAnswer){
        if(playerAnswer.indexOf(c.getValue().toString())>-1
                && playerAnswer.indexOf(c.getSuit().toString())>-1
                &&playerAnswer.indexOf(c.getColor().toString())>-1) {
            System.out.println("dung");
            score++;
            running();
        }
        else {
            System.out.println("Sai");
            running =!running;
        }

    }

    public void checkAnswerOnClick(ActionEvent event){
        playerAnswer = answeringTextField.getText();
        /*
        if(playerAnswer.indexOf(cardList.getList().get(0).getValue().toString())>-1
        && playerAnswer.indexOf(cardList.getList().get(0).getSuit().toString())>-1
        &&playerAnswer.indexOf(cardList.getList().get(0).getColor().toString())>-1) {
            System.out.println("dung");
            score++;
            running();
        }
        else {
            System.out.println("Sai");
            running =!running;
        }*/
        //answeringTextField.clear();
    }

    public void running(){
        while(true) {

            /**
             *  Generate new cardList, number of card = score +1
             */
            cardList.generateCardList(score + 1);
            showAllCard();

            /**
             * Choose card to open with arr element order
             */
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < score + 1; i++) {
                arr.add(i);
            }

            Collections.shuffle(arr); //after this, arr elements have a random order

            for (int e : arr) {
                System.out.println("Card at:" + e+1);
                textCardAt.setText(e + "");
                /**
                 * player answering
                 */

                Card _this = cardList.getList().get(e);

                if (playerAnswer.indexOf(_this.getValue().toString()) > -1 &&
                        playerAnswer.indexOf(_this.getSuit().toString()) > -1 &&
                        playerAnswer.indexOf(_this.getColor().toString()) > -1
                ) {
                    System.out.println("Correct !!!");
                    dunghaysai.setText("Dung");
                } else {

                    /** Xu ly sai va thoat game*/

                    System.out.println("Sai !!!");
                    System.out.println("Score: " + score);
                    System.exit(0);

                }
            }

            score++;
        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Game luyện trí nhớ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

}
