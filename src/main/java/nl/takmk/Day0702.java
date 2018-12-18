package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day0702 {

    public static ArrayList[] instructions = new ArrayList[26];

    public static void main(String[] args) throws FileNotFoundException {

        for (int i = 0; i < instructions.length; i++) {
            instructions[i] = new ArrayList();
        }

        final ArrayList<String> input = readFile("input-day07.txt");

        for (int i = 0; i < input.size(); i++) {
            String[] instruction = toStringArray(input.get(i));
            instructions[getAlphabetPos(instruction[1])].add(getAlphabetPos(instruction[0]));
        }


        String result = "";
        int lastResultLength = -1;

       for (int a =0;a<150;a++) {
//        while (lastResultLength != result.length() ) {
            for (int i = 0; i < instructions.length; i++) {
                lastResultLength = result.length();
                log("current result:" + result);
                log("instructions: " + i + "-" + instructions[i]);



                if (!isDone(instructions[i]) && isAvailable(instructions[i])) {
                    log(i + " is available");
                    result = result + alphabetPostToString(i);

                    for (int ii = 0; ii < instructions.length; ii++) {

                        for (int iii = 0; iii < instructions[ii].size(); iii++) {
                            log("i =" + i);
                            log((int) instructions[ii].get(iii) + "");

                            if (((int) instructions[ii].get(iii)) == i) {

                                log("size before" + instructions[ii].size());
                                instructions[ii].remove(iii);

                                log("size after" + instructions[ii].size());
                                if(instructions[i].size()==0) {
                                    instructions[i].add(99);
                                }
                            }
                        }
                    }

                   break;

                } else {
                    log(i + " is not available");
                }

            }
        }


//        for (int i = 0; i < instructions.length; i++) {
//            //log(i + "-" + instructions[i]);
//            if (instructions[i].size() > 0) {
//                log(i + "-" + instructions[i].get(0));
//            } else {
//                log(i +"");
//            }
//        }

        log(result);

    }

    private static boolean isDone(ArrayList instruction) {
        boolean isDone = instruction.size()>0 && ((int)instruction.get(0))==99;
        if(instruction.size()>0){log((int)instruction.get(0)+"value" + isDone);};
        return isDone;
    }


    public static String alphabetPostToString(int alphabetPos) {
        return Character.toString((char) ('a' + alphabetPos));
    }

    public static boolean isAvailable(ArrayList b) {

        return b.size() == 0;


    }

    public static int getAlphabetPos(String character) {

        character = character.toLowerCase();
        return character.charAt(0) - 'a';
    }

    //-----

    public static String[] toStringArray(String input) {

        return input.split(",");
    }


    public static int[] toIntArray(String input) {

        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }


    private static void log(String logLine) {
        System.out.println(logLine);
    }

    private static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<String>();
        ClassLoader classLoader = Day0702.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            input.add(String.valueOf(scanner.nextLine()));
        }

        return input;
    }
}
