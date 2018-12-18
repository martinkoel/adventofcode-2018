package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0801 {

    static String inputArray[];
    static int cursor = 0, sum = 0;

    public static void main(String[] args) throws FileNotFoundException {

        inputArray = readFile("input-day08.txt").get(0).split(" ");

        processNode();

        log("Sum of meta information " + sum);
    }

    private static void processNode() {
        log("Processing node at cursor " + cursor);

        int nodes = Integer.valueOf(inputArray[cursor++]);
        int metas = Integer.valueOf(inputArray[cursor++]);

        while (nodes-- != 0) {
            processNode();
        }

        while (metas-- != 0) {
            sum = sum + Integer.valueOf(inputArray[cursor++]);
        }

    }


    //-----

    private static void log(String logLine) {
        System.out.println(logLine);
    }

    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0801.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
