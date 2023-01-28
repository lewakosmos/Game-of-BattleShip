package com.seaBattle.View;

import com.seaBattle.Model.DeckSettings;

import javax.swing.*;
import java.awt.*;

public interface DeckGUI {
    JPanel deckCreator();
    default JPanel deckTopLettersCreator(){
        DeckSettings ds = new DeckSettings();
        JPanel deckTopLetterPanel = new JPanel(new GridLayout(1, 11));
        deckTopLetterPanel.add(localEmptyTopLabelCreator());
        for(int i = 0; i < ds.getColumnLetterArray().length; i++){
            JLabel localTopLetterLabel = new JLabel();
            localTopLetterLabel.setText(ds.getColumnLetterArray()[i]);
            localTopLetterLabel.setPreferredSize(new Dimension(20, 30));
            deckTopLetterPanel.add(localTopLetterLabel);
        }
        return deckTopLetterPanel;
    }
    default JLabel localEmptyTopLabelCreator(){
        JLabel localEmptyColumnLabel = new JLabel("");
        localEmptyColumnLabel.setPreferredSize(new Dimension(20, 30));
        return localEmptyColumnLabel;
    }
    default JPanel deckLeftNumbersCreator(){
        DeckSettings ds = new DeckSettings();
        JPanel deckLeftNumberPanel = new JPanel(new GridLayout(10, 1));
        for(int i = 0; i < ds.getStringNumberArray().length; i++){
            JLabel localLeftNumberLabel = new JLabel();
            localLeftNumberLabel.setText(ds.getStringNumberArray()[i]);
            localLeftNumberLabel.setPreferredSize(new Dimension(30, 20));
            deckLeftNumberPanel.add(localLeftNumberLabel);
        }
        return deckLeftNumberPanel;
    }
    JPanel deckButtonAmountCreator();
    default JPanel emptySpacePanelCreator(){
        JPanel emptySpacePanel = new JPanel();
        emptySpacePanel.setPreferredSize(new Dimension(20, 450));
        return emptySpacePanel;
    }
    JPanel southLayoutPanelButtonCreator();

}
