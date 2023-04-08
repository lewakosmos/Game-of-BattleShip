package com.seaBattle.Controller;

import com.seaBattle.Model.PlayerDeckAggregator;
import com.seaBattle.View.MainFrameGUI;
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
        odGUI.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               buttonsHideAndActivation();
               scorePanelSwitch();
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
                        opponentCount();
                        button.setEnabled(false);
                        odGUI.getOpponentPinkButtonList().remove(button);
                        odGUI.getOpponentCommonButtonList().remove(button);
                    }
                    else{
                        button.setBackground(Color.LIGHT_GRAY);
                        button.setEnabled(false);
                        odGUI.getOpponentCommonButtonList().remove(button);
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
    private void scorePanelSwitch(){
        ScoreBoardPanel scp = new ScoreBoardPanel();
        scp.getScoreBoard().remove(scp.getShipsToCreatePanel());
        scp.getScoreBoard().add(scp.getTurnCounterPanel());
        MainFrameGUI mfGUI = new MainFrameGUI();
        mfGUI.getMainFrame().repaint();
    }
    private void buttonsHideAndActivation(){
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        odGUI.getOpponentCommonButtonList().forEach(JButton -> JButton.setEnabled(true));
        pdGUI.getPlayerCommonButtonList().forEach(JButton -> JButton.setBackground(Color.WHITE));
    }
    private void opponentCount(){
        ScoreBoardPanel scp = new ScoreBoardPanel();
        int minusOne = Integer.parseInt(scp.getOpponentCountLabel().getText());
        scp.getOpponentCountLabel().setText(Integer.toString(minusOne - 1));
        if(scp.getOpponentCountLabel().getText().equals("0")){
            scp.getScoreBoard().remove(scp.getTurnCounterPanel());
            scp.getScoreBoard().add(scp.getLastPanelOpponent());
        }
    }

}


