package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SecondaryStructureTest {

    private Set<WeakBond> bonds = new HashSet<>();
    private SecondaryStructure testStructure;


    @Before
    public void setUp() throws Exception {
        bonds.add(new WeakBond(1, 2));
        bonds.add(new WeakBond(5, 6));
        testStructure = new SecondaryStructure("GCAUGU", bonds);
    }

    @Test
    public final void testHashCode() {
        /* Hash codes of two identical structures should be the same */
        Assert.assertEquals(
                new SecondaryStructure("GCAUGU").hashCode(),
                new SecondaryStructure("GCAUGU").hashCode()
        );
    }

    @Test
    public final void testSecondaryStructureString() {
        assertEquals("{(1, 2), (5, 6)}", testStructure.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testSecondaryStructureValidCoupling() {
        new SecondaryStructure("GCAUCU", bonds);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testSecondaryStructureAddExistingBond() {
        bonds.add(new WeakBond(1, 6));
        new SecondaryStructure("GCAUGU", bonds);
    }

    @Test
    public final void testSecondaryStructureStringSetOfWeakBond() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetPrimarySequence() {
        assertEquals("GCAUGU", testStructure.getPrimarySequence());
    }

    @Test
    public final void testGetBonds() {
        Assert.assertTrue(testStructure.getBonds().size() > 0);
    }

    @Test
    public final void testIsPseudoknotted() {
        Set<WeakBond> testBonds = new HashSet<>();
        testBonds.add(new WeakBond(1, 10));
        testBonds.add(new WeakBond(2, 9));
        testBonds.add(new WeakBond(4, 7));
        testBonds.add(new WeakBond(5, 12));
        SecondaryStructure testSS = new SecondaryStructure("GCAUGUGCGUGU", testBonds);
        Assert.assertTrue(testSS.isPseudoknotted());
        Assert.assertFalse(testStructure.isPseudoknotted());
    }

    @Test
    public final void testAddBond() {
        assertTrue(testStructure.addBond(new WeakBond(3, 4)));
    }

    @Test
    public final void testGetCardinality() {
        assertEquals(2, testStructure.getCardinality());
    }

    @Test
    public final void testGetDotBracketNotation() {
        Set<WeakBond> testBonds = new HashSet<>();
        testBonds.add(new WeakBond(5, 11));
        testBonds.add(new WeakBond(4, 12));
        testBonds.add(new WeakBond(3, 13));
        testBonds.add(new WeakBond(2, 14));
        testBonds.add(new WeakBond(1, 15));
        testBonds.add(new WeakBond(20, 26));
        testBonds.add(new WeakBond(19, 27));
        testBonds.add(new WeakBond(18, 28));
        testBonds.add(new WeakBond(17, 29));
        testBonds.add(new WeakBond(16, 30));
        testBonds.add(new WeakBond(44, 50));
        testBonds.add(new WeakBond(43, 51));
        testBonds.add(new WeakBond(42, 52));
        testBonds.add(new WeakBond(41, 53));
        testBonds.add(new WeakBond(40, 54));
        SecondaryStructure testSS = new SecondaryStructure("UUGAUUACGGAUCAAUUGAUUACGGAUCAAGACUACGGUUUGAUUACGGAUCAA", testBonds);
        String expected = "UUGAUUACGGAUCAAUUGAUUACGGAUCAAGACUACGGUUUGAUUACGGA\n" +
                "UCAA\n" +
                "(((((.....)))))(((((.....))))).........(((((.....)\n" +
                "))))";
        System.out.println(testSS.getDotBracketNotation());
        assertEquals(expected, testSS.getDotBracketNotation());
    }

    @Test
    public final void testEqualsObject() {
        assertTrue(testStructure.equals(new SecondaryStructure("GCAUGU", bonds)));
    }

}
