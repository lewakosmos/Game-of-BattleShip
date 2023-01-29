package com.seaBattle.Controller;

import com.seaBattle.Model.DeckSettings;
import com.seaBattle.View.OpponentDeckGUI;
import com.seaBattle.View.PlayerDeckGUI;
import com.seaBattle.View.ScoreBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerDeckCreationListener extends DeckSettings {
    private static ArrayList<JButton> playerBoatList;
    private static ArrayList<JButton> playerDeckList;
    private static final ArrayList<Integer> sizeList =
            new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 3, 4));
    public void playerDeckCreator(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        playerBoatList = new ArrayList<>();
        for(JButton playerButton : pdGUI.getPlayerCommonButtonList()){
            playerButton.addActionListener(e -> {
                playerButton.setBackground(Color.PINK);
                playerButton.setEnabled(false);
                playerBoatList.add(playerButton);
            });
        }
    }
    public void recordActionCreator(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        playerDeckList = new ArrayList<>();
        pdGUI.getRecordButton().addActionListener(e -> {
            if(sizeCheckCreator() && combinedException()){
                recordRightTurn();
                shipsCreatedCounter();
            }
            else{
                recordFalseTurn();
            }
            playerBoatList.clear();
            startButtonBlock();
        });
    }
    private void recordRightTurn(){
        ArrayList<JButton> localLimitsList = new ArrayList<>(limitsCreator());
        localLimitsList.removeAll(playerBoatList);
        localLimitsList.forEach(JButton -> JButton.setBackground(Color.LIGHT_GRAY));
        localLimitsList.forEach(JButton -> JButton.setEnabled(false));
        playerDeckList.addAll(playerBoatList);
    }
    private void recordFalseTurn(){
        playerBoatList.forEach(JButton -> JButton.setBackground(Color.WHITE));
        playerBoatList.forEach(JButton -> JButton.setEnabled(true));
    }
    private ArrayList<JButton> limitsCreator(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        ArrayList<String> limitStringList = new ArrayList<>();
        for(JButton limitButton : playerBoatList){
            String buttonLetter = limitButton.getText().substring(0, 1);
            String buttonNumber = limitButton.getText().substring(1);
            int buttonLetterIndex = Arrays.asList(getColumnLetterArray()).indexOf(buttonLetter);
            int buttonNumberIndex = Arrays.asList(getStringNumberArray()).indexOf(buttonNumber);
            for(int i = buttonLetterIndex - 1; i < buttonLetterIndex + 2; i++){
                for(int j = buttonNumberIndex - 1; j < buttonNumberIndex + 2; j++){
                    try {
                        limitStringList.add(getColumnLetterArray()[i] + getStringNumberArray()[j]);
                    }catch(ArrayIndexOutOfBoundsException ex){}
                }
            }
        }
        ArrayList<JButton> limitButtonList = new ArrayList<>();
        for(JButton button : pdGUI.getPlayerCommonButtonList()){
            if(limitStringList.contains(button.getText())){
                limitButtonList.add(button);
            }
        }
        return limitButtonList;
    }
    private boolean sizeCheckCreator(){
        boolean sizeChecker = false;
        if(sizeList.contains(playerBoatList.size())){
            sizeChecker = true;
            sizeList.remove((Integer) playerBoatList.size());
        }
        return sizeChecker;
    }
    private boolean combinedException(){
        if(!(verticalException() || horizontalException()) || !closePlaceException()){
           sizeList.add(playerBoatList.size());
        }
        return (verticalException() || horizontalException()) && closePlaceException();
    }
    private boolean verticalException() {
        boolean verticalLineException = true;
        String letterPart = playerBoatList.get(0).getText().substring(0, 1);
        for (JButton playerButton : playerBoatList) {
            if (!letterPart.equals(playerButton.getText().substring(0, 1))) {
                verticalLineException = false;
            }
        }
        return verticalLineException;
    }
    private boolean horizontalException() {
        boolean horizontalLineException = true;
        String numberPart = playerBoatList.get(0).getText().substring(1);
        for (JButton playerButton : playerBoatList) {
            if (!numberPart.equals(playerButton.getText().substring(1))) {
                horizontalLineException = false;
            }
        }
        return horizontalLineException;
    }
    private boolean closePlaceException(){
        String letterString = "";
        String numberString = "";
        for(JButton playerButton : playerBoatList){
            letterString = letterString.concat(playerButton.getText().substring(0, 1));
            numberString = numberString.concat(playerButton.getText().substring(1));
            numberString = numberString.concat(" ");
        }
        char[] letterArray = letterString.toCharArray();
        Arrays.sort(letterArray);
        int[] numberArray = Arrays.stream(numberString.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numberArray);
        boolean letterCheck = true;
        boolean numberCheck = true;
        for(int i = 1; i < letterArray.length; i++){
            if (letterArray[i] != letterArray[i - 1] + 1) {
                letterCheck = false;
            }
            if (numberArray[i] != numberArray[i - 1] + 1){
                numberCheck = false;
            }
        }
        return letterCheck || numberCheck;
    }
    private void startButtonBlock(){
        if(sizeList.isEmpty()){
            OpponentDeckGUI odGUI = new OpponentDeckGUI();
            odGUI.getStartButton().setEnabled(true);
        }
    }
    public ArrayList<JButton> getPlayerDeckList(){
        return playerDeckList;
    }
    public void shipsCreatedCounter(){
        ScoreBoardPanel scp = new ScoreBoardPanel();
        switch (playerBoatList.size()) {
            case 1 -> {
                int oneSizeNumber = Integer.parseInt(scp.getOneSizeArea().getText()) - 1;
                scp.getOneSizeArea().setText(Integer.toString(oneSizeNumber));
            }
            case 2 -> {
                int twoSizeNumber = Integer.parseInt(scp.getTwoSizeArea().getText()) - 1;
                scp.getTwoSizeArea().setText(Integer.toString(twoSizeNumber));
            }
            case 3 -> {
                int threeSizeNumber = Integer.parseInt(scp.getThreeSizeArea().getText()) - 1;
                scp.getThreeSizeArea().setText(Integer.toString(threeSizeNumber));
            }
            case 4 -> {
                int fourSizeNumber = Integer.parseInt(scp.getFourSizeArea().getText()) - 1;
                scp.getFourSizeArea().setText(Integer.toString(fourSizeNumber));
            }
        }
    }
}
