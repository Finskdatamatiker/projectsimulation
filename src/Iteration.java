import java.util.*;

public class Iteration {
    /**
     * Denne klasse repræsenterer en iteration.
     * Den indeholder et nummer og en arrayliste af units,
     * som hver iteration har.
     */

    private int iterationId;
    private ArrayList<Unit> units = new ArrayList<>();

    public Iteration(int iterationId){
        this.iterationId = iterationId;
    }

    public int getIterationId() {
        return iterationId;
    }
    public void setIterationId(int iterationId) {
        this.iterationId = iterationId;
    }
    public ArrayList<Unit> getUnits() {
        return units;
    }
    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    /**
     * Metoden laver den første workUnit som udgangspunkt for systemet.
     * Vi starter i state 0 med værdien 1.0, dvs. hele projektet.
     * Jeg tilføjer denne unit til arraylisten af units.
     */
    public void addFirstUnit(){
        Unit unit = new Unit();
        unit.setValue(1.0);
        unit.setRound(1);
        unit.setStateNr(0);
        units.add(unit);
    }


    /**
     * Her bruger jeg min egen Comparator-klasse for at kunne sammenligne Units
     * og dermed for at kunne sortere dem. Jeg vil have dem printet i units i den
     * rækkefølge, de forekommer i projektet.
     * Jeg looper units en ad gangen til toString.
     * @return
     */
    @Override
    public String toString() {
        String tekst = "";
        Collections.sort(units, new SortByState());

        for(Unit u : units){
           tekst = tekst + u.toString() + "\n";
            }

        return iterationId + "\n" +  tekst;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iteration)) return false;
        Iteration iteration = (Iteration) o;
        return iterationId == iteration.iterationId;
    }

}
