/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jg229finalproject;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 *
 * @author Sahtopi
 */
public class GameModel {
    private Node view;
    private Point2D velocity = new Point2D(0,0);
    
    
    //figure out if object is alive or not. If not remove from game world
    private boolean alive = true;
    
    public GameModel(Node view){
        this.view = view;
    }
    
    public void update(){
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }
    
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
    
    public Point2D getVelocity() {
        return velocity;
    }
    
    public Node getView(){
        return view;
    }
    
    //check if object is alive
    public boolean isAlive(){
        return alive;
    }
    
    //if it is dead set to alive
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    public double getRotate(){
        return view.getRotate();
    }
    
    public void rotateRight(){
        view.setRotate(view.getRotate() + 5);
    }
    
    public void rotateLeft(){
        view.setRotate(view.getRotate() - 5);
    }
}
