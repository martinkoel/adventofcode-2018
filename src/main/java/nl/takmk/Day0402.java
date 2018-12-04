package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0402 {

    static int data[][] = new int[4000][60];

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> input = readFile("input-day04-sorted.txt");

        int i = 0;

        // read file (which is already sorted) and store results in data structure
        while (i < input.size() - 3) {

            int guardId = getGuardId(input.get(i++));

            while (!nextGuard(input, i)) {

                int fellAsleepAt = getTime(input.get(i++));
                int wokeupAt = getTime(input.get(i++));

                log("Guard " + guardId + " slept from " + fellAsleepAt + " to " + wokeupAt + " - minutes " + (wokeupAt - fellAsleepAt));

                for (int time = 0; time < 60; time++) {
                    data[guardId][time] = (data[guardId][time]) + sleptMinutes(time, fellAsleepAt, wokeupAt);
                }
            }
        }

        int slepttime[] = new int[4000];

        for (int j = 0; j < 4000; j++) {
            for (int t = 0; t < 60; t++) {
                slepttime[j] = slepttime[j] + data[j][t];
            }
        }

        int idLongestSleeper = 0;
        int mostMinutes = 0;
        int minuteMostMinutes = 0;

        for (int j = 0; j < 4000; j++) {
            for (int t = 0; t < 60; t++) {

                if (data[j][t] > mostMinutes) {
                    log("Guard " + j + " at " + t + " slept minutes:" + data[j][t]);
                    mostMinutes = data[j][t];
                    minuteMostMinutes = t;
                    idLongestSleeper=j;
                }
            }
        }

        log("Answer is id x minute = " + idLongestSleeper + " * " + minuteMostMinutes + " = " + idLongestSleeper * minuteMostMinutes);
    }

    private static int sleptMinutes(int time, int fellAsleepAt, int wokeupAt) {
        return isAsleep(time, fellAsleepAt, wokeupAt) ? 1 : 0;
    }

    private static boolean isAsleep(int time, int fellAsleepAt, int wokeupAt) {
        return time >= fellAsleepAt && time < wokeupAt;
    }

    private static int getTime(String s) {
        if (s.contains(":")) {
            String time = s.substring(s.indexOf(" ") + 1, s.indexOf("] "));
            return Integer.valueOf(time.replace(":", ""));
        } else {
            return -1;
        }
    }

    private static boolean nextGuard(ArrayList<String> input, int i) {
        return getGuardId(input.get(i)) != -1;
    }

    private static int getGuardId(String s) {

        if (s.contains("#")) {
            String id = s.substring(s.indexOf("#") + 1, s.indexOf(" begins"));
            return Integer.valueOf(id);
        } else {
            return -1;
        }
    }

    //-----

    private static void log(String logLine) {
        System.out.println(logLine);
    }

    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0402.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
