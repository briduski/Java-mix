package org.example.nitobook;


public class Thread26 {
// Fork/Join => special Executor service and a thread pool
    //each task is split in smaller pieces - forked to a thread of the pool
    // A task waits until all subtasks are completed
    // and the result and evt. merged (joined) each subtask result
// ForkJoinPool, ForkJoinTask, ForkJoinWorkerThread, RecursiveAction, RecursiveTask, CountedCompleted

    private static int [][] A1 = { {1,2,3},{4,5,6} };
    private static int [][] A2 = { {1,2,3},{4,5,6} , {7,8,9}};
    public static void main(String[] args) {
        Matrix A = new Matrix(A1);
        Matrix B = new Matrix(A2);
        print(A);
        print(B);
        print(mult(A,B));
      //  print(multSubtask(A,B));

    }

    public static Matrix mult(Matrix A, Matrix B){
        if (A.getCols() != B.getRows())
            throw  new IllegalArgumentException("Invalid matrix mult.");

        Matrix C = new Matrix(A.getRows(), B.getCols());

        for(int i=0;i<A.getRows();++i){
            for (int j=0; j<B.getCols(); ++j){
                for (int k=0;k<A.getCols();++k){
                    C.setValue(i, j, C.getValue(i,j) + A.getValue(i,k)*B.getValue(k, j));
                  //  System.out.println("A-row "+i + ", B-colum "+j + ", A-col "+k + ", res: "+C.getValue(i,j));
                }
            }
        }
        return C;
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

