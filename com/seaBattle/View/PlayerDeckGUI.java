package com.seaBattle.View;

import com.seaBattle.Controller.PlayerDeckCreationListener;
import com.seaBattle.Model.DeckSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerDeckGUI implements DeckGUI {
    private static ArrayList<JButton> playerCommonButtonList;
    private static JButton recordButton;
    public JPanel deckCreator(){
        JPanel playerDeckPanel = new JPanel(new BorderLayout());
        playerDeckPanel.add(BorderLayout.NORTH, deckTopLettersCreator());
        playerDeckPanel.add(BorderLayout.WEST, deckLeftNumbersCreator());
        playerDeckPanel.add(BorderLayout.CENTER, deckButtonAmountCreator());
        playerDeckPanel.add(BorderLayout.EAST, emptySpacePanelCreator());
        playerDeckPanel.add(BorderLayout.SOUTH, southLayoutPanelButtonCreator());
        return playerDeckPanel;
    }
    public JPanel deckButtonAmountCreator(){
        DeckSettings ds = new DeckSettings();
        playerCommonButtonList = new ArrayList<>();
        JPanel playerDeckButtonPanel = new JPanel(new GridLayout(10, 10));
        for(int i = 0; i < ds.getDeckButtonsTextList().size(); i++){
            JButton localPlayerDeckButtonPanelButton = new JButton(ds.getDeckButtonsTextList().get(i));
            localPlayerDeckButtonPanelButton.setBackground(Color.WHITE);
            localPlayerDeckButtonPanelButton.setForeground(Color.BLACK);
            localPlayerDeckButtonPanelButton.setFont(new Font("Times New Roman", Font.BOLD, 0));
            playerCommonButtonList.add(localPlayerDeckButtonPanelButton);
            playerDeckButtonPanel.add(localPlayerDeckButtonPanelButton);
        }
        buttonListenerImplementor();
        return playerDeckButtonPanel;
    }
    public JPanel southLayoutPanelButtonCreator(){
        JPanel recordButtonPanel = new JPanel();
        recordButtonPanel.setPreferredSize(new Dimension(450, 50));
        recordButton = new JButton("Record");
        recordButton.setBackground(Color.WHITE);
        recordButtonPanel.add(recordButton);
        recordButtonListenerImplementor();
        return recordButtonPanel;
    }
    public void buttonListenerImplementor(){
        PlayerDeckCreationListener pdcl = new PlayerDeckCreationListener();
        pdcl.playerDeckCreator();
    }
    public void recordButtonListenerImplementor(){
        PlayerDeckCreationListener pdcl = new PlayerDeckCreationListener();
        pdcl.recordActionCreator();
    }
    public ArrayList<JButton> getPlayerCommonButtonList(){
        return playerCommonButtonList;
    }
    public JButton getRecordButton(){
        return recordButton;
    }
}
