import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

public class TestInfectionTracking {
    private static final int ARRAY_LENGTH = 8;
    private String pathToFile;
    private String[] names;
    private int[] locations, movements, infections;

    @Before
    public void init() {
        pathToFile = "/Users/rappelr/Documents/covid-simulation/students.csv";
        names = new String[ARRAY_LENGTH];
        locations = new int[ARRAY_LENGTH];
        movements = new int[ARRAY_LENGTH];
        infections = new int[ARRAY_LENGTH];
    }
    
    @Test 
    public void testPopulateArrays() throws IOException {
        int maxLocationVal = InfectionTracking.populateArrays(pathToFile, names, locations, movements, infections);
        assertEquals(9, maxLocationVal); // return value of populateArrays()
        assertEquals("Yuumi", names[3]); // Yuumi's name
        assertEquals(-8, movements[6]); // Qiqi's movement
        assertEquals(1, infections[1]); // Eevee's infection status
    }

    @Test
    public void testUpdateLocations() throws IOException {
        InfectionTracking.populateArrays(pathToFile, names, locations, movements, infections);
        InfectionTracking.updateLocations(12, locations, movements); // time = 1

        // At time = 0, Bulbasaur's location = 4, movement = -5
        assertEquals(11, locations[0]); 
        // At time = 0, Yanfei's location = 7, movement = -15
        assertEquals(4, locations[7]); 
        // At time = 0, Teemo's location = 3, movement = +31
        assertEquals(10, locations[4]); 

        InfectionTracking.updateLocations(17, locations, movements); // time = 2

        // At time = 1, Teemo's location = 10, movement = +31
        assertEquals(7, locations[4]); 
        // At time = 0, Gengar's location = 4, movement = +2
        // At time = 1, Gengar's location = 6, movement = +2
        assertEquals(8, locations[2]);
    }
}
