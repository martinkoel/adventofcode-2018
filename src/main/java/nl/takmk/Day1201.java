package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Day1201 {

    /**
     * @See https://adventofcode.com/2018/day/12
     * <p>
     * Solves both part 1 and 2
     */
    public static void main(String[] args) throws FileNotFoundException {

        String initialState = ".....#...#####.#..##...##...#.##.#.##.###..##.##.#.#..#...###..####.#.....#..##..#.##......#####..####.............................................................................................................................................................................................................................................................................................................................................................................................................................";
        String state = initialState;

        ArrayList<String> input = readFile("input-day12.txt");

        log("0: " + state);

        for (int t = 1; t < 21; t++) {
            state = process(state, input);
            log(t + ": " + state);
        }

        int offset = -5;
        int sum = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.substring(i, i + 1).equals("#")) {
                sum = sum + offset + i;
            }
        }

        log("sum:" + sum);

        // part 2
        // by observing the growth pattern, it can be observed that the pattern
        // is predictable after around 140 iterations
        // apply this knowledge to calculate the outcome, as it is impossible
        // to do 50 billion iterations

        // first get an "end state" in which the pots related to each other remain the same,
        // but only their position on the x axis changes every iteration.
        // get the pattern at the 200th iteration
        for (int t = 21; t < 201; t++) {
            state = process(state, input);
        }

        long offsetl = 50000000000L - 5 - 200;
        long suml = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.substring(i, i + 1).equals("#")) {
                suml = suml + offsetl + i;
            }
        }

        log("suml:" + suml);
    }

    private static String process(final String initialState, ArrayList<String> input) {
        String newState = "";
        String newChar = "";

        for (int i = 0; i < initialState.length(); i++) {
            for (int y = 0; y < input.size(); y++) {
                newChar = replace(".." + initialState + "...", i, input.get(y));
                if (!"*".equals(newChar)) {
                    break;
                }
            }
            if ("*".equals(newChar)) {
                newChar = ".";
            }
            newState = newState + newChar;
        }

        return newState;
    }

    private static String replace(String state, int i, String s) {
        String subString = state.substring(i, i + 5);
        String instruction = s.substring(0, 5);
        if (subString.equals(instruction)) {
            return s.substring(6);
        }
        return "*";
    }


    //-----


    public static String[] toArray(String input) {
        return input.split(",");
    }

    private static void log(String logLine) {
        System.out.println(logLine);
    }


    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day1201.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }

}

