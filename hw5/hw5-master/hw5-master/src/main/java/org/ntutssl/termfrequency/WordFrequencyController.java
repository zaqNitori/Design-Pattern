package org.ntutssl.termfrequency;

public class WordFrequencyController implements EventListener{
  private EventManager eventManager;
  public WordFrequencyController(EventManager eventManager) {
    this.eventManager=eventManager;
    this.eventManager.subscribe(EventType.START, this);
   }

  public void onEvent(EventType eventType, String event) {
    if(EventType.START==eventType) 
      this.startEvent(event);
  }

  private void startEvent(String filePath){
    this.eventManager.publish(EventType.LOAD, filePath);
    this.eventManager.publish(EventType.RUN, "");
  }
}