package nl.takmk;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day0901 {

    final static int players = 9;
    final static int marbles = 25;
    static int[] playerScore = new int[players + 1];

    static int posCurrentMarble = 0;
    static int currentPlayer = 1;

    static ArrayList<Integer> marblesCircle = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        marblesCircle.add(0);

        for (int marble = 1; marble <= marbles; marble++) {

            if (marble % 23 != 0) {
                addMarble(marble);
            } else {
                playerScore[currentPlayer] = playerScore[currentPlayer] + marble;
                removeMarble();
                log("marble " + marble);
            }

            log(currentPlayer + "-" + posCurrentMarble + "- " + marblesCircle.toString());

            if (currentPlayer < players) {
                currentPlayer++;
            } else {
                currentPlayer = 1;
            }
        }


        for(int i = 0 ; i <= players ; i++) {
            log(i + "-" + playerScore[i]);
        }

    }

    private static void removeMarble() {

        playerScore[currentPlayer] = playerScore[currentPlayer] + marblesCircle.get(posCurrentMarble - 7);

        log("removed marble w v" + marblesCircle.get(posCurrentMarble - 7));
        posCurrentMarble = posCurrentMarble - 7;
        marblesCircle.remove(posCurrentMarble);

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
