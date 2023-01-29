package com.seaBattle.Controller;

import com.seaBattle.Model.PlayerDeckAggregator;
import com.seaBattle.View.OpponentDeckGUI;
import com.seaBattle.View.PlayerDeckGUI;
import com.seaBattle.View.ScoreBoardPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GamePlayProcessListener {
    public void startActionCreator(){
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        odGUI.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odGUI.getOpponentCommonButtonList().forEach(JButton -> JButton.setEnabled(true));
                pdGUI.getPlayerCommonButtonList().forEach(JButton -> JButton.setBackground(Color.WHITE));
                gameQueue();
            }
        });
    }
    private void gameQueue(){
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        for(JButton button : odGUI.getOpponentCommonButtonList()){
            ScoreBoardPanel.getTurnArea().setText("Players turn");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(odGUI.getOpponentPinkButtonList().contains(button)){
                        button.setBackground(Color.PINK);
                        button.setEnabled(false);
                        odGUI.getOpponentPinkButtonList().remove(button);
                    }
                    else{
                        button.setBackground(Color.LIGHT_GRAY);
                        button.setEnabled(false);
                        opponentsTurnAndTimer();
                    }
                }
            });
        }
    }
    private void opponentsTurnAndTimer(){
        PlayerDeckAggregator pda = new PlayerDeckAggregator();
        pda.timerTurn();
    }
}

