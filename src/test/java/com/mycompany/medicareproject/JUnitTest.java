/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.medicareproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {

    @Test
    public void testRegisterPatient() {

        Hospital hospital = new Hospital();

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT);

        boolean result = hospital.registerPatient(patient);

        assertTrue(result);
    }
}