package com.seaBattle.Model;

import com.seaBattle.Controller.PlayerDeckCreationListener;
import com.seaBattle.View.PlayerDeckGUI;

import javax.swing.*;
import java.awt.*;

public class PlayerDeckAggregator {
    public void opponentArtificialIntelligence(){
        randomChooser();
    }
    public void randomChooser(){
        PlayerDeckCreationListener pdcl = new PlayerDeckCreationListener();
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        int randomTile = (int) (Math.random()*pdGUI.getPlayerCommonButtonList().size());
        JButton localButton = pdGUI.getPlayerCommonButtonList().get(randomTile);
        if(pdcl.getPlayerDeckList().contains(localButton)){
            localButton.setBackground(Color.RED);
            randomChooser();
        }
        else {
            localButton.setBackground(Color.CYAN);
        }
        pdGUI.getPlayerCommonButtonList().remove(localButton);
    }
}

