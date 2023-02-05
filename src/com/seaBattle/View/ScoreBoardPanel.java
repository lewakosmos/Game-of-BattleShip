package com.seaBattle.View;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardPanel {
    private static JTextArea turnArea;
    private static JTextArea oneSizeArea;
    private static JTextArea twoSizeArea;
    private static JTextArea threeSizeArea;
    private static JTextArea fourSizeArea;
    private static JPanel shipsToCreatePanel;
    public JPanel scoreBoardPanelCreator(){
        JPanel scoreBoard = new JPanel(new BorderLayout());
        scoreBoard.add(BorderLayout.NORTH, turnAreaCreator());
        scoreBoard.add(BorderLayout.CENTER, intermediatePanelCreator());
        scoreBoard.add(BorderLayout.SOUTH, shipCreatePanel());
        return scoreBoard;
    }
    private JTextArea turnAreaCreator(){
        turnArea = new JTextArea();
        turnArea.setPreferredSize(new Dimension(100, 40));
        turnArea.setText("Game preparing!\nChoose your boats");
        turnArea.setFont(new Font("Arial", Font.ITALIC, 15));
        turnArea.setBackground(Color.WHITE);
        turnArea.setForeground(Color.BLACK);
        return turnArea;
    }
    private JPanel intermediatePanelCreator(){
        JPanel intermediatePanel = new JPanel();
        intermediatePanel.setPreferredSize(new Dimension(100, 20));
        return intermediatePanel;
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
         fourSizeArea = new JTextArea("1");
         fourSizeArea.setEnabled(false);
         fourSizeArea.setFont(font);
         shipNumberPanel.add(fourSizeArea);
         threeSizeArea = new JTextArea("2");
         threeSizeArea.setEnabled(false);
         threeSizeArea.setFont(font);
         shipNumberPanel.add(threeSizeArea);
         twoSizeArea = new JTextArea("3");
         twoSizeArea.setEnabled(false);
         twoSizeArea.setFont(font);
         shipNumberPanel.add(twoSizeArea);
         oneSizeArea = new JTextArea("4");
         oneSizeArea.setEnabled(false);
         oneSizeArea.setFont(font);
         shipNumberPanel.add(oneSizeArea);
         return shipNumberPanel;
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
}
