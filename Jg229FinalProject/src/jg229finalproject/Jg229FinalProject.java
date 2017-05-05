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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
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
        player.setVelocity(new Point2D(0,0));
        addGameObject(player, 300, 300);
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                updateTool();
            }
        };
        timer.start();
        return root;
    }
    
   public void someoneScored()
    {
        int score = 0;
        score++;

//        scoreLabel.setText("Score: " + score);
    }
    
    //adds a bullet to the list
    private void addBullet(GameModel bullet, double x, double y){
        bullets.add(bullet);
        addGameObject(bullet, x , y);
        
    }
    
    //adds an enemy
    private void addEnemy(GameModel enemy, double x, double y){
        enemies.add(enemy);
        addGameObject(enemy, x , y);
        
    }
    //spawns bullet into scene
    private void addGameObject(GameModel object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }
    
    private void updateTool(){
        for (GameModel bullet : bullets){
            for(GameModel enemy : enemies){
                if(bullet.isCollision(enemy)){
                    bullet.setAlive(false);
                    enemy.setAlive(false);
                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
                }
            }
        }
        //go through bullets. if bullet is dead remove it from list
        bullets.removeIf(GameModel::isDead);
        //go through enemies. if enemy is dead remove it from list
        enemies.removeIf(GameModel::isDead);
        
        //update each object
        bullets.forEach(GameModel::update);
        enemies.forEach(GameModel::update);
        player.update();
        
        //controls objects spawning, the if statement controls the rate
        if(Math.random() < 0.01){
            addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }
    
    
    private static class Player extends GameModel{
        
        Player(){
            super(getShape());
        }
            private static Polygon getShape(){
                Polygon ship = new Polygon();
                ship.getPoints().addAll(new Double[]{
                   0.0,0.0,
                    -10.0,-20.0,
                    10.0,-20.0,
                });
                ship.setFill(Color.BLACK);
                return ship;
            }
        
    }
    
    private static class Enemy extends GameModel{
        Enemy(){
            super(getShape());
        }
            private static Polygon getShape(){
                Polygon enemy = new Polygon();
                enemy.getPoints().addAll(new Double[]{
                   40.0,40.0,
                    20.0,60.0,
                    20.0, 80.0,
                    40.0,100.0,
                    80.0,100.0,
                    100.0,80.0,
                    100.0,60.0,
                    80.0,40.0,
                    40.0,40.0,
                    
                });
                enemy.setFill(Color.DARKGRAY);
                return enemy;
            }
    }
    
    
    
    private static class Bullet extends GameModel {
        Bullet(){
            super(new Circle(4, 4, 4, Color.RED));
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(new Scene(createContent()));
        stage.show();
//        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.LEFT) {
                player.rotateLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.rotateRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Bullet bullet  = new Bullet();
                bullet.setVelocity(player.getVelocity().normalize().multiply(5));
                addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
            }
        });
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
