package com.seaBattle.View;

import javax.swing.*;
import java.awt.*;

public class MainFrameGUI {
    private static JFrame mainFrame;
    public void graphicPartStarter(){
        mainFrameCreator();
        playerPanelAdd();
        scoreBoardPanelAdd();
        opponentPanelAdd();
    }
    public void mainFrameCreator(){
        mainFrame = new JFrame();
        mainFrame.setSize(1050, 450);
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
    public void scoreBoardPanelAdd(){
        ScoreBoardPanel sbp = new ScoreBoardPanel();
        mainFrame.getContentPane().add(BorderLayout.CENTER, sbp.scoreBoardPanelCreator());
    }
    public JFrame getMainFrame(){
        return mainFrame;
    }

}
