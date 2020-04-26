package org.example.nitobook;

public class Exercise3 {
    private static Buffer<Integer> buffer = new Buffer<>(2);
    public static void main(String[] args) {
    Thread t1 = new Thread(
            new Runnable() {
                @Override
                public void run() {
                    for (int n = 1; ;){
                        if (!buffer.full())
                            try {
                            buffer.insert(n++);
                           // Thread.sleep(1000);
                          } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                    }
                }
            }
    );

     Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                        if (!buffer.empty())
                            try {
                                System.out.println(buffer.remove());
                            //    Thread.sleep(1000);
                            } catch (Exception ex) {}
                        }
                    }
                }
        );
        t2.start();
        t1.start();

    }
}
