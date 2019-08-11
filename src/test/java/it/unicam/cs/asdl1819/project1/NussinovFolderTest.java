package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NussinovFolderTest {

    private NussinovFolder testFolder;

    @Before
    public void setUp() throws Exception {
        testFolder = new NussinovFolder("GCACGACG");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testNussinovFolder() {

    }

    @Test
    public final void testGetName() {
        Assert.assertEquals("NussinovFolder", testFolder.getName());
    }

    @Test
    public final void testGetSequence() {
        assertEquals("GCACGACG", testFolder.getSequence());
    }

    @Test
    public final void testGetOneOptimalStructure() {
        testFolder.fold();
        String expectedOptimal = "{(1, 2), (5, 7), (4, 8)}";
        assertEquals(expectedOptimal, testFolder.getOneOptimalStructure().toString());
    }

    @Test
    public final void testFold() {
        testFolder.fold();
        String expectedOptimal = "{(1, 2), (5, 7), (4, 8)}";
        assertEquals(expectedOptimal, testFolder.getOneOptimalStructure().toString());
    }

    @Test
    public final void testIsFolded() {
        NussinovFolder nf = new NussinovFolder("AGG");
        nf.fold();
        assertFalse(nf.isFolded());
        testFolder.fold();
        assertTrue(testFolder.isFolded());
    }

}