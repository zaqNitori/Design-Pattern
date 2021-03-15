package org.ntutssl.termfrequency;

import javax.swing.SortOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyManagerStream implements IWordFrequencyManager {

    private Map<String,Integer> _WordFreqMap;

    public WordFrequencyManagerStream() 
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
        List<String> li = new ArrayList<>();

        if(order == SortOrder.UNSORTED)
            li = _WordFreqMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        else
        {
            if(order == SortOrder.ASCENDING)
                li = _WordFreqMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            else 
            {
                li = _WordFreqMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey).collect(Collectors.toList());
            }
        }

        List<String> newli = new ArrayList<>();
        for(String s:li)
            newli.add(s + ": " + _WordFreqMap.get(s) + "\n");
        return newli; 
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
        
        if(order.compareTo("asc") != 0 && order.compareTo("des") != 0)
            throw new WordFrequencyException("The order should be \"asc\" or \"des\".");

        if(range < 1 || range > this.getNumOfWords())
            throw new WordFrequencyException("Out of range! The range should be from 1 to " + _WordFreqMap.size() + ".");

        if(order.compareTo("asc") == 0)
            handler.handleOutput(outputPath, range, this.getWordFrequency(SortOrder.ASCENDING));
        else if(order.compareTo("des") == 0)
            handler.handleOutput(outputPath, range, this.getWordFrequency(SortOrder.DESCENDING));
    }
}