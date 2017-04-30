/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jg229finalproject;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Sahtopi
 */
public class Jg229FinalProject extends Application {
    
    private Pane root;
    
    private List<GameModel> bullets = new ArrayList<>();
    private List<GameModel> enemies = new ArrayList<>();
    
    private GameModel player;
    
    private Parent createContent(){
        root = new Pane();
        root.setPrefSize(600,600);
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                updateTool();
            }
        };
        timer.start();
        return root;
    }
    
    private void updateTool(){
        
    }
    
    private static class Player extends GameModel{
        Player(){
            super(new Rectangle(40, 20, Color.BLACK));
        }
    }
    
    private static class Enemy extends GameModel{
        Enemy(){
            super(new Rectangle(40, 20, Color.WHITE));
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
