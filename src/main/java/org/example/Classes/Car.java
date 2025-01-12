package org.example.Classes;
import org.example.Interfaces.Maintained;

public class Car extends Vehicle implements Maintained{
    boolean needsMaintenance;

    Car(String make, String model, int year, boolean needsMaintenance){
        super(make,model,year);
        this.needsMaintenance=needsMaintenance;
    }
    @Override
    public String toFileFormat()
    {
        return make + ", " + model + ", " + year+", "+needsMaintenance;
    }
    @Override
    Car fromFileFormat(String line) {
        if (line == null || line.trim().isEmpty()) /*line check*/{
            return null;
        }
        String[] parts = line.split(",");

        if (parts.length < 4) {
            System.out.println("Skipping malformed line: " + line);
            return null;
        }

        String make = parts[0].trim();
        String model = parts[1].trim();
        int year;
        boolean needsMaintenance;

        try {
            year = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year format for line: " + line);
            return null;
        }

        try {
            needsMaintenance = Boolean.parseBoolean(parts[3].trim());
        } catch (Exception e) {
            System.out.println("Invalid maintenance status for line: " + line);
            return null;
        }

        return new Car(make, model, year, needsMaintenance);
    }

    @Override
    public String toString(){
        return "Car [make=" + make + ", model=" + model + ", year=" + year + ", needsMaintenance=" + needsMaintenance + "]";
    }
    @Override
    public void performMaintenance(){
        if(needsMaintenance){
            System.out.println("Performing maintenance on "+ model);
            needsMaintenance=false;
        }
        else{
            System.out.println (model+ " is in good condition.");
        }
    }
}
