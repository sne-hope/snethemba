/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.medicareproject;


import java.util.ArrayList;
import java.util.Scanner;

public class MedicareProject {

    private static Scanner input = new Scanner(System.in);
    private static Hospital hospital = new Hospital();

    public static void main(String[] args) {

        int choice = 0;

        System.out.println("==============================");
        System.out.println(" MEDICARE HOSPITAL SYSTEM ");
        System.out.println("==============================");

        do {

            displayMenu();

            choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;

                case 5:
                    displayPatients();
                    break;

                case 6:
                    allocateBed();
                    break;

                case 7:
                    releaseBed();
                    break;

                case 8:
                    hospital.displayWardLayout();
                    break;

                case 9:
                    displayAvailableBeds();
                    break;

                case 10:
                    displayOccupiedBeds();
                    break;

                case 11:
                    displayReport();
                    break;

                case 12:
                    sortPatients();
                    break;

                case 0:
                    System.out.println("Thank you for using MediCare Hospital.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    public static void displayMenu() {

        System.out.println("\n========== MAIN MENU ==========");

        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients");

        System.out.println("6. Allocate Bed");
        System.out.println("7. Release Bed");
        System.out.println("8. Display Ward Layout");
        System.out.println("9. Display Available Beds");
        System.out.println("10. Display Occupied Beds");

        System.out.println("11. Generate Report");
        System.out.println("12. Sort Patients");

        System.out.println("0. Exit");
    }

    // =================================
    // REGISTER
    // =================================

    public static void registerPatient() {

        System.out.println("\n--- REGISTER PATIENT ---");

        String id = getString("Patient ID: ");

        if (hospital.searchPatient(id) != null) {

            System.out.println("Patient ID already exists.");

            return;
        }

        String firstName = getString("First Name: ");
        String lastName = getString("Last Name: ");

        int age = getPositiveInt("Age: ");

        String gender = getString("Gender: ");

        String condition
                = getString("Medical Condition: ");

        System.out.println("\nPatient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        int categoryChoice
                = getInt("Choose category: ");

        Patient patient;

        switch (categoryChoice) {

            case 1:

                String wardNumber
                        = getString("Ward Number: ");

                patient = new Inpatient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        wardNumber);

                break;

            case 2:

                patient = new Patient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.OUTPATIENT);

                break;

            case 3:

                patient = new Patient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        PatientCategory.EMERGENCY);

                break;

            default:

                System.out.println("Invalid category.");

                return;
        }

        boolean registered
                = hospital.registerPatient(patient);

        if (registered) {
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("Patient could not be registered.");
        }
    }

    // =================================
    // SEARCH
    // =================================

    public static void searchPatient() {

        System.out.println("\n--- SEARCH PATIENT ---");

        String id = getString("Enter Patient ID: ");

        Patient patient
                = hospital.searchPatient(id);

        if (patient == null) {

            System.out.println("Patient not found.");

        } else {

            patient.displayDetails();
        }
    }

    // =================================
    // UPDATE
    // =================================

    public static void updatePatient() {

        System.out.println("\n--- UPDATE PATIENT ---");

        String id = getString("Enter Patient ID: ");

        Patient patient
                = hospital.searchPatient(id);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        String firstName = getString("New First Name: ");
        String lastName = getString("New Last Name: ");

        int age = getPositiveInt("New Age: ");

        String gender = getString("New Gender: ");

        String condition
                = getString("New Medical Condition: ");

        boolean updated
                = hospital.updatePatient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition);

        if (updated) {
            System.out.println("Patient updated successfully.");
        }
    }

    // =================================
    // DELETE
    // =================================

    public static void deletePatient() {

        System.out.println("\n--- DELETE PATIENT ---");

        String id = getString("Enter Patient ID: ");

        boolean deleted
                = hospital.deletePatient(id);

        if (deleted) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // =================================
    // DISPLAY PATIENTS
    // =================================

    public static void displayPatients() {

        System.out.println("\n--- ALL PATIENTS ---");

        ArrayList<Patient> patients
                = hospital.getPatients();

        if (patients.isEmpty()) {

            System.out.println("No patients registered.");

            return;
        }

        for (Patient patient : patients) {

            patient.displayDetails();
        }
    }

    // =================================
    // ALLOCATE BED
    // =================================

    public static void allocateBed() {

        System.out.println("\n--- ALLOCATE BED ---");

        if (hospital.getAvailableBeds().isEmpty()) {

            System.out.println("No beds are available.");

            return;
        }

        String id = getString("Enter Patient ID: ");

        Patient patient
                = hospital.searchPatient(id);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        if (!(patient instanceof Inpatient)) {

            System.out.println(
                    "Only inpatients can be allocated a bed.");

            return;
        }

        if (hospital.findPatientBed(id) != null) {

            System.out.println(
                    "This patient already has a bed.");

            return;
        }

        displayAvailableBeds();

        int bedNumber
                = getInt("Enter bed number (1-20): ");

        if (bedNumber < 1 || bedNumber > 20) {

            System.out.println("Invalid bed number.");

            return;
        }

        int row = (bedNumber - 1) / 5;
        int column = (bedNumber - 1) % 5;

        boolean allocated
                = hospital.allocateBed(
                        id,
                        row,
                        column);

        if (allocated) {

            System.out.println(
                    "Bed allocated successfully.");

        } else {

            System.out.println(
                    "Bed is already occupied.");
        }
    }

    // =================================
    // RELEASE BED
    // =================================

    public static void releaseBed() {

        System.out.println("\n--- RELEASE BED ---");

        String id = getString("Enter Patient ID: ");

        boolean released
                = hospital.releaseBed(id);

        if (released) {

            System.out.println("Bed released successfully.");

        } else {

            System.out.println(
                    "Patient does not have an allocated bed.");
        }
    }

    // =================================
    // AVAILABLE BEDS
    // =================================

    public static void displayAvailableBeds() {

        System.out.println("\n--- AVAILABLE BEDS ---");

        ArrayList<String> beds
                = hospital.getAvailableBeds();

        if (beds.isEmpty()) {

            System.out.println("No beds available.");

            return;
        }

        for (String bed : beds) {

            System.out.println(bed);
        }
    }

    // =================================
    // OCCUPIED BEDS
    // =================================

    public static void displayOccupiedBeds() {

        System.out.println("\n--- OCCUPIED BEDS ---");

        ArrayList<String> beds
                = hospital.getOccupiedBeds();

        if (beds.isEmpty()) {

            System.out.println("No occupied beds.");

            return;
        }

        for (String bed : beds) {

            System.out.println(bed);
        }
    }

    // =================================
    // REPORT
    // =================================

    public static void displayReport() {

        System.out.println("\n====== WARD REPORT ======");

        System.out.println(
                "Total Registered Patients: "
                + hospital.getTotalPatients());

        System.out.println(
                "Total Occupied Beds: "
                + hospital.getOccupiedBedCount());

        System.out.println(
                "Total Available Beds: "
                + hospital.getAvailableBeds().size());

        System.out.printf(
                "Ward Occupancy: %.2f%%\n",
                hospital.getOccupancyPercentage());

        System.out.println("\n--- REGISTERED PATIENTS ---");

        displayPatients();

        System.out.println("\n--- AVAILABLE BEDS ---");

        displayAvailableBeds();

        System.out.println("\n--- OCCUPIED BEDS ---");

        displayOccupiedBeds();
    }

    // =================================
    // SORT
    // =================================

    public static void sortPatients() {

        System.out.println("\n--- SORT PATIENTS ---");

        System.out.println("1. Sort by Surname");
        System.out.println("2. Sort by Patient ID");

        int choice
                = getInt("Choose option: ");

        if (choice == 1) {

            hospital.sortBySurname();

            System.out.println(
                    "Patients sorted by surname.");

            displayPatients();

        } else if (choice == 2) {

            hospital.sortByPatientID();

            System.out.println(
                    "Patients sorted by Patient ID.");

            displayPatients();

        } else {

            System.out.println("Invalid choice.");
        }
    }

    // =================================
    // EXCEPTION HANDLING
    // =================================

    public static int getInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        input.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number.");
            }
        }
    }

    public static int getPositiveInt(String message) {

        int number;

        do {

            number = getInt(message);

            if (number <= 0) {

                System.out.println(
                        "Please enter a value greater than 0.");
            }

        } while (number <= 0);

        return number;
    }

    public static String getString(String message) {

        String value;

        do {

            System.out.print(message);

            value = input.nextLine().trim();

            if (value.isEmpty()) {

                System.out.println(
                        "Input cannot be empty.");
            }

        } while (value.isEmpty());

        return value;
    }
}


