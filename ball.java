package com.example.ballpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ball extends Application {
    GamePane gamePane; Label score;
    @Override
    public void start(Stage primaryStage) throws IOException {

        gamePane = new GamePane();
        Image image = new Image("backGround.jpg");
        ImageView imageview = new ImageView(image);
        imageview.setPreserveRatio(false);
        imageview.fitWidthProperty().bind(primaryStage.widthProperty());
        imageview.fitHeightProperty().bind(primaryStage.heightProperty());


        gamePane.setOnMouseClicked(e-> {
            gamePane.selector(e.getX(),e.getY());
            setScore();
        });
        Font font = new Font(20);
        score = new Label("Score: " + gamePane.getScore());
        score.setFont(font);
        score.setTextFill(Color.WHEAT);

        CircleWithText redCircle = new CircleWithText(new Circle(40,Color.RED),new Text("5"));
        CircleWithText blueCircle = new CircleWithText(new Circle(40,Color.BLUE),new Text("10"));
        CircleWithText greenCircle = new CircleWithText(new Circle(40,Color.GREEN),new Text("15"));
        CircleWithText cyanCircle = new CircleWithText(new Circle(40,Color.CYAN),new Text("20"));

        VBox vBox = new VBox(redCircle,blueCircle,greenCircle,cyanCircle);
        Button play = new Button("Play");
        play.setStyle("-fx-font-family: Arial; -fx-font-size: 20px;");
        play.setPrefSize(100,50);
        play.setOnMouseClicked(e -> {gamePane.startGame();setScore();});
        HBox hBox = new HBox(score,play);

        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageview);
        borderPane.setCenter(gamePane);
        borderPane.setRight(vBox);

        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Set the stage dimensions to match the screen dimensions
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Speed Click Game");

        primaryStage.show();
    }

    public void setScore(){
        score.setText("Score: "+gamePane.getScore());
    }

    public static void main(String[] args) {
        launch();
    }
}

class CircleWithText extends StackPane {
    public CircleWithText(Circle circle, Text text) {
        super(circle, text);
        text.setFont(new Font(20));
        setAlignment(Pos.CENTER);
    }
}