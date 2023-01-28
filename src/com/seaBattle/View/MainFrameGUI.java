package com.seaBattle.View;

import javax.swing.*;
import java.awt.*;

public class MainFrameGUI {
    private static JFrame mainFrame;
    public void graphicPartStarter(){
        mainFrameCreator();
        playerPanelAdd();
        opponentPanelAdd();
    }
    public void mainFrameCreator(){
        mainFrame = new JFrame();
        mainFrame.setSize(940, 450);
        mainFrame.setLocation(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void playerPanelAdd(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        mainFrame.getContentPane().add(BorderLayout.WEST, pdGUI.deckCreator());
    }
    public void opponentPanelAdd(){
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        mainFrame.getContentPane().add(BorderLayout.EAST, odGUI.deckCreator());
    }
    public JFrame getMainFrame(){
        return mainFrame;
    }

}
