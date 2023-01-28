package com.seaBattle.Model;

import java.util.*;
import java.util.stream.Collectors;

public class OpponentDeckAggregator extends DeckSettings {
    private LinkedList<Integer> boatSizesList;
    private ArrayList<String> deckLimitsList;
    private ArrayList<String> deckList;
    private String randomTileLetter;
    private String randomTileNumber;
    private Integer boatSize;

    public void deckAggregatorHierarchy(){
        boatSizeListCreator();
        deckLimitsList = new ArrayList<>(getDeckButtonsTextList());
        deckList = new ArrayList<>();
        while(!boatSizesList.isEmpty()){
            deckLimitsList.removeAll(boatLimitsCreator());
        }
    }
    private String startTileCreator(){
        int randomTileIndex = (int) (Math.random()* deckLimitsList.size());
        return deckLimitsList.get(randomTileIndex);
    }
    private void boatSizeListCreator(){
        boatSizesList = new LinkedList<>(Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 3, 4));
    }
    private void randomTileLetterAndNumberSplitCreator(){
        String randomTileLetterAndNumberText = startTileCreator();
        randomTileLetter = randomTileLetterAndNumberText.substring(0, 1);
        randomTileNumber = randomTileLetterAndNumberText.substring(1);
    }
    private int[] indexOfRandomTileLetterAndNumberCreator(){
        int randomTileLetterIndex =
                Arrays.asList(getColumnLetterArray()).indexOf(randomTileLetter);
        int randomTileNumberIndex =
                Arrays.asList(getStringNumberArray()).indexOf(randomTileNumber);
        return new int[]{randomTileLetterIndex, randomTileNumberIndex};
    }
    private void boatSizeCreator(){
        int boatIndex = (int) (Math.random()*boatSizesList.size());
        boatSize = boatSizesList.get(boatIndex);
    }
    private ArrayList<String> boatDirectionListCreator(){
        ArrayList<String> boatDirectionList = new ArrayList<>();
        boatDirectionList.add("North");
        boatDirectionList.add("South");
        boatDirectionList.add("West");
        boatDirectionList.add("East");
        return boatDirectionList;
    }
    private ArrayList<String> boatDirectionExceptionCreator(){
        ArrayList<String> exceptionLocalList = new ArrayList<>(boatDirectionListCreator());
        boatSizeCreator();
        boatSizesList.remove(boatSize);
        randomTileLetterAndNumberSplitCreator();
        String localNumberString = String.join("", getStringNumberArray());
        String localColumnString = String.join("", getColumnLetterArray());
        String northExceptionSubstring = localNumberString.substring(0, boatSize - 1);
        String southExceptionFirstSubstring = localNumberString.substring(localNumberString.length()-(boatSize + 1),
                localNumberString.length()-2);
        String southExceptionSecondSubstring = localNumberString.substring(localNumberString.length()
                - 2);
        if(northExceptionSubstring.contains(randomTileNumber)){
            exceptionLocalList.remove("North");
        } else if(southExceptionFirstSubstring.contains(randomTileNumber) ||
                southExceptionSecondSubstring.equals(randomTileNumber)){
            exceptionLocalList.remove("South");
        }
        String westExceptionSubstring = localColumnString.substring(0, boatSize - 1);
        String eastExceptionSubstring = localColumnString.substring(localColumnString.length() -
                boatSize);
        if(westExceptionSubstring.contains(randomTileLetter)){
            exceptionLocalList.remove("West");
        } else if(eastExceptionSubstring.contains(randomTileLetter)){
            exceptionLocalList.remove("East");
        }
        return exceptionLocalList;
    }
    private String randomDirectionCreator(){
        ArrayList<String> localDirectionList = new ArrayList<>(boatDirectionExceptionCreator());
        int randomDirectionIndex = (int) (Math.random()*localDirectionList.size());
        return localDirectionList.get(randomDirectionIndex);
    }
    private ArrayList<String> boatCreator(){
        String boatCreatorDirection = randomDirectionCreator();
        int[] indexArray = indexOfRandomTileLetterAndNumberCreator();
        int letterIndex = indexArray[0];
        int numberIndex = indexArray[1];
        ArrayList<String> localBoatList = new ArrayList<>();
        switch (boatCreatorDirection) {
            case "North":
                for (int i = 0; i < boatSize; i++) {
                    localBoatList.add(getColumnLetterArray()[letterIndex] + getStringNumberArray()[numberIndex - i]);
                }
                break;
            case "South":
                for (int i = 0; i < boatSize; i++) {
                    localBoatList.add(getColumnLetterArray()[letterIndex] + getStringNumberArray()[numberIndex + i]);
                }
                break;
            case "West":
                for (int i = 0; i < boatSize; i++) {
                    localBoatList.add(getColumnLetterArray()[letterIndex - i] + getStringNumberArray()[numberIndex]);
                }
                break;
            default:
                for (int i = 0; i < boatSize; i++) {
                    localBoatList.add(getColumnLetterArray()[letterIndex + i] + getStringNumberArray()[numberIndex]);
                }
                break;
        }
        return localBoatList;
    }
    private ArrayList<String> boatCreatorTester(){
        ArrayList<String> localBoatList = new ArrayList<>(boatCreator());
        while(!deckLimitsList.containsAll(localBoatList)){
            boatSizesList.add(boatSize);
            localBoatList = new ArrayList<>(boatCreator());
        }
        deckList.addAll(localBoatList);
        System.out.println("step");
        return localBoatList;
    }
    private List<String> boatLimitsCreator(){
        ArrayList<String> localBoatList = new ArrayList<>(boatCreatorTester());
        List<String> localBoatLimitsList = new ArrayList<>();
        for(String localBoatUnit : localBoatList){
            String localBoatUnitLetter = localBoatUnit.substring(0, 1);
            String localBoatUnitNumber = localBoatUnit.substring(1);
            int localBoatUnitLetterIndex = Arrays.asList(getColumnLetterArray()).indexOf(localBoatUnitLetter);
            int localBoatUnitNumberIndex = Arrays.asList(getStringNumberArray()).indexOf(localBoatUnitNumber);
            for(int i = localBoatUnitLetterIndex - 1; i < localBoatUnitLetterIndex + 2; i++){
                for(int j = localBoatUnitNumberIndex - 1; j < localBoatUnitNumberIndex + 2; j++){
                    try {
                        localBoatLimitsList.add(getColumnLetterArray()[i] + getStringNumberArray()[j]);
                    }catch(ArrayIndexOutOfBoundsException ex){}
                }
            }
        }
        return localBoatLimitsList.stream().distinct().collect(Collectors.toList());
    }
    public ArrayList<String> getDeckList(){
        return deckList;
    }
}
