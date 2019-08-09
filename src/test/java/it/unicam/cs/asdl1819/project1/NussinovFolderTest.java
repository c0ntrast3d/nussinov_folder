package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NussinovFolderTest {

    private NussinovFolder testFolder;

    @Before
    public void setUp() throws Exception {
        testFolder = new NussinovFolder("AUGU");
    }

    @Test
    public final void testNussinovFolder() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetName() {
        Assert.assertEquals("NussinovFolder", testFolder.getName());
    }

    @Test
    public final void testGetSequence() {
        assertEquals("AUGU", testFolder.getSequence());
    }

    @Test
    public final void testGetOneOptimalStructure() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testFold() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testIsFolded() {
        assertFalse(testFolder.isFolded());
    }

}
