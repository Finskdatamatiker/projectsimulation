import java.util.Comparator;

public class SortByState implements Comparator<Unit> {
    /*
     Denne klasse sorterer units efter stateNr.
     */
        public int compare(Unit a, Unit b)
        {
            int stateNra = a.getState().getStateNr();
            int stateNrb = b.getState().getStateNr();
            return stateNra - stateNrb;
            //return a.getStateNr() - b.getStateNr();
        }
    }

