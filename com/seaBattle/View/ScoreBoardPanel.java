package com.seaBattle.View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardPanel {
    private static JPanel scoreBoard;
    private static JTextArea turnArea;
    private static JTextArea oneSizeArea;
    private static JTextArea twoSizeArea;
    private static JTextArea threeSizeArea;
    private static JTextArea fourSizeArea;
    private static JLabel playerCountLabel;
    private static JLabel opponentCountLabel;
    private static JPanel shipsToCreatePanel;
    public JPanel scoreBoardPanelCreator(){
        scoreBoard = new JPanel(new GridLayout(3, 1));
        scoreBoard.add(turnAreaCreator());
        scoreBoard.add(shipCreatePanel());
        return scoreBoard;
    }
    private JTextArea turnAreaCreator(){
        turnArea = new JTextArea();
        turnArea.setPreferredSize(new Dimension(100, 40));
        turnArea.setText("Game preparing!\nChoose your boats");
        turnArea.setBackground(scoreBoard.getBackground());
        turnArea.setFont(new Font("Arial", Font.ITALIC, 15));
        turnArea.setForeground(Color.BLACK);
        return turnArea;
    }
    private JPanel turnCounterPanel(){
        JPanel turnCounterPanel = new JPanel(new BorderLayout());
        turnCounterPanel.setPreferredSize(new Dimension(100, 40));
        turnCounterPanel.add(BorderLayout.WEST, turnCounterLabelPanel());
        turnCounterPanel.add(BorderLayout.EAST, turnCounterTextPanel());
        return turnCounterPanel;
    }
    private JPanel turnCounterLabelPanel(){
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        Font font = new Font("Arial", Font.ITALIC, 15);
        JLabel playerTiles = new JLabel("Player tiles");
        playerTiles.setFont(font);
        JLabel opponentTiles = new JLabel("Opponent tiles");
        opponentTiles.setFont(font);
        labelPanel.add(playerTiles);
        labelPanel.add(opponentTiles);
        return labelPanel;
    }
    private JPanel turnCounterTextPanel(){
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        Font font = new Font("Arial", Font.ITALIC, 15);
        playerCountLabel = new JLabel("20");
        playerCountLabel.setForeground(Color.GREEN);
        playerCountLabel.setFont(font);
        opponentCountLabel = new JLabel("20");
        opponentCountLabel.setForeground(Color.GREEN);
        opponentCountLabel.setFont(font);
        textPanel.add(playerCountLabel);
        textPanel.add(opponentCountLabel);
        return textPanel;
    }
    private JPanel shipCreatePanel(){
        shipsToCreatePanel = new JPanel(new BorderLayout());
        shipsToCreatePanel.add(BorderLayout.NORTH, shipCreatePanelTitleArea());
        shipsToCreatePanel.add(BorderLayout.WEST, shipCreateSizePanel());
        shipsToCreatePanel.add(BorderLayout.EAST, shipCreateNumberPanel());
        return shipsToCreatePanel;
     }
     private JTextArea shipCreatePanelTitleArea(){
         JTextArea titleArea = new JTextArea("Ships remaining!");
         titleArea.setFont(new Font("Arial", Font.ITALIC, 15));
         titleArea.setBackground(scoreBoard.getBackground());
         titleArea.setPreferredSize(new Dimension(100, 40));
         return titleArea;
     }
     private JPanel shipCreateSizePanel(){
         JPanel shipSizePanel = new JPanel(new GridLayout(4, 1));
         shipSizePanel.setPreferredSize(new Dimension(80, 100));
         for(int i = 4; i > 0; i--){
             JLabel sizeArea = new JLabel(i + " size boat");
             sizeArea.setFont(new Font("Arial", Font.ITALIC, 15));
             shipSizePanel.add(sizeArea);
         }
         return shipSizePanel;
     }
     private JPanel shipCreateNumberPanel(){
         JPanel shipNumberPanel = new JPanel(new GridLayout(4, 1));
         shipNumberPanel.setPreferredSize(new Dimension(20, 100));
         Font font = new Font("Arial", Font.ITALIC, 15);
         shipNumberPanel.add(fourSizeAreaCreator(font));
         shipNumberPanel.add(threeSizeAreaCreator(font));
         shipNumberPanel.add(twoSizeAreaCreator(font));
         shipNumberPanel.add(oneSizeAreaCreator(font));
         return shipNumberPanel;
     }
     private JTextArea fourSizeAreaCreator(Font font){
         fourSizeArea = new JTextArea("1");
         fourSizeArea.setFont(font);
         fourSizeArea.setEditable(false);
         fourSizeArea.setBackground(scoreBoard.getBackground());
         fourSizeArea.setForeground(Color.GREEN);
         return fourSizeArea;
     }
    private JTextArea threeSizeAreaCreator(Font font){
        threeSizeArea = new JTextArea("2");
        threeSizeArea.setFont(font);
        threeSizeArea.setEditable(false);
        threeSizeArea.setBackground(scoreBoard.getBackground());
        threeSizeArea.setForeground(Color.GREEN);
        return threeSizeArea;
    }
    private JTextArea twoSizeAreaCreator(Font font){
        twoSizeArea = new JTextArea("3");
        twoSizeArea.setFont(font);
        twoSizeArea.setEditable(false);
        twoSizeArea.setBackground(scoreBoard.getBackground());
        twoSizeArea.setForeground(Color.GREEN);
        return twoSizeArea;
    }
    private JTextArea oneSizeAreaCreator(Font font){
        oneSizeArea = new JTextArea("4");
        oneSizeArea.setFont(font);
        oneSizeArea.setEditable(false);
        oneSizeArea.setBackground(scoreBoard.getBackground());
        oneSizeArea.setForeground(Color.GREEN);
        return oneSizeArea;
    }
    private JPanel lastPartPanelPlayer(){
        JPanel lastPartPanel = new JPanel();
        JLabel lastPartLabel = new JLabel("You lost");
        lastPartLabel.setForeground(Color.RED);
        lastPartLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        lastPartPanel.add(lastPartLabel);
        return lastPartPanel;
    }
    private JPanel lastPartPanelOpponent(){
        JPanel lastPartPanel = new JPanel();
        JLabel lastPartLabel = new JLabel("You won");
        lastPartLabel.setForeground(Color.RED);
        lastPartLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        lastPartPanel.add(lastPartLabel);
        return lastPartPanel;
    }
    public static JTextArea getTurnArea(){
        return turnArea;
    }
    public JTextArea getOneSizeArea(){
        return oneSizeArea;
    }
    public JTextArea getTwoSizeArea(){
        return twoSizeArea;
    }
    public JTextArea getThreeSizeArea(){
        return threeSizeArea;
    }
    public JTextArea getFourSizeArea(){
        return fourSizeArea;
    }
    public JPanel getShipsToCreatePanel(){
        return shipsToCreatePanel;
    }
    public JPanel getScoreBoard(){
        return scoreBoard;
    }
    public JPanel getTurnCounterPanel(){
        return turnCounterPanel();
    }
    public JLabel getPlayerCountLabel(){
        return playerCountLabel;
    }
    public JLabel getOpponentCountLabel(){
        return opponentCountLabel;
    }
    public JPanel getLastPanelOpponent(){
        return lastPartPanelOpponent();
    }
    public JPanel getLastPanelPlayer(){
        return lastPartPanelPlayer();
    }
}
