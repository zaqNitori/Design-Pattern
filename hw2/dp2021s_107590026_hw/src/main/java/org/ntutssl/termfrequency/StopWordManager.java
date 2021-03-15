package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Set;

public class StopWordManager implements IStopWordManager 
{
    List<String> list;
    Set<String> set;

    public StopWordManager(String filePath, IOHandler ioHandler) 
    { 
        String pattern = ",";
        list = ioHandler.handleInputAsList(filePath, pattern);
        set = ioHandler.handleInputAsSet(filePath, pattern);
        for(char c = 'A'; c <= 'Z' ;c++)                //insert alphabet
        {
            list.add(String.valueOf(c));
            list.add(String.valueOf((char)(c+32)));
        }
    }

    public boolean isStopWordList(String word) 
    { 
        word = word.toLowerCase();
        return list.contains(word);
    }
                    
    public boolean isStopWordSet(String word) 
    { 
        word = word.toLowerCase();
        return set.contains(word);
    }
}