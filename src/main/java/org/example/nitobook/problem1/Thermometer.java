package org.example.nitobook.problem1;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Thermometer  extends TimerTask {
    private static final Random random = new Random();
    private int value = random.nextInt(10)+15; // between 15 & 24 degrees  //  private TimerTask task = new Tasky();

    @Override
    public void run() {
        value += random.nextInt(15)-5; //-5 & 10
    }
    public Thermometer() {
        Timer timer = new Timer();
        timer.schedule(this, 1000,2000);
    }

    public Thermometer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        /*Timer timer = new Timer();
        Thermometer task = new Thermometer();
        timer.schedule(task, 1000,1000);
        while (true){
            if(task.getValue()>25){
                System.out.println(" !!!!! Alarm !!!!! ");
                System.out.println(" !!! Temperature: "+task.getValue());
            }
        }*/
        Thermometer thermometer = new Thermometer();
        while (true){
            if(thermometer.getValue()>30){
                System.out.println(" !!!!! Alarm !!!!! ");
                System.out.println(" !!! Temperature: "+thermometer.getValue());
            }

        }
    }
}
