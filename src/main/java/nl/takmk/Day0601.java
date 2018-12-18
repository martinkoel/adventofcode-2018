package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day0601 {

    public static final int EQUALLY_FAR = 999999999;
    public static final String INFINITE = "inf";

    public static void main(String[] args) throws FileNotFoundException {

        final ArrayList<String> input = readFile("input-day06.txt");

        int gridDimension = calculateGridDimension(input);
        int grid[][] = new int[gridDimension][gridDimension];

        for (int x = 0; x < gridDimension; x++) {
            for (int y = 0; y < gridDimension; y++) {
                grid[x][y] = calcClosestCoord(input, x, y);
            }
        }

        //mark infinites
        for (int x = 0; x < gridDimension; x++) {
            for (int y = 0; y < gridDimension; y++) {
                if (x == 0 || y == 0) {
                    if (grid[x][y] != EQUALLY_FAR) {
                        log("removing "+input.get(grid[x][y]));
                        input.set(grid[x][y], INFINITE);
                    }
                }
            }
        }

        // count hits on closest coordinates per coordinate

        int counter[] = new int[input.size()];

        for (int x = 0; x < gridDimension; x++) {
            for (int y = 0; y < gridDimension; y++) {
                if (grid[x][y] != EQUALLY_FAR && !input.get(grid[x][y]).equals(INFINITE)) {
                    counter[grid[x][y]]++;
                }
            }
        }

        // figure out the highest counter value

        int max = 0;
        for (int i = 0; i < counter.length; i++) {

            log(counter[i] + "");

            if (counter[i] > max) {
                max = counter[i];
            }
        }

        log("max count is " + max);

    }

    private static int calcClosestCoord(ArrayList<String> input, int x, int y) {

        int i = 0;
        int minDistance = 999999999;
        int coordId = 0;

        while (i < input.size()) {

            int[] array = toIntArray(input.get(i));

            int dist = manhattanDistance(array[0], array[1], x, y);

            if (dist == minDistance) {
                coordId = EQUALLY_FAR;
            } else if (dist < minDistance) {
                minDistance = dist;
                coordId = i;
            }

            i = i + 1;
        }

        log("For coordinate  " + " x = " + x + " y = " + y + " closest coord from input is " + (coordId != EQUALLY_FAR ? input.get(coordId) : "."));

        return coordId;
    }

    private static int calculateGridDimension(ArrayList<String> input) {

        int i = 0;
        int max = 0;

        while (i < input.size()) {

            int[] array = toIntArray(input.get(i++));

            if (array[0] > max) {
                max = array[0];
            }

            if (array[1] > max) {
                max = array[1];
            }

        }

        log("Grid edge:" + max);

        return max;

    }

    /**
     * For example, in the plane, the taxicab distance between
     * {\displaystyle (p_{1},p_{2})} (p_{1},p_{2}) and
     * {\displaystyle (q_{1},q_{2})} (q_{1},q_{2}) is
     * {\displaystyle |p_{1}-q_{1}|+|p_{2}-q_{2}|.}
     * |p_{1}-q_{1}|+|p_{2}-q_{2}|.
     *
     * @return
     */
    public static int manhattanDistance(int p1, int p2, int q1, int q2) {

        return Math.abs(p1 - q1) + Math.abs(p2 - q2);
    }


    public static int[] toIntArray(String input) {

        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    //-----

    private static void log(String logLine) {
        System.out.println(logLine);
    }

    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0601.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
