package nl.takmk;

import org.magicwerk.brownies.collections.primitive.IntGapList;

import java.io.FileNotFoundException;

public class Day0901 {

    final static int players = 424;
    final static int marbles = 7148200;
    static long[] playerScore = new long[players + 1];

    static int posCurrentMarble = 0;
    static int currentPlayer = 1;

    static IntGapList marblesCircle = new IntGapList();

    public static void main(String[] args) throws FileNotFoundException {

        marblesCircle.add(0);

        long startt = System.currentTimeMillis();

        for (int marble = 1; marble <= marbles; marble++) {

            if(marble%10000==0) {
                log("Marble " + marble + " - " + ((System.currentTimeMillis() - startt)/1000)+"s");
            }

            if (marble % 23 != 0) {
                addMarble(marble);
            } else {
                removeMarble(marble);
            }

            if (currentPlayer < players) {
                currentPlayer++;
            } else {
                currentPlayer = 1;
            }
        }

        long highestScore=0;
        for (int i = 0; i <= players; i++) {
           if (highestScore < playerScore[i]) {
               highestScore=playerScore[i];
           }
        }

        log("Highest " + highestScore);

    }

    private static void removeMarble(int marble) {
        playerScore[currentPlayer] = playerScore[currentPlayer] + marble;

        if (posCurrentMarble > 7) {
            posCurrentMarble = posCurrentMarble - 7;
        } else {
            posCurrentMarble = marblesCircle.size() - (7 - posCurrentMarble);
        }

        playerScore[currentPlayer] = playerScore[currentPlayer] + marblesCircle.get(posCurrentMarble);

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
