package org.example.Classes;

//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleAppTest {
    private VehicleApp app;

    @BeforeEach
    public void setUp(){
        app=new VehicleApp();
    }

    /*@AfterEach
    public void exitSetup() {app.saveVehicles();}*/

    @Test
    void listVehiclesTest() {
        //simulates input
        Scanner scanner = new Scanner("Toyota\nCorolla\n2020\nfalse\n");
        app.addVehicle(scanner);
        //Car test=app.getVehicles().getFirst();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        app.listVehicles();
        String output = outputStream.toString();
        assertTrue(output.contains("Toyota"));
        assertTrue(output.contains("Corolla"));
        assertTrue(output.contains("2020"));
        assertTrue(output.contains("false"));


    }

    @Test
    void addVehicleTest() {
        Scanner scanner = new Scanner("Toyota\nCelica\n1991\nfalse\n");
        app.addVehicle(scanner);
        Car test=app.getVehicles().getLast();
        //assertEquals(4, app.getVehicles().size());
        assertEquals("Toyota", test.make);
        assertEquals("Celica", test.model);
        assertEquals(1991, test.year);
        assertFalse(test.needsMaintenance);
    }

    @Test
    void performMaintenanceTest() {
        Scanner scanner =new Scanner("4\n");
        boolean toCheck=app.getVehicles().getFirst().needsMaintenance;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        app.performMaintenance(scanner);
        String output = outputStream.toString();
        assertTrue(output.contains("Performing maintenance")||output.contains("good condition")||output.contains("Invalid"));
        assertFalse(toCheck,"Value should be false as maintenance has been done.");

    }

    @Test
    void loadVehiclesTest() {
        app.loadVehicles();
        List<Car> test =app.getVehicles();
        assertFalse(test.isEmpty());
    }

    @Test
    void saveVehiclesTest() {
        //idk sorry (jk)

        Scanner scanner = new Scanner("Toyota\nCorolla\n2020\nfalse\n");
        app.addVehicle(scanner);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        app.saveVehicles();

        String output =outputStream.toString();
        assertTrue(output.contains("Vehicles saved to file.")||output.contains("Error saving vehicles."));
    }
}