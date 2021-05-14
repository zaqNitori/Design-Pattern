package org.ntutssl.termfrequency;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;
public class DataStorageManagerTest implements EventListener {
    public int validateNum = 0;
    public int eofNum = 0;
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.VALIDATE)
            this.validateNum++;
        if(eventType == EventType.EOF)
            this.eofNum++;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void fileNotFoundSetExceptionIsWork(){
        EventManager em = new EventManager();
        DataStorageManager dsm = new DataStorageManager(em);
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("File not found.");
        em.publish(EventType.LOAD, "notFound.txt");
    }


    @Test
    public void DataStorageManagerShouldAcceptFileWithEvent(){
            EventManager em = new EventManager();
            DataStorageManager dsm = new DataStorageManager(em);
            em.publish(EventType.LOAD, "input/test.txt");
            List<String> testFileList = new ArrayList<>();
            testFileList.add("test");
            testFileList.add("file");
            testFileList.add("test");
            testFileList.add("hello");
            testFileList.add("good");
            testFileList.add("bad");
            testFileList.add("bad");
            testFileList.add("bad");
            assertEquals(dsm.getWords(),testFileList);
    }
    @Test
    public void DataStorageManagerShouldAcceptRunAndPublishValidate(){
            validateNum = 0;
            EventManager em = new EventManager();
            DataStorageManager dsm = new DataStorageManager(em);
            em.subscribe(EventType.VALIDATE, this);
            em.publish(EventType.LOAD, "input/test.txt");
            em.publish(EventType.RUN, "Run");
            assertEquals(this.validateNum,8);
    }
    @Test
    public void DataStorageManagerShouldAcceptRunPublishEof(){
            eofNum = 0;
            EventManager em = new EventManager();
            DataStorageManager dsm = new DataStorageManager(em);
            em.subscribe(EventType.EOF, this);
            em.publish(EventType.LOAD, "input/test.txt");
            em.publish(EventType.RUN, "Run");
            assertEquals(this.eofNum,1);
    }
 }
