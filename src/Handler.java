import java.util.ArrayList;

public class Handler {

    /**
     * Klassen har ansvar for at lave iterationerne.
     * Den har en arrayliste med iterationerne som felt.
     */
    private Iteration iteration = new Iteration(0);
    private ArrayList<Iteration> allIterations = new ArrayList<>();

    public Handler() {}

    public ArrayList<Iteration> getAllIterations() { return allIterations; }
    public void setAllIterations(ArrayList<Iteration> allIterations) { this.allIterations = allIterations; }
    public Iteration getIteration() { return iteration; }
    public void setIteration(Iteration iteration) { this.iteration = iteration; }

    /**
     * Metoden laver den første iteration. Iteration indeholder en metode
     * med startværdier til den første unit, så man har et udgangspunkt.
     */
    public void makeFirstIteration() {
        iteration.addFirstUnit();
        allIterations.add(iteration);
    }

    /**
     * Metoden bliver brugt i metoden makeIteration() til at lave en ny unit
     * til iterationen med bestemte værdier.
     * @param value bestemt mængde arbejde i unit
     * @param round omgang
     * @param state
     * @param iterationsId iteration, unit tilhører
     */
    int iterNr  = 0;
    public void addUnit(double value, int round, State state, int iterationsId) {
        Unit unit = new Unit();
        unit.setValue(value);
        unit.setRound(round);
        unit.setState(state);
        allIterations.get(iterationsId).getUnits().add(unit);
    }

    /**
     * Metoden laver en kopi af den sidste iteration. Den
     * bruger jeg i metoden makeIteration() for at hente units,
     * som danner udgansgpunkt for den nye iteration.
     * @param iterationsID
     * @return
     */
    public ArrayList<Unit> copyPreviousUnits(int iterationsID) {
        ArrayList<Unit> previousUnits = allIterations.get(iterationsID - 1).getUnits();
        return previousUnits;
    }

    /**
     * Denne metode henter summen af værdierne i alle units DEPL.
     * Jeg skal bare sammenligner statenr i unit med DEPL's statenummer, som
     * jeg henter med en getter fra Enum.
     * Nested for-loop, hvor jeg først henter iterationer og looper med i
     * og så henter jeg units i disse iterationer med j.
     * @return
     */
    public double getWorkDone() {
        double workDone = 0.0;
        for (int i = 0; i < allIterations.size(); i++) {
            Iteration iteration = allIterations.get(i);

            for (int j = 0; j < iteration.getUnits().size(); j++) {
                Unit unit = iteration.getUnits().get(j);
                if(unit.getState().toString().equals("DEPL")){
                    workDone += unit.getValue();
                }
            }
        }
        return workDone;
    }

    /**
     * Metoden laver en iteration med units.
     * Jeg henter den forrige iteration og looper dens units. Så kan jeg se,
     * hvad der bliver sendt til denne nye iteration. Ud fra units værdi, statenr og
     * round (omgansgsnummer) ved jeg, hvordan de skal sendes videre til denne nye iteration.
     * Ved slutningen af state Planning, Modelling og Construction skal unit fra den forrige
     * iteration splittes i to, sådan at den nye iteration får to units (den ene har 0.2 og den
     * anden har 0.8 af værdien). På den måde ændrer sig antallet af units i hver iteration.
     */
    public void makeIteration() {
        iteration = new Iteration(allIterations.size());
        allIterations.add(iteration);
        int index = iteration.getIterationId();
        ArrayList<Unit> previousUnits = copyPreviousUnits(index);
        double mp8 = 0.8;
        double mp2 = 0.2;

        for (int i = 0; i < previousUnits.size(); i++) {

            Unit unit = previousUnits.get(i);
            State state = unit.getState();
            int round = unit.getRound();
            double value = unit.getValue();
            State nyState;

            switch (state) {
                case STAR: case COMM:
                    nyState = State.givNyState(state,1);
                    addUnit(value, round, nyState, index);
                    break;
                case PLAN:
                    if (round == 1) {
                        addUnit(value, round + 1, state, index);
                    } else if (round == 2) {
                        double vaerdi = value * mp8;
                        nyState = State.givNyState(state, 1);
                        addUnit(vaerdi, round - 1, nyState, index);
                        double vaerdi2 = value * mp2;
                        nyState = State.givNyState(state,-1);
                        addUnit(vaerdi2, round - 1, nyState, index);
                    }
                    break;
                case MODE:
                    if (round == 1) {
                        addUnit(value, round + 1, state, index);
                    } else if (round == 2) {
                        double vaerdi = value * mp8;
                        double vaerdi2 = value * mp2;
                        nyState = State.givNyState(state,1);
                        addUnit(vaerdi, round - 1, nyState, index);
                        addUnit(vaerdi2, round - 1, state, index);
                    }
                    break;
                case CONS:
                    if (round < 4) {
                        addUnit(value, round + 1, state, index);
                    } else if (round == 4) {
                        double vaerdi = value * mp8;
                        double vaerdi2 = value * mp2;
                        nyState = State.givNyState(state,1);
                        State nyNyState = null;
                        nyNyState = State.givNyState(state,-3);
                        addUnit(vaerdi, round - 3, nyState, index);
                        addUnit(vaerdi2, round - 3, nyNyState, index);
                    }
                    break;
            }
        }
    }


    /**
     * metoden looper iterationerne, som henter
     * toString fra iteration, som igen henter toString fra unit.
     * @return
     */

    @Override
    public String toString() {
        String tekst = "";
        for(int i = 0; i < allIterations.size();i++){
            tekst = tekst + allIterations.get(i) + "\n";
        }
        return tekst;
    }

}
