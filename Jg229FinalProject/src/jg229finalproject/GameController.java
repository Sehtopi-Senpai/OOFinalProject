/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jg229finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Sahtopi
 */
public class GameController implements Initializable{
    @FXML
    private AnchorPane vizPane;
    
    @FXML
    private Label scoreLabel;
    
    private Integer freshScore = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //This sets the score to 0 when a game is started
        scoreLabel.setText(Integer.toString(freshScore));
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
