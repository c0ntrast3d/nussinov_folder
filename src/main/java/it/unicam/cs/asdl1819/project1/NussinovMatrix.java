package it.unicam.cs.asdl1819.project1;

public class NussinovMatrix {
    private int[][] matrix;
    private int rows;
    private int cols;

    public NussinovMatrix(int dimension) {
        matrix = new int[dimension][dimension + 1];
        rows = dimension;
        cols = dimension + 1;
    }

    public int getCell(int row, int col) {
        if (row < 0 || row > this.rows)
            throw new ArrayIndexOutOfBoundsException("Tentativo di accesso ad una cella fuori dalla matrice");
        if (col < 0 || col > this.cols)
            throw new ArrayIndexOutOfBoundsException("Tentativo di accesso ad una cella fuori dalla matrice");
        return this.matrix[row][col + 1];
    }

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
}
