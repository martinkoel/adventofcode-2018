package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day0301 {

    static int coords[][] = new int[1000][1000];


    public static void main(String[] args) throws FileNotFoundException {

        class Claim {
            int leftOffset;
            int topOffset;
            int tall;
            int wide;

            public Claim(int[] array) {
                this.leftOffset = array[0];
                this.topOffset = array[1];
                this.wide = array[2];
                this.tall = array[3];
            }
        }

        ArrayList<String> input = readFile("input-day03.txt");

        for (int i = 0; i < input.size(); i++) {

            Claim claim = new Claim(toIntArray(input.get(i)));

            for (int x = claim.leftOffset; x < claim.leftOffset + claim.wide; x++) {
                for (int y = claim.topOffset; y < claim.topOffset + claim.tall; y++) {
                    coords[x][y]++;
                }
            }
        }

        int moreThanOneClaim = 0;

        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                if (coords[x][y] > 1) {
                    moreThanOneClaim++;
                }
            }
        }

        System.out.print(moreThanOneClaim);
    }


    public static int[] toIntArray(String input) {

        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0301.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
