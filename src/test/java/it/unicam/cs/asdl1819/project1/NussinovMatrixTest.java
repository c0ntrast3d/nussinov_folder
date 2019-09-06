package it.unicam.cs.asdl1819.project1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NussinovMatrixTest {

    private NussinovMatrix testMatrix;

    @Before
    public final void setUp() {
        testMatrix = new NussinovMatrix(2);
    }

    @Test
    public final void testMatrixDimension() {
        Assert.assertEquals(2, testMatrix.getRows());
        Assert.assertEquals(3, testMatrix.getCols());
    }

    @Test
    public final void testCellInsertion() {
        testMatrix.setCell(1, 1, 10);
        Assert.assertEquals(10, testMatrix.getCell(1, 1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public final void testSetCellOutOfMatrixDimension() {
        testMatrix.setCell(4, 5, 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public final void testGetCellOutOfMatrixDimension() {
        testMatrix.getCell(4, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testSetCellWithNegativeValue() {
        testMatrix.setCell(1, 1, -1);
    }

    @Test
    public final void testToString() {
        String toStringResult = testMatrix.toString();
        Assert.assertEquals(" 0 | 0 | 0 |\n 0 | 0 | 0 |\n", toStringResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testStringifyMatrixNotEqualToMatrixDimension() {
        testMatrix.stringifyMatrix("AUGAAUH");
    }

    @Test
    public final void testStringifyMatrix() {
        NussinovMatrix nm = new NussinovMatrix(8);
        String stringified = nm.stringifyMatrix("GCACGACG");
        String expected =
                "        G   C   A   C   G   A   C   G\n" +
                        "G | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "C | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "A | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "C | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "G | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "A | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "C | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n" +
                        "G | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\n";
        Assert.assertEquals(expected, stringified);
    }
}
