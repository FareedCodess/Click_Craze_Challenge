package com.example.ballpane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class myCircle extends Circle {
    private final int dy =1;
    private int value;
    private int index;
    private Color color;
    private  int x;


    public myCircle(int index,int radius,int x){
        super(0,0, radius);
        this.index = index;

        setValue();
        setColor();
        setRandomX(x);

    }
    public void setRandomX(int x){
        int o_x = (int)getRadius() + (int)(Math.random() * (x -(int)getRadius()));
        setCenterX(o_x);
    }
    public void setValue(){
        this.value = ((int) (Math.random() * 4) + 1 ) * 5;
    }
    public void setColor(){
        switch (this.value){
            case (5) -> this.color = Color.RED;
            case (10) -> this.color = Color.GREEN;
            case (15) -> this.color = Color.BLUE;
            case (20) -> this.color = Color.CYAN;
        }
//        this.color = value == 5 ? Color.RED :  value == 10 ? Color.BLUE : Color.GREEN;
        setFill(this.color);
    }
    public int getValue() {
        return value;
    }
    public int getIndex() {
        return index;
    }
    public int getDy() {
        return dy;
    }
    public void adjustPosition(){
        setCenterY(getDy() + getCenterY());
    }
}

