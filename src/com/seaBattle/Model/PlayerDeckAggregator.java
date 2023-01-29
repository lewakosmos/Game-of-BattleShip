package com.seaBattle.Model;
import com.seaBattle.Controller.PlayerDeckCreationListener;
import com.seaBattle.View.OpponentDeckGUI;
import com.seaBattle.View.PlayerDeckGUI;
import com.seaBattle.View.ScoreBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerDeckAggregator extends PlayerDeckCreationListener {
    private static ArrayList<JButton> missedButtons = new ArrayList<>();
    private static ArrayList<JButton> hitButtons = new ArrayList<>();
    public void timerTurn(){
        ScoreBoardPanel.getTurnArea().setText("Opponents turn");
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        odGUI.getOpponentCommonButtonList().forEach(JButton -> JButton.setEnabled(false));
        Timer turnTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpleOpponentsTurn();
            }
        });
        turnTimer.start();
        turnTimer.setRepeats(false);
    }
    private void combinedOpponentsTurn(){
        if(hitButtons.isEmpty()){
            simpleOpponentsTurn();
        }
        else if(hitButtons.size()==1){


        }
    }
    private void simpleOpponentsTurn(){
        JButton localButton = randomButtonCreator();
        if(getPlayerDeckList().contains(localButton)){
            localButton.setBackground(Color.PINK);
            hitButtons.add(localButton);
            secondTurnAfterRightHit();
        }
        else {
            localButton.setBackground(Color.LIGHT_GRAY);
            missedButtons.add(localButton);
            ScoreBoardPanel.getTurnArea().setText("Players turn");
            OpponentDeckGUI odGUI = new OpponentDeckGUI();
            odGUI.getOpponentCommonButtonList().forEach(JButton -> JButton.setEnabled(true));
        }
    }
    private void closeTileFounderTurn(){

    }
    private JButton randomButtonCreator(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        int randomTile = (int) (Math.random()*pdGUI.getPlayerCommonButtonList().size());
        JButton randomButton = pdGUI.getPlayerCommonButtonList().get(randomTile);
        pdGUI.getPlayerCommonButtonList().remove(randomButton);
        return randomButton;
    }
    private void secondTurnAfterRightHit() {
        timerTurn();
    }
}

