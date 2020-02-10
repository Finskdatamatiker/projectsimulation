import java.util.ArrayList;

public class SimulationMain {
    public static void main(String[] args) {

        Handler handler = new Handler();
        int antalletAfIterationer = 30;

        handler.makeFirstIteration();

        //ret det her til ikke at have recursive kode
        for(int i = 0; i < antalletAfIterationer; i++) {
            handler.makeIteration();
        }
        System.out.println(handler.toString());
        System.out.println(handler.getWorkDone());
    }
}
