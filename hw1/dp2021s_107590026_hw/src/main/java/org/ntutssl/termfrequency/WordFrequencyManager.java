package org.ntutssl.termfrequency;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordFrequencyManager{
    private Map<String,Integer> _WordFreqMap;
    
    public WordFrequencyManager(){
        _WordFreqMap = new HashMap<String,Integer>();
    }

    public void incrementCount(String word){
        word = word.toLowerCase();
        if(_WordFreqMap.containsKey(word))
            _WordFreqMap.put(word,_WordFreqMap.get(word)+1);
        else
            _WordFreqMap.put(word,1);
    }

    public int getNumOfWords(){ return _WordFreqMap.size(); }

    public Integer getCount(String word){ 
        if(_WordFreqMap.containsKey(word))
            return _WordFreqMap.get(word);
        else
            return 0;
    }

    public Map<String,Integer> getWordFrequencyDescending(){

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(_WordFreqMap.entrySet()); //轉換為list

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        
        Map<String,Integer> mp = new LinkedHashMap<String,Integer>();
        for (Map.Entry<String, Integer> mapping : list){
            mp.put(mapping.getKey(), mapping.getValue());
        } 

        return mp;
    }

    public Map<String, Integer> getWordFrequencyAscending(){ 
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(_WordFreqMap.entrySet()); //轉換為list

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        
        Map<String,Integer> mp = new LinkedHashMap<String,Integer>();
        for (Map.Entry<String, Integer> mapping : list){
            mp.put(mapping.getKey(), mapping.getValue());
        } 

        return mp;
    }
}