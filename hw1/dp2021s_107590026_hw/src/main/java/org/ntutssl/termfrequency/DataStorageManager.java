package org.ntutssl.termfrequency;

import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class DataStorageManager{

    private List<String> wordsList;
    private Set<String> wordsSet;
    private boolean canOpenFile = true;

    public DataStorageManager(String filePath){
        wordsList = new ArrayList<String>();
        wordsSet = new HashSet<String>();
        try(Scanner sc = new Scanner(new File(filePath)))
        {
            String s;
            Pattern pattern = Pattern.compile("[\\W_]+");
            sc.useDelimiter(pattern);
            while(sc.hasNext())
            {
                s = sc.next();
                wordsSet.add(s);
                wordsList.add(s);
            }
        }catch (IOException e) {
            canOpenFile = false;
            System.out.println(filePath + " File not exist");
        }
    }

    public boolean isFileOpenCorrectly() { return canOpenFile; }

    public Set<String> getSet(){
        return wordsSet;
    }

    public List<String> getWords(){
        return wordsList;
    }
}