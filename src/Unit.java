import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Unit {
    /**
     * Klassen repræsenterer de forskellige workUnits i hver iteration.
     */

    /**
     * value = bestemt mængde arbejde. dvs. procenter af det samlede projekt, fx. 0.0004 af det samlede projekt
     * round = hver state har et bestemt antal iterationer, så round fortæller, om unit
     * skal forblive i den samme state eller gå videre
     */
    private double value;
    private int round;
    private State state;

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
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
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

        return state +
                " - value: " + df.format(value) +
                ", round: " + round;
    }
}
