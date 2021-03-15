package org.ntutssl.termfrequency;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import javax.swing.SortOrder;
import java.util.Collections;
import java.util.Comparator;

public class WordFrequencyManager implements IWordFrequencyManager 
{

    private Map<String,Integer> _WordFreqMap;

    public WordFrequencyManager() 
    { 
        _WordFreqMap = new HashMap<>();
    }

    public void incrementCount(String word) 
    { 
        word = word.toLowerCase();
        if(_WordFreqMap.containsKey(word))
            _WordFreqMap.put(word,_WordFreqMap.get(word)+1);
        else
            _WordFreqMap.put(word,1);
    }

    public int getNumOfWords() { return _WordFreqMap.size(); }

    public Integer getCount(String word){ 
        if(_WordFreqMap.containsKey(word))
            return _WordFreqMap.get(word);
        else
            return 0;
    }

    public List<String> getWordFrequency(SortOrder order) 
    { 
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(_WordFreqMap.entrySet()); //轉換為list

        if(order.equals(SortOrder.ASCENDING))
        {
            Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
                public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
        }
        else if(order.equals(SortOrder.DESCENDING))
        {
            Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
                public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                    return -o1.getValue().compareTo(o2.getValue());
                }
            });
        }
        
        List<String> li = new ArrayList<>();
        for (Map.Entry<String, Integer> mapping : list){
            li.add(mapping.getKey() + ": " + String.valueOf(mapping.getValue()) + "\n");
        }

        return li;
    }

    public void output(
        String outputPath,
        String order,
        int range,
        IOHandler handler
    )
    { 
        if(this.getWordFrequency(SortOrder.UNSORTED).size() == 0)
            throw new WordFrequencyException("Word not found.");
        
        if(order.compareTo("asc")!=0 && order.compareTo("des")!=0)
            throw new WordFrequencyException("The order should be \"asc\" or \"des\".");

        if(range < 1 || range > this.getNumOfWords())
            throw new WordFrequencyException("Out of range! The range should be from 1 to " + _WordFreqMap.size() + ".");
        
        if(order == "asc")
            handler.handleOutput(outputPath, range, this.getWordFrequency(SortOrder.ASCENDING));
        else if(order == "des")
            handler.handleOutput(outputPath, range, this.getWordFrequency(SortOrder.DESCENDING));
    }
}