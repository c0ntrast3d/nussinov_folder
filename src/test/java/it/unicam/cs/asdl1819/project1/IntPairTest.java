package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntPairTest {

    private IntPair testPair;

    @Before
    public void setUp() {
        testPair = new IntPair(1, 2);
    }

    @Test
    public final void testToString() {
        String expected = String.format("Left : %d | Right : %d", 1, 2);
        Assert.assertEquals(expected, testPair.toString());
    }

}
