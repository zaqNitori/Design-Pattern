package org.ntutssl.termfrequency;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class IOHandler {

    public IOHandler() 
    { 
    }

    public List<String> handleInputAsList(String filePath, String pattern) 
    { 
        List<String> list = new ArrayList<>();
        list.clear();
        String s;
        try(Scanner sc = new Scanner(new File(filePath),"UTF-8"))
        {
            sc.useDelimiter(pattern);
            while(sc.hasNext())                         
            {
                s = sc.next();
                s = s.toLowerCase();
                list.add(s);                            //insert into list
            }
        }catch(IOException io)
        {
            throw new WordFrequencyException("File not found.");
        }
        return list;
    }

    public Set<String> handleInputAsSet(String filePath, String pattern) 
    { 
        Set<String> set = new HashSet<>();
        set.clear();
        String s;
        try(Scanner sc = new Scanner(new File(filePath)))
        {
            sc.useDelimiter(pattern);
            while(sc.hasNext())                         
            {
                s = sc.next();
                s = s.toLowerCase();
                set.add(s);                                 //insert into set
            }
        }catch(IOException io)
        {
            throw new WordFrequencyException("File not found.");
        }
        return set;
    }

    public void handleOutput(String outputPath, int range, List<String> data)
    { 
        //output data to a txt file
        try(FileWriter writeFile = new FileWriter(outputPath)) {
            for(String s:data)
            {
                if(range-- == 0) break;
                writeFile.write(s);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}