public class SimulationMain {
    public static void main(String[] args) {

        Handler handler = new Handler();
        int antalletAfIterationer = 27;

        handler.makeFirstIteration();
        for(int i = 0; i < antalletAfIterationer; i++) {
            handler.makeIteration();
        }
        System.out.println(handler.toString());
        System.out.println(handler.getWorkDone());
    }
}
