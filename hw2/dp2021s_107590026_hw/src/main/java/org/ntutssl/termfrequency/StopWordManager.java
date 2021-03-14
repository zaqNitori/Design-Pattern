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