import java.util.ArrayList;

public class Handler {

    /**
     * Klassen har ansvar for at lave iterationerne.
     * Den har en arrayliste med iterationerne som sit felt.
     */
    private ArrayList<Iteration> allIterations = new ArrayList<>();

    public Handler() {
    }

    public ArrayList<Iteration> getAllIterations() { return allIterations; }
    public void setAllIterations(ArrayList<Iteration> allIterations) { this.allIterations = allIterations; }

    /**
     * Metoden laver den første iteration. Iteration indeholder en metode
     * med startværdier til den første unit, så man har et udgangspunkt.
     */
    public void makeFirstIteration() {
        Iteration iteration = new Iteration(0);
        iteration.addFirstUnit();
        allIterations.add(iteration);
    }

    /**
     * Metoden bliver brugt i metoden makeIteration() til at lave en ny unit
     * til iterationen med bestemte værdier.
     * @param value bestemt mængde arbejde i unit
     * @param round omgang
     * @param stateNr
     * @param iterationsId iteration, unit tilhører
     */
    public void addUnit(double value, int round, int stateNr, int iterationsId) {
        Unit unit = new Unit();
        unit.setValue(value);
        unit.setRound(round);
        unit.setStateNr(stateNr);
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
                if (unit.getStateNr() == State.DEPL.getStateNr()) {
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
        Iteration nyIteration = new Iteration(allIterations.size());
        allIterations.add(nyIteration);
        int index = nyIteration.getIterationId();
        ArrayList<Unit> previousUnits = copyPreviousUnits(index);
        double mp8 = 0.8;
        double mp2 = 0.2;

        for (int i = 0; i < previousUnits.size(); i++) {

            Unit unit = previousUnits.get(i);
            int stateNr = unit.getStateNr();
            int round = unit.getRound();
            double value = unit.getValue();

            switch (stateNr) {
                case 0: case 1:
                    addUnit(value, round, (stateNr + 1), index);
                    break;
                case 2:
                    if (round == 1) {
                        addUnit(value, round + 1, stateNr, index);
                    } else if (round == 2) {
                        double værdi = value * mp8;
                        addUnit(værdi, round - 1, stateNr + 1, index);
                        double værdi2 = value * mp2;
                        addUnit(værdi2, round - 1, stateNr - 1, index);
                    }
                    break;
                case 3:
                    if (round == 1) {
                        addUnit(value, round + 1, stateNr, index);
                    } else if (round == 2) {
                        double værdi = value * mp8;
                        double værdi2 = value * mp2;
                        addUnit(værdi, round - 1, stateNr + 1, index);
                        addUnit(værdi2, round - 1, stateNr, index);
                    }
                    break;
                case 4:
                    if (round < 4) {
                        addUnit(value, round + 1, stateNr, index);
                    } else if (round == 4) {
                        double værdi = value * mp8;
                        double værdi2 = value * mp2;
                        addUnit(værdi, round - 3, stateNr + 1, index);
                        addUnit(værdi2, round - 3, stateNr - 3, index);
                    }
                    break;
            }
        }
    }

    /**
     * metoden looper iterationerne, som henter
     * toString fra iteration, som henter toString fra unit.
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
