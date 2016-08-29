import java.util.Comparator;

/**
 * Created by Art on 8/15/2016.
 */
public class Point {

    private int x;
    private int y;

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }

    public int dist(Point given){
        return (int) (Math.pow(this.x - given.getX(), 2.0) + Math.pow(this.y - given.getY(), 2.0));
    }

}
