package org.ntutssl.termfrequency;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.xml.crypto.dsig.CanonicalizationMethod;

import java.util.HashSet;
import java.io.File;
import java.io.IOException;


public class IOHandler {

    private List<String> list;
    private Set<String> set;
    private boolean canOpenFile = true;

    public IOHandler() 
    { 
        list = new ArrayList<String>();
        set = new HashSet<String>();
    }

    public boolean isFileOpen() { return canOpenFile; }

    public List<String> handleInputAsList(String filePath, String pattern) 
    { 
        list.clear();
        String s;
        try(Scanner sc = new Scanner(new File(filePath)))
        {
            sc.useDelimiter(pattern);
            while(sc.hasNext())                         
            {
                s = sc.next();
                s = s.toLowerCase();
                list.add(s);                            //insert into list
            }
            canOpenFile = true;
        }catch(IOException io)
        {
            canOpenFile = false;
            System.out.println(filePath + " File not exist.");
        }
        return list;
    }

    public Set<String> handleInputAsSet(String filePath, String pattern) 
    { 
        set.clear();
        String s;
        try(Scanner sc = new Scanner(new File(filePath)))
        {
            sc.useDelimiter(pattern);
            while(sc.hasNext())                         
            {
                s = sc.next();
                s = s.toLowerCase();
                set.add(s);                             //insert into set
            }
            canOpenFile = true;
            for(char c = 'A'; c <= 'Z' ;c++)                //insert alphabet
            {
                set.add(String.valueOf(c));
                set.add(String.valueOf((char)(c+32)));
            }
            set.add("");
        }catch(IOException io)
        {
            canOpenFile = false;
            System.out.println(filePath + " File not exist.");
        }
        return set;
    }

    public void handleOutput(String outputPath, int range, List<String> data)
    { 
        //output data to a txt file
    }
}