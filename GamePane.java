package com.example.ballpane;



import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GamePane extends Pane  {
    private final int radius = 40;
    private int count = 0;
    private double defaultRate ;
    private int score = 0;
    private myCircle[] myCirclesArr = new myCircle[50];
    private Timeline animation;
    private Label label = new Label();
    private Font font = new Font(20);
    private GridPane topScores = new GridPane();
    private VBox vBox = new VBox();


    public int getScore() {
        return score;
    }
    public GamePane() throws IOException {
        Label welcomeLabel = new Label("Welcome to Speed Click Game");
        welcomeLabel.setFont(new Font(54));
        welcomeLabel.setTextFill(Color.WHITESMOKE);
        getChildren().add(welcomeLabel);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5), welcomeLabel);
        translateTransition.setFromX(100);
        translateTransition.setFromY(-100);
        translateTransition.setToY(300);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), welcomeLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setDelay(Duration.seconds(3));
        fadeTransition.setOnFinished(event -> {getChildren().remove(welcomeLabel);

        label.setText("To Start, Click the play button\n" +
                "Your score will be shown at the bottom and the values of the \n" +
                "objects are shown on the right side.");
        label.setTextFill(Color.WHITESMOKE);
        label.setFont(font);
            try {
                getScores();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            label.setAlignment(Pos.CENTER);
            topScores.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label,topScores);

        getChildren().add(vBox);});
        translateTransition.play();
        fadeTransition.play();
    }
    public void startGame(){
        reset();
        getChildren().add(this.myCirclesArr[this.count]);
        this.animation = new Timeline( new KeyFrame(Duration.millis(50), e -> {
            try {
                moveBallDownAndRemove();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        this.animation.setCycleCount(Timeline.INDEFINITE);
        this.animation.play();
        this.defaultRate = this.animation.getRate();
    }

    private void reset(){
        this.score = 0;this.count = 0;
        getChildren().remove(vBox);
        createObjects();
    }
    private void createObjects(){
        for (int i = 0 ; i < myCirclesArr.length ; i++){
            myCirclesArr[i] = new myCircle(i,radius,(int)getWidth());}
    }
    private void add(boolean shouldAdd) {
        if (shouldAdd && count != myCirclesArr.length -1){
            getChildren().add(myCirclesArr[count + 1]);count++;
            increaseSpeed();
        }
    }
    private void roundOver() throws IOException {animation.setRate(this.defaultRate);animation.stop(); endRoundDisplay();}
    private void saveScores() throws IOException {
        File file = new File("High_Scores.txt");

        Scanner input = new Scanner(file);
        ArrayList<Integer> highScores = new ArrayList<>();
        while(input.hasNext()){
            highScores.add(input.nextInt());
        } input.close();

        highScores.add(score);
        highScores.sort(Collections.reverseOrder());

        highScores.remove(highScores.size()-1);
        PrintWriter output = new PrintWriter(file);
        highScores.forEach(output::println);
        output.close();
    }
    private void getScores() throws IOException {
        File file = new File("High_Scores.txt");
        Scanner input = new Scanner(file);
        ArrayList<Integer> highScores = new ArrayList<>();
        while(input.hasNext()){
            highScores.add(input.nextInt());
        } input.close();

        topScores.setPadding(new Insets(10));
        topScores.setHgap(10);
        topScores.setVgap(10);

        Label nameLabel = new Label("Rank");
        Label scoreLabel = new Label("Top Scores");
        nameLabel.setTextFill(Color.WHITESMOKE);
        nameLabel.setFont(font);
        scoreLabel.setTextFill(Color.WHITESMOKE);
        scoreLabel.setFont(font);
        topScores.add(nameLabel, 0, 0);
        topScores.add(scoreLabel, 1, 0);

        String[] ranks = { "1", "2", "3", "4", "5" };

        for (int i = 0; i < ranks.length; i++) {
            Label nameLabelEntry = new Label(ranks[i]);
            Label scoreLabelEntry = new Label(Integer.toString(highScores.get(i)));
            nameLabelEntry.setTextFill(Color.WHITESMOKE);
            nameLabelEntry.setFont(font);
            scoreLabelEntry.setTextFill(Color.WHITESMOKE);
            scoreLabelEntry.setFont(font);

            topScores.add(nameLabelEntry, 0, i + 1);
            topScores.add(scoreLabelEntry, 1, i + 1);
        }

    }
    private void updateScores() throws IOException{
        saveScores();
        File file = new File("High_Scores.txt");
        Scanner input = new Scanner(file);
        ArrayList<Integer> highScores = new ArrayList<>();
        while(input.hasNext()){
            highScores.add(input.nextInt());
        } input.close();

        for (int i = 0; i < highScores.size(); i++) {
            getLabelFromCell(topScores, 1, i + 1).setText(Integer.toString(highScores.get(i)));
        }
    }
    private Label getLabelFromCell(GridPane gridPane, int columnIndex, int rowIndex) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == columnIndex && GridPane.getRowIndex(node) == rowIndex) {
                if (node instanceof Label) {
                    return (Label) node;
                }
            }
        } return null;
    }
    public void selector(double x,double y)  {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (Node node: this.getChildren()) {
            if (node instanceof myCircle){
            myCircle circle = (myCircle) node;

            if (circle.contains(x,y)){
                this.score += circle.getValue();
                indexes.add(circle.getIndex());
            }
        }}
        indexes.forEach(e-> {this.getChildren().remove(myCirclesArr[e]); add(true);});

    }
    private void increaseSpeed(){
        this.animation.setRate(this.animation.getRate() + 0.5);
    }
    private DoubleProperty rateProperty(){
        return animation.rateProperty();
    }
    protected void moveBallDownAndRemove() throws IOException {
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Boolean> shouldAdd = new ArrayList<>();

        if (this.getChildren().isEmpty()) roundOver();
        else {
            for (Node node : this.getChildren()) {
                if (node instanceof myCircle){
                myCircle circle = (myCircle) node;
                if (circle.getCenterY() > getHeight()) {
                    indexes.add(circle.getIndex());
                } else {
                    circle.adjustPosition();
                    shouldAdd.add((int)myCirclesArr[count].getCenterY() == (int)getHeight()/4);
                }
            }}
            shouldAdd.forEach(this::add);
            indexes.forEach(e-> this.getChildren().remove(myCirclesArr[e]));
        }
    }
    private void endRoundDisplay() throws IOException {
        label.setText("Nice !\nYour score for this play was :" + getScore() +
                "\nIf you wish to play again, click the play button.");
        updateScores();
        getChildren().add(vBox);

    }

}


