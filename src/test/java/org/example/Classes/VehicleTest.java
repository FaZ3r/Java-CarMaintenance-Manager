package org.example.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class VehicleTest {
  @Test
  void VehicleConstructorTest()
  {
      String make="Renault", model="Clio";
      int year=2009;
      boolean needsMaintenance=false;

      Vehicle test= new Car(make,model,year,needsMaintenance);
      assertEquals(make, test.make,"The make must be set correctly");
      assertEquals(model, test.model,"The model must also be set correctly");
      assertEquals(year,test.year,"The given year should be identical");
  }
}