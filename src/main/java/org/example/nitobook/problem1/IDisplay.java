package org.example.nitobook.problem1;

public interface IDisplay {
    public Thermometer getThermometer();
    public void show(int temp);
    public void warning(int temp);
    public void error();
    public boolean isFatalError();
}
