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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

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
    private Pane answerPane;

    @FXML
    void initialize(){
        answerPane.setVisible(false);
        showScore.setText("0");
        dunghaysai.setText("");
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
        int viTriLaBai = arr.get(0)+1;
        textCardAt.setText(viTriLaBai+"");
        answerPane.setVisible(true);
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
                //System.out.println("Đúng");
                dunghaysai.setText("Đúng");
                score++;
                answeringTextField.clear();
                textCardAt.setText("");
                running();
            } else {

               // Ket thuc tro choi
                endGame();
            }
        }
    }

    public void running(){
        /**
         *  Generate new cardList, number of card = score +1
         */
        cardList.generateCardList(score + 1);
        showAllCard();
        answerPane.setVisible(false);
        showScore.setText(score+"");

        /**
         * Choose card to open with arr element order
         */

        arr.clear();
        for (int i = 0; i < score + 1; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr); //after this, arr elements have a random order

        //System.out.println(arr.get(0));

        desk.setVisible(true);

    }

    public void endGame(){
        //System.out.println("Sai");
        dunghaysai.setText("Oh no!");
        desk.setVisible(true);
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
