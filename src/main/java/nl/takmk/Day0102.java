package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0102 {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Integer> input = readFile("input-day01-01.txt");
        ArrayList<Integer> frequencies = new ArrayList<>();

        int num01 = 0;
        int currentFrequency;
        boolean found = false;

        while (!found) {

            for (Integer cur : input) {
                currentFrequency = num01 + cur.intValue();

                if (frequencies.contains(currentFrequency)) {
                    System.out.println("First occurrence of same frequency is: " + currentFrequency);
                    found = true;
                    break;
                }

                num01 = currentFrequency;
                frequencies.add(currentFrequency);
            }
        }
    }

    private static ArrayList<Integer> readFile(String fileName) throws FileNotFoundException {
        ArrayList<Integer> input = new ArrayList<Integer>();
        ClassLoader classLoader = Day0102.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(Integer.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
