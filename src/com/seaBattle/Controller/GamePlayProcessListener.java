package com.seaBattle.Controller;

import com.seaBattle.Model.OpponentDeckAggregator;
import com.seaBattle.Model.PlayerDeckAggregator;
import com.seaBattle.View.OpponentDeckGUI;
import com.seaBattle.View.PlayerDeckGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GamePlayProcessListener {
    public void gamePlay(){
        startActionCreator();
    }
    public void startActionCreator(){
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        odGUI.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonListCreator();
                pdGUI.getPlayerCommonButtonList().forEach(JButton -> JButton.setBackground(Color.WHITE));
            }
        });
    }
    public void buttonListCreator(){
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        for(JButton button : odGUI.getOpponentCommonButtonList()){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(odGUI.getOpponentPinkButtonList().contains(button)){
                        button.setBackground(Color.PINK);
                    }
                    else{
                        button.setBackground(Color.CYAN);

                    }
                    if(button.getBackground().equals(Color.CYAN)){
                        try{
                            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                            PlayerDeckAggregator pda = new PlayerDeckAggregator();
                            pda.randomChooser();
                        }catch(InterruptedException ex){}
                    }
                }
            });
        }
    }
}

