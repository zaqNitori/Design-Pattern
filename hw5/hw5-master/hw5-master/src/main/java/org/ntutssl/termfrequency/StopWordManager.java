package org.ntutssl.termfrequency;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class StopWordManager implements EventListener{
  private EventManager eventManager;
  private List<String> word_List;
  public StopWordManager(EventManager eventManager) {
    this.word_List = new ArrayList<>();
    this.eventManager=eventManager;
    this.eventManager.subscribe(EventType.LOAD, this);
    this.eventManager.subscribe(EventType.VALIDATE, this);
  }

  public List<String> getStopWords() {
    return this.word_List;
  }

  public void onEvent(EventType eventType, String event) {
    if(eventType == EventType.LOAD)
      this.LoadEvent();
    if(eventType == EventType.VALIDATE)
      this.validateEvent(event);
  }

  private void validateEvent(String word){
    if(!this.word_List.contains(word)) this.eventManager.publish(EventType.COUNT, word);
  }

  private void LoadEvent(){
    this.word_List = new ArrayList<>();
    try(Scanner sc = new Scanner(new File("input/stop_words.txt"))){
      sc.useDelimiter(",");
      while(sc.hasNext()){
        String s = sc.next();
        this.word_List.add(s);
      }
      for (char c = 'a'; c <= 'z'; c++) {
        if(!this.word_List.contains("" + c))
          this.word_List.add("" + c);
      }
      }catch(IOException e){
        throw new WordFrequencyException("File not found.");
      }
  }
}