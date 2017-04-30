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
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        
        player = new Player();
        player.setVelocity(new Point2D(1,0));
        
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
        for (GameModel bullet : bullets){
            for(GameModel enemy : enemies){
                if(bullet.isCollision(enemy)){
                    bullet.setAlive(false);
                    enemy.setAlive(false);
                }
            }
        }
    }
    
    private static class Player extends GameModel{
        Player(){
            super(new Rectangle(40, 20, Color.BLACK));
        }
    }
    
    private static class Enemy extends GameModel{
        Enemy(){
            super(new Circle(15, 15, 20, Color.WHITE));
        }
    }
    
    private static class Bullet extends GameModel {
        Bullet(){
            super(new Circle(4, 4, 4, Color.RED));
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
