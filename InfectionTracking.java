/** 
 * Copyright (c) 2020 @rappelrx on GitHub
 */

import java.io.IOException; // for throwing IOException
import java.util.Scanner; // to scan input file
import java.io.File; // to allow File object
import java.util.Arrays; // for converting arrays to Strings

/**
 * This class uses arrays to keep track of students' names,
 * locations, movements, and infection status. Using if-else
 * statements, the class checks validity of inputs, as well as
 * organize values into the appropriate indices of arrays with
 * the help of for-loops.
 */
public class InfectionTracking {
    private static final String EMPTY_STRING = ""; // initialize String
    private static final int TEST_FOUR = 4; // for testing purposes

    /**
     * This main method is for testing purposes only.
     * 
     * @param args not used
     * @return void
     */
    public static void main(String[] args) throws IOException {
        String[] names = new String[TEST_FOUR];
        int[] locations = new int[TEST_FOUR];
        int[] movements = new int[TEST_FOUR];
        int[] infections = new int[TEST_FOUR];
        int s = populateArrays("Students.csv",
                names, locations, movements, infections);
        System.out.println(Arrays.toString(names));
        System.out.println(Arrays.toString(locations));
        System.out.println(Arrays.toString(movements));
        System.out.println(Arrays.toString(infections));
        System.out.println(s);
    }

    /**
     * Populates arrays for names, locations, movements, and
     * infection status.
     * 
     * @param pathToFile - String, input file
     * @param names      - String[] array for students' names
     * @param locations  - int[] for students' locations
     * @param movements  - int[] for students' movements in location
     * @param infections - int[] for infected (1) or not (0)
     * @return int for maximum location value + 1
     */
    public static int populateArrays(String pathToFile, String[] names,
            int[] locations, int[] movements, int[] infections) throws IOException {
        if (names == null || locations == null || movements == null
                || infections == null) {
            return -1; // the arrays do not exist
        } else if (pathToFile == null) {
            return -1; // the file does not exist
        } else if (names.length != locations.length
                || names.length != movements.length
                || names.length != infections.length) {
            return -1; // the arrays are not same length
        }

        int maxLocationVal = 0; // initialize return value to 0
        File file = new File(pathToFile);
        Scanner scnr = new Scanner(file);
        // delimiter is comma and newline for scanner to use
        scnr.useDelimiter("\\s*[,\\n]\\s*");
        int count = 0;
        while (scnr.hasNext() && count < names.length) {
            names[count] = scnr.next();
            locations[count] = scnr.nextInt();
            if (maxLocationVal < locations[count] + 1) {
                // set new highest value for return value
                maxLocationVal = locations[count] + 1;
            }
            movements[count] = scnr.nextInt();
            infections[count] = scnr.nextInt();
            scnr.close();
            if (infections[count] != 0 && infections[count] != 1) {
                return -1; // input has invalid number for infection
            }
            count++;
        }

        return maxLocationVal;
    }

    /**
     * Uses locations and movements arrays to update new locations
     * array in a specific array "world" size.
     * 
     * @param worldSize - int specifies array size for world
     * @param locations - int[] for students' locations
     * @param movements - int[] for students' movements in location
     * @return void
     */
    public static void updateLocations(int worldSize, int[] locations,
            int[] movements) {
        // checking null first before checking out-of-bounds
        if (locations == null || movements == null) {
            return;
        }
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] < 0 || locations[i] >= worldSize) {
                return; // element(s) of locations array is out-of-bounds
            }
        }
        if (worldSize < 0) {
            return; // negative world size
        } else if (locations.length != movements.length) {
            return; // arrays are not same length
        } else {
            for (int i = 0; i < locations.length; i++) {
                int hold = locations[i]; // holds original value at index
                hold = locations[i] + movements[i];
                if (hold < 0) {
                    // edge case for initial negative location
                    locations[i] = worldSize + (hold % worldSize);
                } else if (hold >= worldSize) {
                    // edge case for initial location larger than world size
                    locations[i] = hold % worldSize;
                } else {
                    locations[i] = hold;
                }
            }
        }
    }

    /**
     * Uses infections and locations arrays to update infection
     * status of uninfected who share location with infected student(s).
     * 
     * @param worldSize  - int array size for world
     * @param infections - int[] for infections status
     * @param locations  - int[] for students' locations
     * @return int[] array for number of infections by each student
     */
    public static int[] updateInfections(int worldSize, int[] infections,
            int[] locations) {
        if (worldSize < 0) {
            return null; // negative world size
        } else if (infections == null || locations == null) {
            return null; // the arrays do not exist
        } else if (infections.length != locations.length) {
            return null; // the arrays are not same length
        }
        for (int h = 0; h < infections.length; h++) {
            if (locations[h] < 0 || locations[h] >= worldSize) {
                return null; // out-of-bounds elements in location
            }
            if (infections[h] != 0 && infections[h] != 1) {
                return null; // invalid infection status number
            }
        }

        // create new parallel array for number infections by each student
        int[] numStudentsInfected = new int[infections.length];

        for (int i = 0; i < infections.length; i++) {
            if (infections[i] == 0) {
                numStudentsInfected[i] = 0; // cannot infect anyone
            } else if (infections[i] == 1) {
                int numInfect = 0; // initialize to 0 infections caused
                for (int j = 0; j < infections.length; j++) {
                    // checks for uninfected in same location as infected
                    if (locations[i] == locations[j]
                            && infections[j] == 0) {
                        numInfect++;
                    }
                }
                numStudentsInfected[i] = numInfect;
            }
        }

        // separate loop to update infections status array
        for (int k = 0; k < infections.length; k++) {
            if (infections[k] == 0) {
                // check to see if share same location with infected
                for (int l = 0; l < infections.length; l++) {
                    if (infections[l] == 1 && locations[l] == locations[k]) {
                        infections[k] = 1; // now infected
                    }
                }
            }
        }

        return numStudentsInfected;
    }

    /**
     * Keeps track of how many infections total caused by each
     * student over a specified number of days.
     * 
     * @param days       - int number of days (time period of infections)
     * @param worldSize  - int array size for world
     * @param locations  - int[] for students' locations
     * @param movements  - int[] for movements in location
     * @param infections - int[] for students' infection status
     * @return int[] array of infection records for each student
     */
    public static int[] countInfectionsByStudent(int days, int worldSize,
            int[] locations, int[] movements, int[] infections) {
        if (days < 0) {
            return null; // negative number of days
        }
        if (worldSize < 0) {
            return null; // negative world size
        }
        if (locations == null || movements == null || infections == null) {
            return null; // the arrays do not exist
        }
        if (locations.length != movements.length
                || locations.length != infections.length) {
            return null; // the arrays are not same length
        }
        for (int h = 0; h < locations.length; h++) {
            if (locations[h] < 0 || locations[h] >= worldSize) {
                return null; // location element out-of-bounds
            }
            if (infections[h] != 0 && infections[h] != 1) {
                return null; // invalid infection status number
            }
        }

        // new parallel array keeps track of infection record for each student
        int[] totalInfect = new int[infections.length];

        for (int i = 1; i <= days; i++) {
            updateLocations(worldSize, locations, movements);
            // create variable in order to access array elements for each day
            int[] oneDay = updateInfections(worldSize, infections, locations);
            for (int j = 0; j < oneDay.length; j++) {
                // adds a day's worth of infections to infection record
                totalInfect[j] = totalInfect[j] + oneDay[j];
            }
        }

        return totalInfect;
    }

    /**
     * Finds average number of people that would be infected by
     * one infected person.
     * 
     * @param infectionRecord - int[] for infection record of each student
     * @return average (ceiling value) number people infected by someone
     */
    public static int findRNaught(int[] infectionRecord) {
        if (infectionRecord == null || infectionRecord.length == 0) {
            return -1; // invalid array for infection records
        }

        // used doubles for double division when finding average
        double sum = 0.0;
        double numStudents = 0.0;
        for (int i = 0; i < infectionRecord.length; i++) {
            if (infectionRecord[i] < 0) {
                return -1; // negative number of infections; invalid
            }
            sum += infectionRecord[i];
            numStudents++;
        }

        // takes ceiling value in order to represent integer number of people
        int rNaught = (int) Math.ceil(sum / numStudents);
        return rNaught;
    }

    /**
     * Checks which student has caused the most infections.
     * 
     * @param infectionRecord - int[] for infection record of each student
     * @param names           - String[] for students' names
     * @return String name of student who spread infections the most
     */
    public static String findSuperSpreader(int[] infectionRecord, String[] names) {
        if (infectionRecord == null || names == null) {
            return null; // the arrays do not exist
        }
        if (infectionRecord.length == 0 || names.length == 0) {
            return null; // the arrays have no elements
        }
        if (infectionRecord.length != names.length) {
            return null; // the arrays are not same length
        }

        int maxInfect = 0; // initialize max number of infections to 0
        String superSpreader = EMPTY_STRING; // initialize name to empty string
        for (int i = 0; i < infectionRecord.length; i++) {
            if (infectionRecord[i] < 0) {
                return null; // negative infection record; invalid
            }

            // checks to see if student has caused more infections
            if (infectionRecord[i] > maxInfect) {
                maxInfect = infectionRecord[i];
                superSpreader = names[i];
            }
        }

        return superSpreader;
    }
}