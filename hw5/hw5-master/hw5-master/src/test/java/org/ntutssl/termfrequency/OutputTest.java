package org.ntutssl.termfrequency;
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class OutputTest{

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void outOfRangeUpperLimitExceptionIsWork(){
            EventManager em = new EventManager();
            Output output = new Output(em, 3, "des");
            exceptionRule.expect(WordFrequencyException.class);
            exceptionRule.expectMessage("Out of range! The range should be from 1 to 2.");
            em.publish(EventType.OUTPUT, "Test: 1\nFile: 2\n");
    }

    @Test
    public void outOfRangeLowerLimitExceptionIsWork(){
        EventManager em = new EventManager();
        Output output = new Output(em, -1, "des");
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("Out of range! The range should be from 1 to 2.");
        em.publish(EventType.OUTPUT, "Test: 1\nFile: 2\n");
    }

    @Test
    public void orderInvalidExceptionIsWork(){
        EventManager em = new EventManager();
        Output output = new Output(em, 1, "test");
        exceptionRule.expect(WordFrequencyException.class);
        exceptionRule.expectMessage("The order should be \"asc\" or \"des\".");
        em.publish(EventType.OUTPUT, "Test: 1\nFile: 2\n");
    }
 }
