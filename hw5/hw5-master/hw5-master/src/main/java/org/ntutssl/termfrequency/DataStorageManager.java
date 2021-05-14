package org.ntutssl.termfrequency;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataStorageManager implements EventListener{
  private List<String> dataWords;
  private EventManager eventManager;
  public DataStorageManager(EventManager eventManager) {
      this.dataWords = new ArrayList<>();
      this.eventManager=eventManager;
      this.eventManager.subscribe(EventType.LOAD, this);
      this.eventManager.subscribe(EventType.RUN, this);
   }

  public List<String> getWords() {
      return this.dataWords;
   }

  public void onEvent(EventType eventType, String event) {
    if(eventType==EventType.LOAD)
      LoadEvent(event);
    if(eventType==EventType.RUN)
      runEvent();
  }
  private void LoadEvent(String filePath){
    this.dataWords = new ArrayList<>();
    try(Scanner sc = new Scanner(new File(filePath), "UTF-8")){
        sc.useDelimiter("[\\W_]+");
        while(sc.hasNext()){
            this.dataWords.add(sc.next().toLowerCase());
      }
    }catch(FileNotFoundException e){
      throw new WordFrequencyException("File not found.");
    }
  }

  private void runEvent(){
    for(String word : this.dataWords){
      this.eventManager.publish(EventType.VALIDATE, word);
    }
    this.eventManager.publish(EventType.EOF, "successfully");
  }
}