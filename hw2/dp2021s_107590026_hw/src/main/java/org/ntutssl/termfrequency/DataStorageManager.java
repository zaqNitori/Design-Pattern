package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class DataStorageManager implements IDataStorageManager {
    
    private List<String> wordsList;
    private Set<String> wordsSet;
    
    public DataStorageManager(String filePath, IOHandler ioHandler) 
    { 
        String pattern = "[\\W_]+";
        wordsList = new ArrayList<String>();
        wordsList = ioHandler.handleInputAsList(filePath, pattern);
        wordsSet = new HashSet<>();
        wordsSet = ioHandler.handleInputAsSet(filePath, pattern);
    }

    public List<String> getWords() { return wordsList; }

    public Set<String> getSet() { return wordsSet; }
}