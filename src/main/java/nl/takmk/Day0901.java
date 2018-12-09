package nl.takmk;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day0901 {


    static int posCurrentMarble = 0;
    static ArrayList<Integer> marblesCircle = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        int players = 9;
        int marbles = 25;


        marblesCircle.add(0);


        for (int i = 1; i != marbles; i++) {

            addMarble(i);
            log(marblesCircle.toString());
        }


    }

    private static void addMarble(int marbleValue) {

        if (posCurrentMarble + 2 < marblesCircle.size()) {
            posCurrentMarble = posCurrentMarble + 2;
            marblesCircle.add(posCurrentMarble, marbleValue);
        } else if (posCurrentMarble + 2 == marblesCircle.size()) {
            marblesCircle.add(marbleValue);
            posCurrentMarble = marblesCircle.size();
        } else {
            posCurrentMarble = 1;
            marblesCircle.add(posCurrentMarble, marbleValue);
        }
    }


    //-----

    private static void log(String logLine) {
        System.out.println(logLine);
    }


}
