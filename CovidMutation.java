/** 
 * Copyright (c) 2020 @rappelrx on GitHub
 */

import java.util.Scanner;

/**
 * This class mutates a given genome sequence of COVID-19.
 * Using the imported Scanner class, CovidMutation is able
 * to take in input from user and manipulate the input in code.
 */
public class CovidMutation {
    /**
     * Turn user input into String to be referenced, loop through
     * the String to reverse lettering for every k size chunk
     * and put mutated sequence in new String to be printed out.
     * Special cases include when the k value is 0 or less,
     * and when the k value is greater than the given
     * genome sequence length.
     *
     * @param args not used
     * @return void nothing returned
     */
    public static void main(String[] args) {
        String reverseSeq = ""; // initialize output to empty String.

        Scanner scnr = new Scanner(System.in);
        String genomeSeq = scnr.nextLine();
        int kValue = Integer.parseInt(scnr.nextLine());
        scnr.close();

        if (kValue <= 0) {
            reverseSeq = genomeSeq;
        } else if (kValue < genomeSeq.length()) {
            int currentSeg = 0; // keep track of which chunk in loop
            int numChunks = genomeSeq.length() / kValue;
            int remainder = genomeSeq.length() % kValue;

            for (int h = numChunks; h > 0; h--) {
                for (int i = kValue - 1; i >= 0; i--) {
                    int subStart = i + currentSeg; // for substring
                    int subEnd = subStart + 1; // for substring

                    reverseSeq // statement continues on new line
                            = reverseSeq.concat(genomeSeq.substring(subStart,
                                    subEnd));

                    if (i == 0) {
                        currentSeg += kValue; // move to next chunk
                    }
                }
            }

            for (int i = genomeSeq.length() - 1; i > genomeSeq.length() - 1 - remainder; i--) {
                reverseSeq = reverseSeq.concat(genomeSeq.substring(i, i + 1));
            }
        } else {
            for (int i = genomeSeq.length() - 1; i >= 0; i--) {
                reverseSeq = reverseSeq.concat(genomeSeq.substring(i, i + 1));
            }
        }

        System.out.println(reverseSeq);
    }
}