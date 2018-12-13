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

          long part2 =50000000000L;
//        ArrayList<String> input = readFile("input-day12-example.txt");
//        String initialState = ".....#..#.#..##......###...###..............."; //add 5 dots on both sides (as empty pots are on both sides)
        String initialState = ".....#...#####.#..##...##...#.##.#.##.###..##.##.#.#..#...###..####.#.....#..##..#.##......#####..####.............................................................................................................................................................................................................................................................................................................................................................................................................................";

        ArrayList<String> input = readFile("input-day12.txt");

        String state = initialState;
          log("0: " +state);
        for (int t = 1; t < 21; t++) {

            state = process(state, input);

            if (t % 1000 == 1) {
           //     log("t" + t);
            }

                log(t+": "+state);
        }

        int offset = -5;
        int sum = 0;
        for (int i = 0; i < state.length(); i++) {

            if (state.substring(i, i + 1).equals("#")) {
                sum = sum + offset + i;
                //   log("i "+(offset+i));
            }

        }

        log("sum:" + sum);
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

