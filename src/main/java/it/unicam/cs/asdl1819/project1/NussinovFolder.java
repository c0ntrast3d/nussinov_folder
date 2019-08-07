/**
 *
 */
package it.unicam.cs.asdl1819.project1;

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
    private final SecondaryStructure secondaryStructure = null;
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

    @Override
    public void fold() {
        NussinovMatrix nm = new NussinovMatrix(this.sequenceLength);

/*        for (int j = 1; j < n; j++)
            for (int i = j; i >= 0; i--) {
                for (int t = j; t >= i; t--)
                    if (S.charAt(j) + S.charAt(t) == 138 || S.charAt(j) + S.charAt(t) == 150)
                        if (i == t) //if j pairs with i
                            temp = Math.max(temp, 1 + OPT[t + 1][j - 1]);
                        else
                            temp = Math.max(temp, OPT[i][t - 1] + 1 + OPT[t + 1][j - 1]);
                OPT[i][j] = Math.max(OPT[i][j - 1], temp);
                temp = 0;
            }*/
        final int[] counter = {1};

/*        IntStream.range(0, this.sequenceLength).forEach(col -> {
            int loopCount = col;
            System.out.println("COL :: " + col);
            IntStream.range(0, this.sequenceLength - col).forEach(row -> {
                System.out.println("ROW :: " + row);
                nm.setCell(loopCount, col, counter[0]);
                counter[0]++;
                if (ValidCoupling.isValidCoupling(
                        String.format("%c%c", this.primarySequence.charAt(row), this.primarySequence.charAt(col)))) {
                    System.out.println("VALID : Row :: " + row + " Col :: " + col);
                    //   nm.setCell(row, col, 1);
                }
            });
        });*/
        int temp = 0;
        for (int j = 1; j < sequenceLength; j++)
            for (int i = j; i >= 0; i--) {
                for (int t = j; t >= i; t--)
                    if (ValidCoupling.isValidCoupling(String.format("%c%c", this.primarySequence.charAt(j), this.primarySequence.charAt(t))))
                        if (i == t) //if j pairs with i
                            temp = Math.max(temp, 1 + nm.getCell(t + 1, j - 1));
                        else
                            temp = Math.max(temp, nm.getCell(i, t - 1) + 1 + nm.getCell(t + 1, j - 1));
                nm.setCell(i, j, Math.max(nm.getCell(i, j - 1), temp));
                //nm.setCell(i, j, counter[0]);
                counter[0]++;
                temp = 0;
            }
        nm.printMatrixWithSequence(this.primarySequence);
    }

    @Override
    public boolean isFolded() {
        // TODO implementare
        return false;
    }

    public static void main(String[] args) {
        NussinovFolder nf = new NussinovFolder("GCACGACG");
        nf.fold();
    }
}