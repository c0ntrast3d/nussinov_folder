/**
 *
 */
package it.unicam.cs.asdl1819.project1;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementazione dell'algoritmo di Nussinov per trovare, data una sequenza di
 * nucleotidi, una struttura secondaria senza pseudonodi che ha un numero
 * massimo di legami deboli.
 *
 * @author Luca Tesei
 *
 */
public class NussinovFolder implements FoldingAlgorithm {

    private final String primarySequence;
    private final int sequenceLength;
    private SecondaryStructure secondaryStructure;
    private boolean isFolded = false;

    /**
     * Costruisce un solver che utilizza l'algoritmo di Nussinov.
     *
     * @param primarySequence
     *                            la sequenza di nucleotidi di cui fare il
     *                            folding
     *
     * @throws IllegalArgumentException
     *                                      se la primarySequence contiene dei
     *                                      codici di nucleotidi sconosciuti
     * @throws NullPointerException
     *                                      se la sequenza di nucleotidi è nulla
     */
    public NussinovFolder(String primarySequence) {
        if (primarySequence == null)
            throw new NullPointerException(
                    "Tentativo di costruire un solutore Nussinov a partire da una sequenza nulla");
        String seq = primarySequence.toUpperCase().trim();
        // check bases in the primary structure - IUPAC nucleotide codes
        for (int i = 0; i < seq.length(); i++)
            switch (seq.charAt(i)) {
                case 'A':
                case 'U':
                case 'C':
                case 'G':
                    break;
                default:
                    throw new IllegalArgumentException(
                            "INPUT ERROR: primary structure contains an unkwnown nucleotide code at position "
                                    + (i + 1));
            }
        this.primarySequence = seq;
        this.sequenceLength = seq.length();
        this.secondaryStructure = new SecondaryStructure(seq);
    }

    public String getName() {
        return "NussinovFolder";
    }

    @Override
    public String getSequence() {
        return this.primarySequence;
    }

    @Override
    public SecondaryStructure getOneOptimalStructure() {
        return secondaryStructure;
    }

    private void tracebackRecursive(NussinovMatrix matrix, int row, int col) {
        if (col <= row) {
            return;
        } else if (matrix.getCell(row, col) == matrix.getCell(row, col - 1)) {
            tracebackRecursive(matrix, row, col - 1);
            return;
        } else {
            for (int k = row; k < col; k++) {
                String currentPair = String.format("%c%c", this.primarySequence.charAt(k), this.primarySequence.charAt(col));
                if (ValidCoupling.isValidCoupling(currentPair)) {
                    if (matrix.getCell(row, col) == matrix.getCell(row, k - 1) + matrix.getCell(k + 1, col - 1) + 1) {
                        this.secondaryStructure.addBond(new WeakBond(k + 1, col + 1));
                        tracebackRecursive(matrix, row, k - 1);
                        tracebackRecursive(matrix, k + 1, col - 1);
                        return;
                    }
                }
            }
        }
    }

    private void traceback(NussinovMatrix matrix) {

        Stack<IntPair> cellsStack = new Stack<>();
        IntPair firstIntPair = new IntPair(0, this.sequenceLength - 1);
        cellsStack.push(firstIntPair);
        while (!cellsStack.isEmpty()) {
            IntPair temp = cellsStack.pop();
            int row = temp.getLeft();
            int col = temp.getRight();
            if (col <= row) {
                continue;
            } else if (matrix.getCell(row, col) == matrix.getCell(row, col - 1)) {
                cellsStack.push(new IntPair(row, col - 1));
            } else {
                for (int k = row; k < col; k++) {
                    String currentPair = String.format("%c%c", this.primarySequence.charAt(k), this.primarySequence.charAt(col));
                    if (ValidCoupling.isValidCoupling(currentPair)) {
                        if (matrix.getCell(row, col) == matrix.getCell(row, k - 1) + matrix.getCell(k + 1, col - 1) + 1) {
                            this.secondaryStructure.addBond(new WeakBond(k + 1, col + 1));
                            cellsStack.push(new IntPair(row, k - 1));
                            cellsStack.push(new IntPair(k + 1, col - 1));
                            break;
                        }
                    }
                }
            }
        }
        this.isFolded = true;
    }

    @Override
    public void fold() {
        NussinovMatrix matrix = new NussinovMatrix(this.sequenceLength);
        /* La diagonale principale e zero, perche rappresenta l'accoppiamento tra lo stesso nucleotide
         * che non e' valido. Quindi iniziamo dalla diagonale superiore alla principale */
        IntStream.range(1, this.sequenceLength).forEach(diagonal -> {
            IntStream.range(0, this.sequenceLength - diagonal).forEach(row -> {
                final int col = row + diagonal;
                int tempMax = matrix.getCell(row, col - 1);
                for (int k = row; k < col; k++) {
                    String currentPair = String.format("%c%c", this.primarySequence.charAt(k), this.primarySequence.charAt(col));
                    if (ValidCoupling.isValidCoupling(currentPair)) {
                        int secondRecurrenceCase = matrix.getCell(row, k - 1) + matrix.getCell(k + 1, col - 1) + 1;
                        tempMax = Math.max(tempMax, secondRecurrenceCase);
                    }
                }
                matrix.setCell(row, col, tempMax);
            });
        });
        traceback(matrix);
    }

    @Override
    public boolean isFolded() {
        return secondaryStructure.getBonds().size() > 0;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        NussinovFolder nf = new NussinovFolder("GCACGACG");
        nf.fold();
        List<WeakBond> bonds = nf.secondaryStructure.getBonds().stream().sorted(Comparator.comparing(WeakBond::getI)).collect(Collectors.toList());
        System.out.println(nf.secondaryStructure.toString());
        bonds.forEach(bond -> System.out.println(bond.toString()));
        System.out.println(nf.secondaryStructure.getDotBracketNotation());
        System.out.println((System.currentTimeMillis() - start) + " ms");

    }
}