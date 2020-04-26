package org.example.nitobook;

public class Matrix {
    private final int [][] matrix;

    public Matrix(int rows, int columns ) {
        this.matrix = new int[rows][columns];
    }

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }
    public int getCols(){
        return matrix[0].length;
    }
    public int getRows(){
        return matrix.length;
    }
    public int getValue(int row, int column){
        return matrix[row][column];
    }
    public void setValue(int row, int coumn, int value){
        matrix[row][coumn]=value;
    }
}