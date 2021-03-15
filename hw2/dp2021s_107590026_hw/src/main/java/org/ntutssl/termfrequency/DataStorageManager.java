package org.ntutssl.termfrequency;

import java.util.List;
import java.util.ArrayList;

public class DataStorageManager implements IDataStorageManager {
    
    private List<String> wordsList;
    
    public DataStorageManager(String filePath, IOHandler ioHandler) 
    { 
        String pattern = "[\\W_]+";
        wordsList = new ArrayList<String>();
        wordsList = ioHandler.handleInputAsList(filePath, pattern);
    }

    public List<String> getWords() { return wordsList; }

}