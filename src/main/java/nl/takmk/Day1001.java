package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Day1001 {

    /**
     * @See https://adventofcode.com/2018/day/10
     * <p>
     * Solves both part 1 and 2
     */
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> input = readFile("input-day10.txt");
        ArrayList<Point> points = new ArrayList<>();

        // read input and convert to format that is usable

        for (int i = 0; i < input.size(); i++) {
            Point point = new Point(toIntArray(input.get(i)));
            points.add(point);
        }

        // loop through 50000 seconds and calculate the smallest size,
        // assuming this is the time that the message is readable

        Long lowestsize = 99999999999999L;
        int timeLowestsize = 0;

        for (int t = 0; t < 50000; t++) {

            Long result = calcSizeForT(points, t);

            if (result < lowestsize) {
                timeLowestsize = t;
                lowestsize = result;
            }
        }

        log(MessageFormat.format("{0} at t={1}", lowestsize, timeLowestsize));

        // create matrix that is needed to display state
        // assume 200 , can be calculated - enough for now
        int dim = 200;
        String[][] matrix = new String[dim][dim];

        // fill the background with dots (blank it)
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                matrix[x][y] = ".";
            }
        }

        // put the points in the matrix
        for (Point point : matrixAtT(points, timeLowestsize)) {
            matrix[point.posX][point.posY] = "#";
        }

        // 'draw' the matrix
        for (int y = 0; y < dim; y++) {
            for (int x = 0; x < dim; x++) {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }
    }


    private static Long calcSizeForT(ArrayList<Point> points, int t) {

        int minX = 99999;
        int minY = 9999;
        int maxX = 0;
        int maxY = 0;

        for (Point point : points) {

            int difX = t * point.vX;
            int difY = t * point.vY;
            int curX = point.posX + difX;
            int curY = point.posY + difY;

            if (curX < minX) {
                minX = curX;
            } else if (curX > maxX) {
                maxX = curX;
            }

            if (curY < minY) {
                minY = curY;
            } else if (curY > maxY) {
                maxY = curY;
            }

        }

        long xdif;
        long ydif;

        xdif = maxX - minX;
        ydif = maxY - minY;

        if (xdif > ydif) {
            return xdif;
        } else {
            return ydif;
        }

    }


    private static ArrayList<Point> matrixAtT(ArrayList<Point> points, int t) {

        ArrayList<Point> newPoints = new ArrayList<Point>();

        for (Point point : points) {
            int curX = point.posX + (t * point.vX);
            int curY = point.posY + (t * point.vY);

            int[] p = new int[4];
            p[0] = curX;
            p[1] = curY;
            p[2] = point.vX;
            p[3] = point.vY;

            newPoints.add(new Point(p));

//            log(p[0] + "," + p[1]);
        }

        return newPoints;
    }


    //-----


    public static int[] toIntArray(String input) {
        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void log(String logLine) {
        System.out.println(logLine);
    }


    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day1001.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }

}

