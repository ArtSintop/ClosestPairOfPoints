import java.util.Comparator;

 /**
 * Created by Art on 8/15/2016.
 */
public class ComparePointsByY implements Comparator<Point> {

    public int compare(Point P1, Point P2) {
        if (P1.getY() < P2.getY()) {
            return -1;
        }
        else if (P1.getY() > P2.getY()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}