package org.example.Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleApp {
    private static final String DATA_FILE = "vehicles.txt";
    private final List<Car> vehicles;

    public List<Car> getVehicles() {
        return vehicles;
    }


    public VehicleApp() {
        vehicles = new ArrayList<>();
        loadVehicles();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nVehicle Management System");
            System.out.println("1. List Vehicles");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Perform Maintenance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listVehicles();
                    break;
                case 2:
                    addVehicle(scanner);
                    break;
                case 3:
                    performMaintenance(scanner);
                    break;
                case 4:
                    saveVehicles();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void listVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
        } else {
            for (Car car : vehicles) {
                System.out.println(car.toString());
            }
        }
    }

    public void addVehicle(Scanner scanner) {
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Does the vehicle need maintenance (true/false)? ");
        boolean needsMaintenance = scanner.nextBoolean();
        scanner.nextLine();

        Car car = new Car(make, model, year, needsMaintenance);
        vehicles.add(car);
        System.out.println("Vehicle added.");
    }

    public void performMaintenance(Scanner scanner) {
        System.out.print("Enter vehicle index for maintenance: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < vehicles.size()) {
            Car car = vehicles.get(index);
            car.performMaintenance();
        } else {
            System.out.println("Invalid vehicle index.");
        }
    }
    public void loadVehicles() {
        File file = new File(DATA_FILE);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No previous data found, starting fresh.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = new Car("", "", 0, false);
                vehicles.add(car.fromFileFormat(line));
            }
            System.out.println("Vehicles loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading the vehicles file.");
            System.out.println(e.getMessage());
        }
    }

    public void saveVehicles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Car car : vehicles) {
                writer.write(car.toFileFormat());  // car to csv format
                writer.newLine();
            }
            System.out.println("Vehicles saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving vehicles.");
            System.out.println(e.getMessage());
        }
    }
}
