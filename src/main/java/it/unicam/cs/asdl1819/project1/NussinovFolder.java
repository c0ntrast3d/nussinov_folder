/**
 *
 */
package it.unicam.cs.asdl1819.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
    private final SecondaryStructure secondaryStructure;
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
        // TODO implementare
        return null;
    }

    private void traceback2(NussinovMatrix matrix, int row, int col) {
        if (col <= row) {
            return;
        } else if (matrix.getCell(row, col) == matrix.getCell(row, col - 1)) {
            traceback2(matrix, row, col - 1);
            return;
        } else {
            for (int k = row; k < col; k++) {
                String currentPair = String.format("%c%c", this.primarySequence.charAt(k), this.primarySequence.charAt(col));
                if (ValidCoupling.isValidCoupling(currentPair)) {
                    if (matrix.getCell(row, col) == matrix.getCell(row, k - 1) + matrix.getCell(k + 1, col - 1) + 1) {
                        System.out.println("PAIR :: " + currentPair + " ROW : " + (k + 1) + " COL : " + (col + 1));
                        traceback2(matrix, row, k - 1);
                        traceback2(matrix, k + 1, col - 1);
                        return;
                    }

                }
            }
        }
    }

    private void traceback(NussinovMatrix matrix) {

        Stack<Cell> storage = new Stack<>();
        Cell firstCell = new Cell(0, this.sequenceLength - 1);
        storage.push(firstCell);
        List<WeakBond> record = new ArrayList<>();
        while (!storage.isEmpty()) {
            Cell temp = storage.pop();
            int row = temp.getLeft();
            int col = temp.getRight();
            if (col <= row) {
                System.out.println("TRACEBACK CONTINUE");
                continue;
            } else if (matrix.getCell(row, col) == matrix.getCell(row, col - 1)) {
                storage.push(new Cell(row, col - 1));
            } else {
                for (int k = row; k < col; k++) {
                    String currentPair = String.format("%c%c", this.primarySequence.charAt(k), this.primarySequence.charAt(col));
                    if (ValidCoupling.isValidCoupling(currentPair)) {
                        if (matrix.getCell(row, col) == matrix.getCell(row, k - 1) + matrix.getCell(k + 1, col - 1) + 1) {
                            System.out.println("PAIR :: " + currentPair + " ROW : " + (k + 1) + " COL : " + (col + 1));
                            record.add(new WeakBond(k + 1, col + 1));
                            storage.push(new Cell(row, k - 1));
                            storage.push(new Cell(k + 1, col - 1));
                            break;
                        }
                    }
                }
            }

        }
        System.out.println("PRINTING BONDS");
        record.forEach(bond -> System.out.println(bond.toString()));
    }

    @Override
    public void fold() {
        NussinovMatrix matrix = new NussinovMatrix(this.sequenceLength);

        IntStream.range(1, this.sequenceLength).forEach(diagonal -> {
            IntStream.range(0, this.sequenceLength - diagonal).forEach(row -> {
                final int col = row + diagonal;
                int tempMax = matrix.getCell(row, col - 1);
                for (int k = row; k < col; k++) {
                    String currentPair = String.format("%c%c", this.primarySequence.charAt(k), this.primarySequence.charAt(col));
                    if (ValidCoupling.isValidCoupling(currentPair)) {
                        int secondReccurrenceCase = matrix.getCell(row, k - 1) + matrix.getCell(k + 1, col - 1) + 1;
                        tempMax = Math.max(tempMax, secondReccurrenceCase);
                    }
                }
                matrix.setCell(row, col, tempMax);
            });
        });

        matrix.printMatrixWithSequence(this.primarySequence);
        //System.out.println(matrix.toString());
        traceback(matrix);
        //traceback2(matrix, 0, this.sequenceLength - 1);
        //System.out.println(this.secondaryStructure.toString());
    }

    @Override
    public boolean isFolded() {
        return this.secondaryStructure.getBonds().size() > 0;
    }

    public static void main(String[] args) {
        NussinovFolder nf = new NussinovFolder("GCACGACG");
        //NussinovFolder nf = new NussinovFolder("GGUCCAC");
        nf.fold();
    }
}