package org.example.Classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CarTest {

    @Test
    void constructorTest(){
        int year=2014;
        String make="BMW", model="X5";
        boolean maintenance=true;
        Car test = new Car(make, model, year, maintenance);

        assertEquals(make, test.make, "Values make should be the same.");
        assertEquals(model, test.model,"Model should be the same.");
        assertEquals(year, test.year,"Year must be identical.");
    }
    @Test
    void toFileFormatTest() {
        Car test = new Car("BMW", "Z3", 1999, true);
        assertEquals(test.make + ", " + test.model + ", " + test.year+", "+test.needsMaintenance,test.toFileFormat());
    }

    @Test
    void fromFileFormatTest() {
        String line="Skoda, Superb, 2020, true";
        Car test = new Car("","",0,false);
        test=test.fromFileFormat(line);
        assertEquals("Skoda",test.make);
        assertEquals("Superb",test.model);
        assertEquals(2020,test.year);
        assertTrue(test.needsMaintenance);
    }

    @Test
    void testToString() {
        Car test = new Car("BMW", "Z3", 1999, true);
        assertEquals("Car [make=" + test.make + ", model=" + test.model + ", year=" + test.year + ", needsMaintenance=" + test.needsMaintenance + "]",test.toString());
    }

    @Test
    void performMaintenanceTest() {
        Car test = new Car("BMW", "Z3", 1999, true);
        test.performMaintenance();
        assertFalse(test.needsMaintenance);
    }
}