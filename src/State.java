import java.util.ArrayList;

public enum State {
    /**
     * De forskellige states som Enum.
     * Jeg har lavet min egen konstruktør for at give
     * enums stateNr.
     */

    STAR(0),
    COMM(1),
    PLAN(2),
    MODE(3),
    CONS(4),
    DEPL(5);

    private final int stateNr;
    State(int stateNr) {
        this.stateNr = stateNr;
    }

    public static State givNyState(State state, int forskel) {
        for (State s : values()) {
            if (s.getStateNr() == (state.getStateNr() + forskel)) {
                return s;
            }
        }
        return null;
    }

    public int getStateNr(){
        return stateNr;}

}


