/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicareproject;

/**
 *
 * @author Snethemba
 */
import java.util.ArrayList;

public class Hospital {
    
  

    private ArrayList<Patient> patients;

    // 4 rows and 5 columns = 20 beds
    private Patient[][] beds;

    public Hospital() {

        patients = new ArrayList<>();

        beds = new Patient[4][5];
    }

    // ==============================
    // REGISTER PATIENT
    // ==============================

    public boolean registerPatient(Patient patient) {

        if (patient == null) {
            return false;
        }

        if (searchPatient(patient.getPatientID()) != null) {
            return false;
        }

        patients.add(patient);

        return true;
    }

    // ==============================
    // SEARCH PATIENT
    // ==============================

    public Patient searchPatient(String patientID) {

        for (Patient patient : patients) {

            if (patient.getPatientID().equalsIgnoreCase(patientID)) {
                return patient;
            }
        }

        return null;
    }

    // ==============================
    // UPDATE PATIENT
    // ==============================

    public boolean updatePatient(String patientID,
            String firstName,
            String lastName,
            int age,
            String gender,
            String medicalCondition) {

        Patient patient = searchPatient(patientID);

        if (patient == null) {
            return false;
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);

        return true;
    }

    // ==============================
    // DELETE PATIENT
    // ==============================

    public boolean deletePatient(String patientID) {

        Patient patient = searchPatient(patientID);

        if (patient == null) {
            return false;
        }

        // Release bed first if patient has one
        releaseBed(patientID);

        patients.remove(patient);

        return true;
    }

    // ==============================
    // GET PATIENTS
    // ==============================

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    // ==============================
    // ALLOCATE BED
    // ==============================

    public boolean allocateBed(String patientID,
            int row, int column) {

        Patient patient = searchPatient(patientID);

        if (patient == null) {
            return false;
        }

        // Only inpatients may receive beds
        if (!(patient instanceof Inpatient)) {
            return false;
        }

        if (row < 0 || row >= 4
                || column < 0 || column >= 5) {
            return false;
        }

        // Bed is already occupied
        if (beds[row][column] != null) {
            return false;
        }

        // Check if patient already has a bed
        if (findPatientBed(patientID) != null) {
            return false;
        }

        beds[row][column] = patient;

        String bedNumber = getBedNumber(row, column);

        Inpatient inpatient = (Inpatient) patient;
        inpatient.setBedNumber(bedNumber);

        return true;
    }

    // ==============================
    // FIND PATIENT BED
    // ==============================

    public String findPatientBed(String patientID) {

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                    column < beds[row].length;
                    column++) {

                if (beds[row][column] != null) {

                    if (beds[row][column]
                            .getPatientID()
                            .equalsIgnoreCase(patientID)) {

                        return getBedNumber(row, column);
                    }
                }
            }
        }

        return null;
    }

    // ==============================
    // RELEASE BED
    // ==============================

    public boolean releaseBed(String patientID) {

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                    column < beds[row].length;
                    column++) {

                if (beds[row][column] != null) {

                    if (beds[row][column]
                            .getPatientID()
                            .equalsIgnoreCase(patientID)) {

                        if (beds[row][column]
                                instanceof Inpatient) {

                            Inpatient inpatient
                                    = (Inpatient) beds[row][column];

                            inpatient.setBedNumber("");
                        }

                        beds[row][column] = null;

                        return true;
                    }
                }
            }
        }

        return false;
    }

    // ==============================
    // BED NUMBER
    // ==============================

    public String getBedNumber(int row, int column) {

        int number = row * 5 + column + 1;

        if (number < 10) {
            return "B0" + number;
        }

        return "B" + number;
    }

    // ==============================
    // GET AVAILABLE BEDS
    // ==============================

    public ArrayList<String> getAvailableBeds() {

        ArrayList<String> availableBeds
                = new ArrayList<>();

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                    column < beds[row].length;
                    column++) {

                if (beds[row][column] == null) {

                    availableBeds.add(
                            getBedNumber(row, column));
                }
            }
        }

        return availableBeds;
    }

    // ==============================
    // GET OCCUPIED BEDS
    // ==============================

    public ArrayList<String> getOccupiedBeds() {

        ArrayList<String> occupiedBeds
                = new ArrayList<>();

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                    column < beds[row].length;
                    column++) {

                if (beds[row][column] != null) {

                    String information
                            = getBedNumber(row, column)
                            + " - "
                            + beds[row][column]
                                    .getPatientID();

                    occupiedBeds.add(information);
                }
            }
        }

        return occupiedBeds;
    }

    // ==============================
    // COUNT OCCUPIED BEDS
    // ==============================

    public int getOccupiedBedCount() {

        int count = 0;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                    column < beds[row].length;
                    column++) {

                if (beds[row][column] != null) {
                    count++;
                }
            }
        }

        return count;
    }

    // ==============================
    // TOTAL PATIENTS
    // ==============================

    public int getTotalPatients() {
        return patients.size();
    }

    // ==============================
    // OCCUPANCY PERCENTAGE
    // ==============================

    public double getOccupancyPercentage() {

        return (getOccupiedBedCount() / 20.0) * 100;
    }

    // ==============================
    // SORT BY SURNAME
    // Simple bubble sort
    // ==============================

    public void sortBySurname() {

        for (int i = 0;
                i < patients.size() - 1;
                i++) {

            for (int j = 0;
                    j < patients.size() - i - 1;
                    j++) {

                String surname1
                        = patients.get(j).getLastName();

                String surname2
                        = patients.get(j + 1).getLastName();

                if (surname1.compareToIgnoreCase(surname2) > 0) {

                    Patient temporary
                            = patients.get(j);

                    patients.set(j,
                            patients.get(j + 1));

                    patients.set(j + 1,
                            temporary);
                }
            }
        }
    }

    // ==============================
    // SORT BY PATIENT ID
    // ==============================

    public void sortByPatientID() {

        for (int i = 0;
                i < patients.size() - 1;
                i++) {

            for (int j = 0;
                    j < patients.size() - i - 1;
                    j++) {

                String id1
                        = patients.get(j).getPatientID();

                String id2
                        = patients.get(j + 1).getPatientID();

                if (id1.compareToIgnoreCase(id2) > 0) {

                    Patient temporary
                            = patients.get(j);

                    patients.set(j,
                            patients.get(j + 1));

                    patients.set(j + 1,
                            temporary);
                }
            }
        }
    }

    // ==============================
    // DISPLAY WARD
    // ==============================

    public void displayWardLayout() {

        System.out.println("\n--- WARD LAYOUT ---");

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                    column < beds[row].length;
                    column++) {

                String bedNumber
                        = getBedNumber(row, column);

                if (beds[row][column] == null) {

                    System.out.print(
                            "[" + bedNumber + " FREE] ");
                } else {

                    System.out.print(
                            "[" + bedNumber + " OCC]  ");
                }
            }

            System.out.println();
        }
    }
}
    

