public enum State {
    /**
     * De forskellige states, som Enum.
     * Jeg har lavet min egen konstruktør for at give
     * dem stateNr.
     */

    STAR(0),
    COMM(1),
    PLAN(2),
    MODE(3),
    CONS(4),
    DEPL(5);

    private final int stateNr;
    private State(int stateNr) {
        this.stateNr = stateNr;
    }

    public int getStateNr(){
        return stateNr;}

}
