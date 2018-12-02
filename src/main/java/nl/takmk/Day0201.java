package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0201 {

    public final static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> input = readFile("input-day02.txt");

        int twos = 0;
        int threes = 0;

        for (String cur : input) {

            if (hasTwoMatchingLetters(cur)) {
                twos++;
            }

            if (hasThreeMatchingLetters(cur)) {
                threes++;
            }

            System.out.println(twos + "-" + threes + "-" + twos * threes);
        }

    }

    private static boolean hasTwoMatchingLetters(final String cur) {
        return hasMatchingCharacters(cur, 2);
    }

    private static boolean hasThreeMatchingLetters(final String cur) {
        return hasMatchingCharacters(cur, 3);
    }

    private static boolean hasMatchingCharacters(final String cur, final int count) {
        for (int i = 0; i < alphabet.length; i++) {

            String[] split = (cur + "X").split(alphabet[i]); // append X not to miss out on edge cases

            if (count == split.length - 1) { // subtract 1 to get actual length that can be compared
                System.out.println(alphabet[i] + "-" + cur + "-" + count);
                return true;
            }
        }

        return false;
    }


    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0201.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
