package org.ntutssl.termfrequency;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyController{
    public static void main(String[] args){ 
        System.out.println("Program Started!!!");

        String order = args[3];
        int range;

        DataStorageManager dsm = new DataStorageManager(args[1]/*"input/pride-and-prejudice.txt"*/);
        StopWordManager swm = new StopWordManager(args[0]/*"input/stop_words.txt"*/);
        WordFrequencyManager wfm = new WordFrequencyManager();

        if(dsm.isFileOpenCorrectly() == false)              //dsm get no file
        {
            System.out.println("The file path of text to be counted is not correct.");
            return;
        }
        else if(swm.isFileOpenCorrectly() == false)         //swm get no file
        {
            System.out.println("The file path of stop_words.txt is not correct.");
            return;
        }

        if(order.compareTo("des")!=0 && order.compareTo("asc")!=0 )           //order command incorrect
        {
            System.out.println("Incorrect sorting order!");
            return;
        }

        List<String> wordsList = dsm.getWords();
        for(String s:wordsList)
        {
            if(swm.isStopWordSet(s) == false)
                wfm.incrementCount(s);
        }

        try {                                               
            range = Integer.parseInt(args[2]);      
            if(range > wfm.getNumOfWords() || range < 1)    //Incorrect input range
            {
                System.out.println("Incorrect input range!");
                return;
            }
        } catch (NumberFormatException e) {                 //input range cannot convert to int
            System.out.println("Input range cannot convert to int!");   
            return;
        }

        Map<String,Integer> wordMap = new LinkedHashMap<String,Integer>();
        if(order.compareTo("asc") == 0) wordMap = wfm.getWordFrequencyAscending();
        else wordMap = wfm.getWordFrequencyDescending();
        

        for(Map.Entry<String,Integer> mp:wordMap.entrySet())
        {
            if(range--<=0) break;
            System.out.println(mp.getKey() + ": " + mp.getValue());
        }

        System.out.println("Program Finish Successfully!!!");
    }

}