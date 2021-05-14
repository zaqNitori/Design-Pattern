package org.ntutssl.termfrequency;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EventManager {
  Map<EventType, List<EventListener>> bulletin;
  public EventManager(){
    this.bulletin = new HashMap<>();
  }

  public void subscribe(EventType eventType, EventListener listener) {
    if(this.bulletin.containsKey(eventType)){
      List<EventListener> listeners = this.bulletin.get(eventType);
      listeners.add(listener);
      this.bulletin.put(eventType,listeners);
    }
    else{
      List<EventListener> listeners = new ArrayList<>();
      listeners.add(listener);
      this.bulletin.put(eventType,listeners);
    }
  }

  public void publish(EventType eventType, String event) {
    if(this.bulletin.get(eventType)!=null)
      for(EventListener obj : this.bulletin.get(eventType))
        obj.onEvent(eventType, event);
  }
}