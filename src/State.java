public class State {

    private String name;
    private int numberIterations;
    private double workReceived;
    private double workSent;
    private Handler handler;

    public State(){}

    public State(String name){
        this.name = name;
    }

    /**
     *
     * @param procent
     */
    public void receiveWork(double procent){
        workReceived = procent * handler.getWorkLeft();
    }

     public void sendWorkForward(double procent){
        workSent = procent * handler.getWorkLeft();
    }

    public void sendWorkBack(double procent){
        
    }

    public void updateWorkDone(){

    }

    public void updateWorkLeft(){

    }

}
