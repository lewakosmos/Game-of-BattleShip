package com.seaBattle.Model;

import java.util.ArrayList;

public class DeckSettings {
    private final String[] columnLetterArray = {"A","B","C","D","E","F","G","H","I","J"};
    private final String[] stringNumberArray = {"1","2","3","4","5","6","7","8","9","10"};
    private static final ArrayList<String> deckButtonsTextList = new ArrayList<>();
    public void deckButtonTextListCreator(){
        for(String stringNumber : stringNumberArray){
            for(String columnLetter : columnLetterArray){
                deckButtonsTextList.add(columnLetter + stringNumber);
            }
        }
    }
    public String[] getStringNumberArray(){
        return stringNumberArray;
    }
    public String[] getColumnLetterArray(){
        return columnLetterArray;
    }
    public ArrayList<String> getDeckButtonsTextList(){
        return deckButtonsTextList;
    }
}
