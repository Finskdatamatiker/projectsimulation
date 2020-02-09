import java.util.Comparator;

public class SortByState implements Comparator<Unit> {
    /*
     Denne klasse sorterer units efter stateNr.
     */
        public int compare(Unit a, Unit b)
        {
            return a.getStateNr() - b.getStateNr();
        }
    }

