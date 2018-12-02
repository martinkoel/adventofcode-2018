package nl.takmk;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0101 {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Integer> input = readFile("input-day01-01.txt");

        int sum = input.stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum);
    }

    private static ArrayList<Integer> readFile(String fileName) throws FileNotFoundException {
        ArrayList<Integer> input = new ArrayList<Integer>();
        ClassLoader classLoader = Day0101.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(Integer.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
