package org.ntutssl.termfrequency;
import static org.junit.Assert.*;
import org.junit.Test;
public class EventManagerTest implements EventListener{
    public String startEvent;
    public String loadEvent;
    public String runEvent;
    public String validateEvent;
    public String countEvent;
    public String eofEvent;
    public String outputEvent;
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.START)
            this.startEvent = event;
        if(eventType == EventType.LOAD)
            this.loadEvent = event;
        if(eventType == EventType.RUN)
            this.runEvent = event;
        if(eventType == EventType.VALIDATE)
            this.validateEvent = event;
        if(eventType == EventType.COUNT)
            this.countEvent = event;
        if(eventType == EventType.EOF)
            this.eofEvent = event;
        if(eventType == EventType.OUTPUT)
            this.outputEvent = event;
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishStartEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.START, this);
            em.publish(EventType.START, "Test");
            assertEquals(this.startEvent,"Test");
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishLoadEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.LOAD, this);
            em.publish(EventType.LOAD, "Test");
            assertEquals(this.loadEvent,"Test");
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishRunEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.RUN, this);
            em.publish(EventType.RUN, "Test");
            assertEquals(this.runEvent,"Test");
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishValidateEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.VALIDATE, this);
            em.publish(EventType.VALIDATE, "Test");
            assertEquals(this.validateEvent,"Test");
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishCountEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.COUNT, this);
            em.publish(EventType.COUNT, "Test");
            assertEquals(this.countEvent,"Test");
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishEofEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.EOF, this);
            em.publish(EventType.EOF, "Test");
            assertEquals(this.eofEvent,"Test");
    }
    @Test
    public void EventManagerShouldCanSubscribeAndPublishOutputEvent(){
            EventManager em = new EventManager();
            em.subscribe(EventType.OUTPUT, this);
            em.publish(EventType.OUTPUT, "Test");
            assertEquals(this.outputEvent,"Test");
    }
 }