//package com.example.ballpane;
//
//import javafx.animation.Animation;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.animation.TranslateTransition;
//import javafx.beans.property.DoubleProperty;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.util.Duration;
//
//import java.util.ArrayList;
//
//public class ballPane extends Pane {
//    public final int radius  = 20;
//    private double score ;
//    private final int numberOfBalls = 5;
//    private int count = 0;
//
//    private int x = radius, y = radius;
//    private int dx = 1, dy = 1;
//
//    private Circle mycircle ;
//
//    private Timeline animation;
//    private boolean isSelect = false ;
//    Timeline[] animations = new Timeline[numberOfBalls];
//    myCircle[] Balls = new myCircle[numberOfBalls];
//    public ballPane(){
//        add();
//
////        for (Timeline animation : animations){
////            getChildren().add(Balls[count]);
////            animation = new Timeline( new KeyFrame(Duration.millis(100),e -> moveBallDownAndRemove(count)));
////            animation.setCycleCount(Timeline.INDEFINITE);
////            animation.play();
////            count++;
////        }
//        animation = new Timeline(
//                new KeyFrame(Duration.millis(50), e -> moveBallDownAndRemove()));
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.play(); // Start animation
//
////        for (int i = 0; i < Balls.length; i++) {
////            getChildren().add(Balls[i]);
////            Timeline animation = new Timeline(
////                    new KeyFrame(Duration.millis(100), e -> moveBallDownAndRemove(count))
////            );
////            animation.setCycleCount(Timeline.INDEFINITE);
////            animation.play();
////            count++;
////        }
////        count = 0;
//
//    }
////    public void createBalls(){
////        for (int i = 0; i < numberOfBalls; i++){
////            Balls[i] = new myCircle((int)(radius+Math.random()*getWidth()),y,radius,(i+1)*5);
////        }
////    }
//    public void add() {
//        Color color = new Color(Math.random(),
//                Math.random(), Math.random(), 0.5);
//
//        mycircle = new myCircle(x+ (int)(Math.random()*20), y,radius, color,(int)(Math.random()*5));
//        getChildren().add(mycircle);
//    }
//
//
//
//    public void play(){
//        animation.play();
//    }
//    public void pause(){
//        animation.pause();
//    }
//    public void selector(double x,double y){ if(mycircle.contains(x,y)){
////        isSelect = true;
//        getChildren().remove(getChildren().size() - 1);
//        add();
//    }}
//
//    public void increaseSpeed(){
//        animation.setRate(animation.getRate() + 50.1);
//    }
//    public void decreaseSpeed(){
//        animation.setRate(animation.getRate() > 0 ? animation.getRate()-50.1 : 0);
//    }
//
//    public DoubleProperty rateProperty(){
//        return animation.rateProperty();
//    }
//    public void displayBalls(int c){
//
//
//    }
//
////    protected void moveBall(){
////        if (y < radius || y > getHeight() - radius){
////            dy*=-1;
////        }
////          y+=dy;
////        circle.setCenterX(x);circle.setCenterY(y);
////    }
//
////    protected void moveBallDownAndRemove(int count){
////
////        if (y > getHeight() - radius) {getChildren().remove(Balls[count]); }
////        // remove the circle from the pane after the transition is finished
////        else {
////
////        Balls[count].setCenterY(Balls[count].getCenterY()+dy);
////        }
////
////    }
//    protected void moveBallDownAndRemove(){
//
//        if (y > getHeight() - radius) {
//            getChildren().remove(mycircle);
//            add();
//        }
//        // remove the circle from the pane after the transition is finished
//        else {
//            mycircle.setCenterY(mycircle.getCenterY()+dy);
//        }
//
//    }
//
//}
