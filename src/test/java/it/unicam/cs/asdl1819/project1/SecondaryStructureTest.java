package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.fail;

public class SecondaryStructureTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public final void testHashCode() {

        /* Hash codes of two identical structures should be the same */
        Assert.assertEquals(
                new SecondaryStructure("AUGA").hashCode(),
                new SecondaryStructure("AUGA").hashCode()
        );

    }

    @Test
    public final void testSecondaryStructureString() {
        Set<WeakBond> bonds = new HashSet<>();
        bonds.add(new WeakBond(1, 2));
        bonds.add(new WeakBond(3, 4));
        bonds.add(new WeakBond(5, 6 ));
        bonds.add(new WeakBond(5, 6 ));

        System.out.println(new SecondaryStructure("AUGA", bonds).toString());
        System.out.println(new SecondaryStructure("AUGA").hashCode());

        // fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testSecondaryStructureStringSetOfWeakBond() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetPrimarySequence() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetBonds() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testIsPseudoknotted() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testAddBond() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetCardinality() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testGetDotBracketNotation() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testEqualsObject() {
        fail("Not yet implemented"); // TODO
    }

}
