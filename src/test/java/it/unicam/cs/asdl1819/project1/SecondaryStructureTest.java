package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        Set<String> stringSet = new HashSet<>();
        testStructure.getBonds().forEach(bond -> stringSet.add(bond.toString()));
        Set<String> expected = new HashSet<>();
        expected.add("(1, 2)");
        expected.add("(5, 6)");
        assertEquals(stringSet, expected);
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
        Set<WeakBond> crossingBonds = new HashSet<>();
        crossingBonds.add(new WeakBond(1, 10));
        crossingBonds.add(new WeakBond(2, 9));
        crossingBonds.add(new WeakBond(4, 7));
        crossingBonds.add(new WeakBond(5, 12));
        SecondaryStructure testCrossingSS = new SecondaryStructure("GCAUGUGCGUGU", crossingBonds);
        Assert.assertTrue(testCrossingSS.isPseudoknotted());
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
        String expected = "GCAUGU\n" +
                "()..()";
        assertEquals(expected, testStructure.getDotBracketNotation());
    }

    @Test
    public final void testEqualsObject() {
        assertEquals(testStructure, new SecondaryStructure("GCAUGU", bonds));
    }

}
