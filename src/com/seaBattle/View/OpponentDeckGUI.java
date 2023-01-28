package com.seaBattle.View;

import com.seaBattle.Controller.GamePlayProcessListener;
import com.seaBattle.Model.DeckSettings;
import com.seaBattle.Model.OpponentDeckAggregator;
import com.seaBattle.Model.PlayerDeckAggregator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OpponentDeckGUI implements DeckGUI {
    private static ArrayList<JButton> opponentPinkButtonList;
    private static ArrayList<JButton> opponentCommonButtonList;
    private static JButton startButton;
    public JPanel deckCreator(){
        JPanel opponentDeckPanel = new JPanel(new BorderLayout());
        opponentDeckPanel.add(BorderLayout.NORTH, deckTopLettersCreator());
        opponentDeckPanel.add(BorderLayout.WEST, deckLeftNumbersCreator());
        opponentDeckPanel.add(BorderLayout.CENTER, deckButtonAmountCreator());
        opponentDeckPanel.add(BorderLayout.EAST, emptySpacePanelCreator());
        opponentDeckPanel.add(BorderLayout.SOUTH, southLayoutPanelButtonCreator());
        return opponentDeckPanel;
    }
    public JPanel deckButtonAmountCreator(){
        OpponentDeckAggregator oda = new OpponentDeckAggregator();
        oda.deckAggregatorHierarchy();
        DeckSettings ds = new DeckSettings();
        opponentPinkButtonList = new ArrayList<>();
        opponentCommonButtonList = new ArrayList<>();
        JPanel opponentDeckButtonPanel = new JPanel(new GridLayout(10, 10));
        for(int i = 0; i < ds.getDeckButtonsTextList().size(); i++){
            JButton localOpponentDeckButtonPanelButton = new JButton(ds.getDeckButtonsTextList().get(i));
            if(oda.getDeckList().contains(localOpponentDeckButtonPanelButton.getText())){
                opponentPinkButtonList.add(localOpponentDeckButtonPanelButton);
            }
            opponentCommonButtonList.add(localOpponentDeckButtonPanelButton);
            localOpponentDeckButtonPanelButton.setBackground(Color.WHITE);
            localOpponentDeckButtonPanelButton.setForeground(Color.BLACK);
            localOpponentDeckButtonPanelButton.setFont(new Font("Times New Roman", Font.BOLD, 0));
            opponentDeckButtonPanel.add(localOpponentDeckButtonPanelButton);
        }
        return opponentDeckButtonPanel;
    }
    public JPanel southLayoutPanelButtonCreator(){
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setPreferredSize(new Dimension(450, 50));
        startButton = new JButton("Start Game");
        startButton.setBackground(Color.WHITE);
        startListenerImplementor();
        startButtonPanel.add(startButton);
        return startButtonPanel;
    }
    public void startListenerImplementor(){
        GamePlayProcessListener gppl = new GamePlayProcessListener();
        gppl.gamePlay();
    }
    public ArrayList<JButton> getOpponentPinkButtonList(){
        return opponentPinkButtonList;
    }
    public ArrayList<JButton> getOpponentCommonButtonList(){
        return opponentCommonButtonList;
    }
    public JButton getStartButton(){
        return startButton;
    }
}
