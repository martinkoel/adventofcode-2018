package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0801 {

    static String inputArray[];
    static int cursor = 0;
    static int sum = 0;

    public static void main(String[] args) throws FileNotFoundException {

        final ArrayList<String> input = readFile("input-day08.txt");

        inputArray = (input.get(0)).split(" ");

        while (cursor < inputArray.length) {
            log("Cursor at " + cursor);
            processNode();
        }

        log("Sum of meta information " + sum);

    }

    private static void processNode() {
        log("Processing node at cursor " + cursor);

        int nodes = Integer.valueOf(inputArray[cursor++]);
        int metas = Integer.valueOf(inputArray[cursor++]);

        while (nodes != 0) {
            processNode();
            nodes--;
        }

        while (metas != 0) {
            sum = sum + Integer.valueOf(inputArray[cursor++]);
            metas--;
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
