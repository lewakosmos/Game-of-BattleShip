package com.seaBattle.Model;
import com.seaBattle.Controller.PlayerDeckCreationListener;
import com.seaBattle.View.OpponentDeckGUI;
import com.seaBattle.View.PlayerDeckGUI;
import com.seaBattle.View.ScoreBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerDeckAggregator extends PlayerDeckCreationListener {
    private static final ArrayList<Integer> sizeList = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 3, 4));
    private static final ArrayList<JButton> hitButtonsList = new ArrayList<>();
    private static final ArrayList<JButton> missedButtonsList = new ArrayList<>();
    public void timerTurn(){
        ScoreBoardPanel.getTurnArea().setText("Opponents turn");
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        odGUI.getOpponentCommonButtonList().forEach(JButton -> JButton.setEnabled(false));
        Timer turnTimer = new Timer(1000, e -> gameQueue());
        turnTimer.start();
        turnTimer.setRepeats(false);
    }
    private void gameQueue(){
        switch (hitButtonsList.size()) {
            case 0 -> simpleOpponentsTurn();
            case 1 -> playersNeighbourSearcher();
            case 2 -> thirdNeighbourSearcher();
            default -> forthElementSearcher();
        }
    }
    private void simpleOpponentsTurn(){
        JButton localButton = randomButtonCreator();
        if(getPlayerDeckList().contains(localButton)){
            rightClickOnButton(localButton);
        }
        else {
            falseClickOnButton(localButton);
        }
    }
    private void rightClickOnButton(JButton localButton) {
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        PlayerDeckCreationListener pdcl = new PlayerDeckCreationListener();
        localButton.setBackground(Color.PINK);
        pdcl.getPlayerDeckList().remove(localButton);
        pdGUI.getPlayerCommonButtonList().remove(localButton);
        hitButtonsList.add(localButton);
        boolean oneSizeOptionToCreateImmediately = hitButtonsList.size() == 1 && !sizeList.contains(2)
                && !sizeList.contains(3) && !sizeList.contains(4);
        boolean twoSizeOptionToCreateImmediately = hitButtonsList.size() == 2
                && !sizeList.contains(3) && !sizeList.contains(4);
        boolean threeSizeOptionToCreateImmediately = hitButtonsList.size() == 3 && !sizeList.contains(4);
        boolean fourSizeOptionToCreateImmediately = hitButtonsList.size() == 4;
         if(oneSizeOptionToCreateImmediately || twoSizeOptionToCreateImmediately ||
                 threeSizeOptionToCreateImmediately || fourSizeOptionToCreateImmediately){
            sizeListRemove(hitButtonsList.size());
            makeLimits();
            hitButtonsList.clear();
        }
        timerTurn();
    }
    private void falseClickOnButton(JButton localButton){
        localButton.setBackground(Color.LIGHT_GRAY);
        missedButtonsList.add(localButton);
        ScoreBoardPanel.getTurnArea().setText("Players turn");
        OpponentDeckGUI odGUI = new OpponentDeckGUI();
        odGUI.getOpponentCommonButtonList().forEach(JButton -> JButton.setEnabled(true));
    }
    private JButton randomButtonCreator(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        int randomTile = (int) (Math.random()*pdGUI.getPlayerCommonButtonList().size());
        JButton randomButton = pdGUI.getPlayerCommonButtonList().get(randomTile);
        pdGUI.getPlayerCommonButtonList().remove(randomButton);
        return randomButton;
    }
    private void playersNeighbourSearcher() {
        ArrayList<JButton> secondTurnList = new ArrayList<>(playerNeighbourAmountList());
        if(secondTurnList.isEmpty()){
            sizeListRemove(hitButtonsList.size());
            makeLimits();
            hitButtonsList.clear();
            timerTurn();
        }
        else{
            int secondTurnListRandomIndex = (int) (Math.random()*secondTurnList.size());
            JButton localButton = secondTurnList.get(secondTurnListRandomIndex);
            if(getPlayerDeckList().contains(localButton)){
                rightClickOnButton(localButton);
            }
            else {
                falseClickOnButton(localButton);
            }
        }
    }
    private ArrayList<JButton> playerNeighbourAmountList(){
        DeckSettings ds = new DeckSettings();
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        ArrayList<JButton> neighbourTurnList = new ArrayList<>();
        String buttonColumnValue = hitButtonsList.get(0).getText().substring(0, 1);
        int buttonColumnValueIndex = Arrays.asList(ds.getColumnLetterArray()).indexOf(buttonColumnValue);
        String buttonStringValue = hitButtonsList.get(0).getText().substring(1);
        int buttonStringValueIndex = Arrays.asList(ds.getStringNumberArray()).indexOf(buttonStringValue);
        for(int i = -1; i < 2; i+=2){
            try{
                String firstElement = ds.getColumnLetterArray()[buttonColumnValueIndex + i] +
                        ds.getStringNumberArray()[buttonStringValueIndex];
                String secondElement = ds.getColumnLetterArray()[buttonColumnValueIndex] +
                        ds.getStringNumberArray()[buttonStringValueIndex + i];
                for(JButton button: pdGUI.getPlayerCommonButtonList()){
                    if(button.getText().equals(firstElement) || button.getText().equals(secondElement)){
                        neighbourTurnList.add(button);
                    }
                }
            }catch(ArrayIndexOutOfBoundsException ex){}
        }
        neighbourTurnList.removeAll(missedButtonsList);
        return neighbourTurnList;
    }
    private void thirdNeighbourSearcher(){
        ArrayList<JButton> thirdTurnList = new ArrayList<>(playerNeighbourThirdAndForthTurnAmountList());
        if(thirdTurnList.isEmpty()){
            sizeListRemove(hitButtonsList.size());
            makeLimits();
            hitButtonsList.clear();
            timerTurn();
        }
        else{
            int thirdTurnListRandomIndex = (int) (Math.random()*thirdTurnList.size());
            JButton localButton = thirdTurnList.get(thirdTurnListRandomIndex);
            if(getPlayerDeckList().contains(localButton)){
                rightClickOnButton(localButton);
            }
            else {
                falseClickOnButton(localButton);
            }
        }
    }
    private void forthElementSearcher(){
        ArrayList<JButton> forthTurnList = new ArrayList<>(playerNeighbourThirdAndForthTurnAmountList());
        if(forthTurnList.isEmpty()){
            sizeListRemove(hitButtonsList.size());
            makeLimits();
            hitButtonsList.clear();
            timerTurn();
        }
        else{
            int forthTurnListRandomIndex = (int) (Math.random()*forthTurnList.size());
            JButton localButton = forthTurnList.get(forthTurnListRandomIndex);
            if(getPlayerDeckList().contains(localButton)){
                rightClickOnButton(localButton);
            }
            else {
                falseClickOnButton(localButton);
            }
        }
    }
    private ArrayList<JButton> playerNeighbourThirdAndForthTurnAmountList(){
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        DeckSettings ds = new DeckSettings();
        ArrayList<JButton> thirdAndForthTurnList = new ArrayList<>();
        String firstButtonColumnValue = hitButtonsList.get(0).getText().substring(0,1);
        int firstButtonColumnValueIndex = Arrays.asList(ds.getColumnLetterArray()).indexOf(firstButtonColumnValue);
        String firstButtonStringValue = hitButtonsList.get(0).getText().substring(1);
        int firstButtonStringValueIndex = Arrays.asList(ds.getStringNumberArray()).indexOf(firstButtonStringValue);
        String secondButtonColumnValue = hitButtonsList.get(hitButtonsList.size() - 1).getText().substring(0, 1);
        int secondButtonColumnValueIndex = Arrays.asList(ds.getColumnLetterArray()).indexOf(secondButtonColumnValue);
        String secondButtonStringValue = hitButtonsList.get(hitButtonsList.size() - 1).getText().substring(1);
        int secondButtonStringValueIndex = Arrays.asList(ds.getStringNumberArray()).indexOf(secondButtonStringValue);
        if(firstButtonColumnValue.equals(secondButtonColumnValue)) {
            for (int i = -1; i < 2; i += 2) {
                try {
                    String firstButtonString = firstButtonColumnValue +
                            (ds.getStringNumberArray()[firstButtonStringValueIndex + i]);
                    String secondButtonString = secondButtonColumnValue +
                            (ds.getStringNumberArray()[secondButtonStringValueIndex + i]);
                    for (JButton button : pdGUI.getPlayerCommonButtonList()) {
                        if (button.getText().equals(firstButtonString) ||
                                button.getText().equals(secondButtonString)) {
                            thirdAndForthTurnList.add(button);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {}
            }
        }
        else {
            for(int i = -1; i < 2; i += 2){
                try {
                    String firstButtonString = ds.getColumnLetterArray()[firstButtonColumnValueIndex + i] +
                            firstButtonStringValue;
                    String secondButtonString = ds.getColumnLetterArray()[secondButtonColumnValueIndex + i] +
                            secondButtonStringValue;
                    for (JButton button : pdGUI.getPlayerCommonButtonList()) {
                        if (button.getText().equals(firstButtonString) ||
                                button.getText().equals(secondButtonString)) {
                            thirdAndForthTurnList.add(button);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {}
            }
        }
        thirdAndForthTurnList.removeAll(hitButtonsList);
        thirdAndForthTurnList.removeAll(missedButtonsList);
        return thirdAndForthTurnList;
    }
    private void sizeListRemove(int boatSize){
        sizeList.remove((Integer) boatSize);
    }
    private void makeLimits(){
        DeckSettings ds = new DeckSettings();
        PlayerDeckGUI pdGUI = new PlayerDeckGUI();
        for(JButton button : hitButtonsList){
            String buttonColumnLetter = button.getText().substring(0, 1);
            int buttonColumnLetterIndex = Arrays.asList(ds.getColumnLetterArray()).indexOf(buttonColumnLetter);
            String buttonStringNumber = button.getText().substring(1);
            int buttonStringNumberIndex = Arrays.asList(ds.getStringNumberArray()).indexOf(buttonStringNumber);
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    try{
                        String buttonString = ds.getColumnLetterArray()[buttonColumnLetterIndex + i] +
                                ds.getStringNumberArray()[buttonStringNumberIndex + j];
                        for(JButton limitButton : pdGUI.getPlayerCommonButtonList()){
                            if(limitButton.getText().equals(buttonString)){
                                missedButtonsList.add(limitButton);
                            }
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){}
                }
            }
        }
        missedButtonsList.removeAll(hitButtonsList);
        pdGUI.getPlayerCommonButtonList().removeAll(missedButtonsList);
    }
}

