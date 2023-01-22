/** 
 * Copyright (c) 2020 @rappelrx on GitHub
 */

import java.util.Scanner;

/**
 * Count number of adenine nucleobases in the COVID-19
 * sequence inputted by user (via usage of imported Scanner
 * class), as well as find its corresponding DNA sequence.
 * Instance variables are Strings adenine, thymine, cytosine,
 * and guanine to represent the four nucleobases A, T, C, G.
 */
public class CovidGenomeAnalysis {
    private static String adenine = "A";
    private static String thymine = "T";
    private static String cytosine = "C";
    private static String guanine = "G";

    /**
     * Turn user input into String to be referenced, loop
     * through the String to find number of A's, and
     * put together appropriate base pair letters in new
     * String to be printed out.
     * Special case: If for-loop encounters "A", then
     * increment count of A's.
     *
     * @param args not used
     * @return void, nothing returned
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        String inputDNA = scnr.next();
        scnr.close();

        int numA = 0; // set initial count to 0 A's.
        String outDNA = ""; // initialize output to empty String.

        for (int i = 0; i < inputDNA.length(); i++) {
            if (inputDNA.substring(i, i + 1).equals(adenine)) {
                numA++;
                outDNA = outDNA.concat(thymine);
            }
            if (inputDNA.substring(i, i + 1).equals(thymine)) {
                outDNA = outDNA.concat(adenine);
            }
            if (inputDNA.substring(i, i + 1).equals(cytosine)) {
                outDNA = outDNA.concat(guanine);
            }
            if (inputDNA.substring(i, i + 1).equals(guanine)) {
                outDNA = outDNA.concat(cytosine);
            }
        }

        System.out.println(numA + " " + outDNA);
    }
}