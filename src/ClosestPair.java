import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.sql.Types.NULL;

/**
 * Created by Art on 8/15/2016.
 */
public class ClosestPair {

    public static void main(String []args)throws FileNotFoundException{
        System.out.println("Project 2: Closest Pair of Points");
        Scanner input = new Scanner(System.in);
        String yesOrNo = "y";
        while (yesOrNo.equals("y")) {
            ArrayList<Point> sortedByX = new ArrayList<Point>();
            ArrayList<Point> sortedByY = new ArrayList<Point>();
            init(sortedByX, sortedByY);
//            for(Point X : sortedByX) {
//                System.out.print(X.toString() + ",");
//            }
//            System.out.println();
//            for(Point X : sortedByY) {
//                System.out.print(X.toString() + ",");
//            }
            System.out.println("Sqrt(" + findShortest(sortedByX, sortedByY) + ")");
            System.out.println();
            System.out.println();
            System.out.println("Check another file? y or n");
            yesOrNo = input.nextLine();
        }
        System.exit(0);

    }

    private static void init(ArrayList<Point> sortByX, ArrayList<Point> sortByY) throws FileNotFoundException {
        String fileName;
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Please enter input file name");
        fileName = input.nextLine();
        File file = new File(fileName);
        Scanner fileInput = new Scanner(file);
        while (fileInput.hasNext()) {
            sortByX.add(new Point(fileInput.nextInt(), fileInput.nextInt()));
        }

        //Copy constructor to sortbyY
        for(Point X : sortByX) {
            sortByY.add(X);
        }

        //Sort Array by X values
        Collections.sort(sortByX, new ComparePointsByX());
        //Sort Array by Y Values
        Collections.sort(sortByY, new ComparePointsByY());

    }

    private static int findShortest(ArrayList<Point> P1, ArrayList<Point> P2){
        int delta1;
        int delta2;
        int delta3;
        //Used for splits
        ArrayList<Point> grabY = new ArrayList<Point>();
        int smallestDelta = 0;
        int bruteSmallest = Integer.MAX_VALUE;
        int halfWay;
        if(P1.size()<= 3){
            for(int i = 0; i < P1.size(); i++){
                for(int j = i+1; j < P1.size(); j++){
                    if(bruteSmallest > P1.get(i).dist(P1.get(j))){
                        bruteSmallest = P1.get(i).dist(P1.get(j));
                    }
                }
            }
            return bruteSmallest;
        }
        else{
            //Center point for array cutoffs and value for splits
            halfWay = P1.size()/2;
            ArrayList<Point> xHalf1 = new ArrayList<Point>();
            ArrayList<Point> xHalf2 = new ArrayList<Point>();
            ArrayList<Point> yHalf1 = new ArrayList<Point>();
            ArrayList<Point> yHalf2 = new ArrayList<Point>();

            for(int i = 0; i < P1.size(); i++){
                if(i < halfWay){
                    xHalf1.add(P1.get(i));
                }
                else{
                    xHalf2.add(P1.get(i));
                }

                if(P1.get(halfWay).getX() > P2.get(i).getX() ){
                    yHalf1.add(P2.get(i));
                }
                else{
                    yHalf2.add(P2.get(i));
                }

            }

//            System.out.println();
//            for(Point X : yHalf1) {
//                System.out.print(X.toString() + ",");
//            }
//
//            System.exit(0);

            //Recurisve call to find delta1
            delta1 = findShortest(xHalf1, yHalf1);
            //Recursive call to find delta2
            delta2 = findShortest(xHalf2, yHalf2);

            //Find smallest of the 3 deltas
            if(delta1 < delta2){
                smallestDelta = delta1;
            }
            else{
                smallestDelta = delta2;
            }

            //Grab all values smaller than delta where X values are smaller then the middle
            for(int i = 0; i < P1.size(); i++){
                int addMaybe = (int) Math.pow(P2.get(i).getX() - P1.get(halfWay).getX(), 2.0);
                if( addMaybe < smallestDelta){
                    grabY.add(P2.get(i));
                }
            }

            //Check if there exists a smallerDelta
            for(int i =0; i < grabY.size(); i++){
                for(int j = i+1; j < grabY.size(); j++){
                    delta3 = grabY.get(i).dist(grabY.get(j));
                    if(delta3 < smallestDelta){
                        smallestDelta = delta3;
                    }
                }
            }

        }
        return smallestDelta;
    }

}
