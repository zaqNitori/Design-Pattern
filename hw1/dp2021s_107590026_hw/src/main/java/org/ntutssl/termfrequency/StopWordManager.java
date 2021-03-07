package org.ntutssl.termfrequency;

import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


import java.util.HashSet;

public class StopWordManager{

    private List<String> list;
    private Set<String> set;
    private boolean canOpenFile = true;

    public StopWordManager(String filePath){
        String s;
        list = new ArrayList<String>();
        set = new HashSet<String>();
        try(Scanner sc = new Scanner(new File(filePath)))
        {
            sc.useDelimiter(",");
            while(sc.hasNext())                         
            {
                s = sc.next();
                s = s.toLowerCase();
                list.add(s);                            //insert into list
                set.add(s);                             //insert into set
            }
        }catch(IOException io)
        {
            canOpenFile = false;
            System.out.println(filePath + " File not exist.");
        }
        for(char c = 'A'; c <= 'Z' ;c++)                //insert alphabet
        {
            list.add(String.valueOf(c));
            list.add(String.valueOf((char)(c+32)));
            set.add(String.valueOf(c));
            set.add(String.valueOf((char)(c+32)));
        }
        set.add("");
    }

    public boolean isFileOpenCorrectly() { return canOpenFile; }

    public boolean isStopWordList(String word){
        word = word.toLowerCase();
        return list.contains(word); 
    }

    public boolean isStopWordSet(String word){
        word = word.toLowerCase();
        return set.contains(word); 
    }
}