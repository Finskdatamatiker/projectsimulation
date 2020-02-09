import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Unit {
    /**
     * Klassen repræsenterer de forskellige workUnits i hver iteration
     */

    /**
     * value = bestemt mængde arbejde
     * round = hver state har et bestemt antal iterationer, så round fortæller, om unit
     * skal forblive i den samme state eller gå videre
     * stateNr = state, som unit tilhører i denne iteration
     */
    private double value;
    private int round;
    private int stateNr;

    public Unit(){}

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public int getStateNr() {
        return stateNr;
    }
    public void setStateNr(int stateNr) {
        this.stateNr = stateNr;
    }

    /**
     * Jeg vil have doubles til at blive vist med komma
     * og et bestemt antal decimaler, så jeg bruger DecimalFormat
     * og DecimalFormatSymbols til det.
     * Jeg henter navnene på state fra Enum.
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.0000000000");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        String stateName ="";
        switch (stateNr){
            case 0: stateName = State.STAR.toString();
            break;
            case 1: stateName = State.COMM.toString();
            break;
            case 2: stateName = State.PLAN.toString();
            break;
            case 3: stateName = State.MODE.toString();
            break;
            case 4: stateName = State.CONS.toString();
            break;
            case 5: stateName = State.DEPL.toString();
            break;
        }
        return stateName +
                " - value: " + df.format(value) +
                ", round: " + round;
    }
}
