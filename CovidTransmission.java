/** 
 * Copyright (c) 2020 @rappelrx on GitHub
 */

import java.util.Scanner;

/**
 * This class uses the Scanner class to determine total 
 * time of contact based on user input in the format:
 * (startDay startHour startMin endDay endHour endMin).
 */
public class CovidTransmission {
    
    // Magic numbers
    private static final int MAX_DAY = 31;
    private static final int MAX_HOUR = 23;
    private static final int MAX_MIN = 59;
    private static final int MINS_IN_DAY = 1440;
    private static final int SEC_IN_MIN = 60;
    private static final int HRS_IN_DAY = 24;
    private static final int THREE_HOURS = 180;
    private static final int SIX_HOURS = 360;
    private static final String EMPTY_STRING = "";
    private static final String LOW_RISK = "low";
    private static final String MED_RISK = "medium";
    private static final String HIGH_RISK = "high";
    private static final String EXT_HIGH_RISK = "extremely high";

    /** 
     * Prints total time of contact (in minutes) and
     * risk level of contracting Covid based on this 
     * total time of contact.
     * 
     * @param args, not used
     * @return void, nothing returned
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int numMinutes = 0; // initialize
        String riskLevel = EMPTY_STRING; // initialize

        String d1Input = scnr.next();
        String h1Input = scnr.next();
        String m1Input = scnr.next();
        String d2Input = scnr.next();
        String h2Input = scnr.next();
        String m2Input = scnr.next();

        int d1 = Integer.parseInt(d1Input);
        int h1 = Integer.parseInt(h1Input);
        int m1 = Integer.parseInt(m1Input);
        int d2 = Integer.parseInt(d2Input);
        int h2 = Integer.parseInt(h2Input);
        int m2 = Integer.parseInt(m2Input);

        // Check for invalid inputs.
        if ((d1 < 0 || d1 > MAX_DAY) || (d2 < 0 || d2 > MAX_DAY)) {
            numMinutes = -1;
            riskLevel = "-1";
        }
        if ((h1 < 0 || h1 > MAX_HOUR) || (h2 < 0 || h2 > MAX_HOUR)) {
            numMinutes = -1;
            riskLevel = "-1";
        }
        if ((m1 < 0 || m1 > MAX_MIN) || (m2 < 0 || m2 > MAX_MIN)) {
            numMinutes = -1;
            riskLevel = "-1";
        }

        // Determine number of minutes of contact.
        if (riskLevel != "-1") {
            numMinutes = (d2 - d1) * MINS_IN_DAY;

            if (h2 < h1) {
                numMinutes -= MINS_IN_DAY;
                numMinutes += ((HRS_IN_DAY - h1 + h2) * SEC_IN_MIN);
            } else {
                numMinutes += (h2 - h1) * SEC_IN_MIN;
            }

            if (m2 < m1) {
                numMinutes -= SEC_IN_MIN;
                numMinutes += (SEC_IN_MIN - m1 + m2);
            } else {
                numMinutes += (m2 - m1);
            }
        }

        // Determine risk level of contracting Covid.
        if (numMinutes >= 0 && numMinutes <= SEC_IN_MIN) {
            riskLevel = LOW_RISK;
        } else if (numMinutes > SEC_IN_MIN && numMinutes <= THREE_HOURS) {
            riskLevel = MED_RISK;
        } else if (numMinutes > THREE_HOURS && numMinutes <= SIX_HOURS) {
            riskLevel = HIGH_RISK;
        } else if (numMinutes > SIX_HOURS) {
            riskLevel = EXT_HIGH_RISK;
        }

        // Invalid number of minutes.
        if (numMinutes < 0) {
            numMinutes = -1;
            riskLevel = "-1";
        }

        System.out.println(numMinutes + " " + riskLevel);
        scnr.close();
    }
}