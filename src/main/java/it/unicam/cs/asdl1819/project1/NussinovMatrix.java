package it.unicam.cs.asdl1819.project1;

import java.util.stream.IntStream;

public class NussinovMatrix {
    private int[][] matrix;
    private int rows;
    private int cols;

    private void init() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                this.matrix[row][col] = -1;
            }
        }
    }

    /**
     * @throws IllegalArgumentException se si cerca di creare una matrice di dimensione minore di 2
     */

    public NussinovMatrix(int dimension) {
        if (dimension < 2)
            throw new IllegalArgumentException("Impossibile creare una matrice di dimensione minore di 2");
        matrix = new int[dimension][dimension + 1];
        rows = dimension;
        cols = dimension + 1;
        //init();
    }

    /**
     * @return il valore della cella riferenziata
     * @throws ArrayIndexOutOfBoundsException se si cerca di ottenere il valore di una cella che va fuori
     *                                        le dimensioni della matrice
     */
    public int getCell(int row, int col) {
        if (row < 0 || row > this.rows)
            throw new ArrayIndexOutOfBoundsException(String.format("Tentativo di accesso ad una cella fuori dalla matrice : riga %d, colonna %d", row, col));
        if (col < -1 || col > this.cols)
            throw new ArrayIndexOutOfBoundsException(String.format("Tentativo di accesso ad una cella fuori dalla matrice : riga %d, colonna %d", row, col));
        return this.matrix[row][col + 1];
    }

    /**
     * assegna il valore fornito alla cella riferenziata
     *
     * @throws ArrayIndexOutOfBoundsException se si cerca di ottenere il valore di una cella che va fuori
     *                                        le dimensioni della matrice
     */
    public void setCell(int row, int col, int value) {
        if (row < 0 || row > this.rows)
            throw new ArrayIndexOutOfBoundsException("Tentativo di scrittura in una cella fuori dalla matrice");
        if (col < 0 || col > this.cols)
            throw new ArrayIndexOutOfBoundsException("Tentativo di scrittura in una cella fuori dalla matrice");
        if (value < 0) throw new IllegalArgumentException("Il valore di una cella non puo essere negativo");
        this.matrix[row][col + 1] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder stringified = new StringBuilder();
        IntStream.range(0, this.rows).forEach(row -> {
            StringBuilder rowString = new StringBuilder();
            IntStream.range(0, this.cols).forEach(col -> {
                rowString.append(new StringBuilder().append(" ").append(this.matrix[row][col]).append(" |"));
            });
            stringified.append(rowString.toString()).append("\n");

        });
        return stringified.toString();
    }

    /**
     * @throws IllegalArgumentException se la lunghezza della sequenza e diversa dalla dimensione della matrice
     */

    public void printMatrixWithSequence(String primarySequence) {
        if (primarySequence.length() != this.rows)
            throw new IllegalArgumentException("La lunghezza della sequenza primaria deve essere uguale al numero di righe della matrice");
        StringBuilder stringified = new StringBuilder();
        String[] splitted = primarySequence.split("");
        String spacedDelimiter = "   ";
        String spacedPS = String.join(spacedDelimiter, splitted);
        String initialSpace = "        ";
        stringified.append(initialSpace)
                .append(spacedPS)
                .append("\n");

        IntStream.range(0, this.rows).forEach(row -> {
            StringBuilder rowString = new StringBuilder();
            rowString.append(primarySequence.charAt(row)).append(" |");
            IntStream.range(0, this.cols).forEach(col -> {
                rowString.append(" ")
                        .append(this.matrix[row][col])
                        .append(" |");
            });
            stringified.append(rowString.toString())
                    .append("\n");
        });

        System.out.println(stringified.toString());
    }

}
