package org.example.nitobook.problem1;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm extends TimerTask {
    private ArrayList<IDisplay> observers = new ArrayList<>();
    private Thermometer[] therms;
    Timer timer;

    @Override
    public boolean cancel() {
    return true;
    }

    @Override
    public void run() {
     //   while (foundValidThermometer()){
            for (IDisplay observer: observers){
                if (observer.getThermometer().getValue() >20){
                    observer.error();
                } else if (observer.getThermometer().getValue() > 18){
                    observer.warning(observer.getThermometer().getValue() );
                } else {
                    observer.show(observer.getThermometer().getValue());
                }
            }
        //}
       //System.out.println("Canceling the Alarm !! ");
       //timer.cancel();
       // this.cancel();
    }
    public Alarm(Thermometer... thermometers) {
        therms = thermometers;
        timer = new Timer();
        timer.schedule(this, 500, 800);

        /*
        The constructor constructor shall initialize the  array and  start a timer that is ticking the first time
        after 500 milliseconds, and otherwise ticks every 800 milliseconds. Each time the timer is ticking,
        it should for each thermometer send messages to the observers
        that concerning the thermometer when to call error(),
        if the temperature is than or equal to 100 greater and call call warning()
        if the  temperature is over 80 degrees and otherwise call show().
*/
    }
    public void addObserver(IDisplay observer){
        observers.add(observer);
    }
    public boolean foundValidThermometer(){
        for (IDisplay observer: observers){
            if (!observer.isFatalError()) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Thermometer[] terms = { new Thermometer(), new Thermometer(), new Thermometer() };
        Alarm alarm = new Alarm(terms);
        alarm.addObserver(new Display("The living room", terms[0]));
        alarm.addObserver(new Display("The office", terms[1]));
        alarm.addObserver(new Display("The winter garden", terms[0]));
        alarm.addObserver(new Display("The bedroom", terms[2]));
    }
}
