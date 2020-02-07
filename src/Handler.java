import java.util.ArrayList;

public class Handler {

    //private int sumIterations;
    private double workDone;
    private double workLeft;
    private ArrayList<Iteration> allIterations;

    public Handler(){}

    public double getWorkDone() { return workDone; }
    public void setWorkDone(double workDone) { this.workDone = workDone; }
    public double getWorkLeft() { return workLeft; }
    public void setWorkLeft(double workLeft) { this.workLeft = workLeft; }
    public ArrayList<Iteration> getAllIterations() { return allIterations; }
    public void setAllIterations(ArrayList<Iteration> allIterations) { this.allIterations = allIterations; }


}
