package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day0302 {

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


        for (int i = 0; i < input.size(); i++) {

            Claim claim = new Claim(toIntArray(input.get(i)));

            int alReadyClaimedCounter = 0;

            for (int x = claim.leftOffset; x < claim.leftOffset + claim.wide; x++) {
                for (int y = claim.topOffset; y < claim.topOffset + claim.tall; y++) {
                    if (coords[x][y] > 1) {
                        alReadyClaimedCounter++;
                    }
                }
            }

            if (alReadyClaimedCounter == 0) {
                System.out.println(i + 1); //add one, as internal index starts at zero and input at 1
            }
        }


    }


    public static int[] toIntArray(String input) {

        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0302.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
