package nl.takmk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day0802 {

    static String inputArray[];
    static int cursor = 0;

    public static void main(String[] args) throws FileNotFoundException {

        inputArray = readFile("input-day08.txt").get(0).split(" ");

        log("Total is " + processNode("0"));

    }

    private static int processNode(String rootNode) {
        log("Processing rootnode: " + rootNode);

        int nodes = Integer.valueOf(inputArray[cursor++]);
        int metas = Integer.valueOf(inputArray[cursor++]);

        int nodeSum[] = new int[nodes];
        int metaValues[] = new int[metas];

        for (int i = 0; i < nodes; i++) {
            nodeSum[i] = processNode(rootNode + "." + i);
            log("Processed rootnode/node " + rootNode + "." + i + " the node sum: " + nodeSum[i]);
        }

        log("Processed rootnode: " + rootNode);

        for (int i = 0; i < metas; i++) {
            int curVal = Integer.valueOf(inputArray[cursor++]);
            metaValues[i] = curVal;
        }

        int total = 0;


        if (nodes > 0) {
            //return sum of nodes referred by meta values

            for (int i = 0; i < metas; i++) {
                int node = metaValues[i];

                log("Rootnode " + rootNode + " meta: " + i + " value: " + node + " (nodes: " + nodes + ")");

                if (node <= nodes) {
                    log("Rootnode " + rootNode + " to be added: " + nodeSum[node - 1]);
                    total = total + nodeSum[node - 1];
                } else {
                    log("Rootnode " + rootNode + " ignoring, out of bound");
                }
            }
        } else {
            //return sum of meta values

            for (int i = 0; i < metas; i++) {
                total = total + metaValues[i];
            }

        }

        log("Rootnode " + rootNode + " total " + total);

        return total;

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
