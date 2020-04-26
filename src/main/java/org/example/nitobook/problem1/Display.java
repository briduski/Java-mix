package org.example.nitobook.problem1;

public class Display extends Thread implements IDisplay {
    private Thermometer therm;
    private String name;
    private boolean fatalError=false;

    public Display(String name, Thermometer thermometer){
        therm = thermometer;
        this.name = name;
      //  this.start();
    }
    @Override
    public void run() {
        System.out.println(" >>>> Display run, name:  "+ name);
        while(!fatalError) {
            delay(100);
        }
        System.out.println(">> Fatal Error happened !! in thermometer: "+name);
    }

    @Override
    public Thermometer getThermometer() {
        return therm;
    }

    @Override
    public void show(int temp) {
        //System.out.println(String.format("%d degrees in %s", temp, name));
    }

    @Override
    public void warning(int temp) {
        //System.out.println(String.format("Warning: %d degrees in %s", temp, name));
    }

    @Override
    public void error() {
        //System.out.println("Disaster in " + name + " .........................");
        fatalError = true;
    }
    public boolean isFatalError() {
        return fatalError;
    }
    private static void delay(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
