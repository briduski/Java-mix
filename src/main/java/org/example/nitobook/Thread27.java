package org.example.nitobook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Thread27 extends RecursiveAction {
    private static int [][] A1 = { {1,2,3},{4,5,6} };
    private static int [][] A2 = { {1,2,3},{4,5,6} , {7,8,9}};
    private final Matrix A,B,C;
    private final int row;

    public Thread27(Matrix a, Matrix b, Matrix c) {
       this(a,b,c, -1);
    }

    public Thread27(Matrix a, Matrix b, Matrix c, int row) {
        if (a.getCols() != b.getRows())
            throw  new IllegalArgumentException("Invalid matrix mult.");
        A = a;
        B = b;
        C = c;
        this.row = row;
    }

    public static void main(String[] args) {
        Matrix A = new Matrix(A1);
        Matrix B = new Matrix(A2);
        print(A);
        print(B);
        Matrix C = new Matrix(A.getRows(), B.getCols());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new Thread27(A,B,C));
        print(C);
    }

    @Override
    protected void compute() {
        if (row == -1){
            List<Thread27> tasks = new ArrayList();
            for (int r = 0; r<A.getRows();++r){
                tasks.add(new Thread27(A,B,C,r));
            }
            //System.out.println("Number of tasks: "+A.getRows());
            invokeAll(tasks);
            //System.out.println("after invokeAll (all tasks already started)");
        }else {
           // System.out.println("Calling compute, row "+row);
            multSubtask(A,B,C,row);
        }
    }

    public static Matrix multSubtask(Matrix A, Matrix B){
        if (A.getCols() != B.getRows())
            throw  new IllegalArgumentException("Invalid matrix mult.");

        Matrix C = new Matrix(A.getRows(), B.getCols());
        for(int i=0;i<A.getRows();++i){
            multSubtask(A,B,C, i);
        }
        return C;
    }
    public static void multSubtask(Matrix A, Matrix B, Matrix C, int r){
        for (int j=0; j<B.getCols(); ++j){
            for (int k=0;k<A.getCols();++k){
                C.setValue(r, j, C.getValue(r,j) + A.getValue(r,k)*B.getValue(k, j));
            }
        }

    }



    public  static void print (Matrix M){
        for(int i=0;i<M.getRows();++i){
            for (int j=0; j<M.getCols(); ++j){
                System.out.printf("%5d", M.getValue(i,j));
            }
            System.out.println();
        }
        System.out.println();
    }
}
