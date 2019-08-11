package it.unicam.cs.asdl1819.project1;

public class IntPair {

    private final int left;
    private final int right;

    public IntPair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public String toString() {
        return String.format("Left : %d | Right : %d", this.left, this.right);
    }
}