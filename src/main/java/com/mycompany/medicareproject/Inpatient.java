/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicareproject;

public class Inpatient extends Patient {

    private String wardNumber;
    private String bedNumber;

    public Inpatient(String patientID, String firstName,
            String lastName, int age, String gender,
            String medicalCondition, String wardNumber) {

        super(patientID, firstName, lastName, age,
                gender, medicalCondition,
                PatientCategory.INPATIENT);

        this.wardNumber = wardNumber;
        this.bedNumber = "";
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Ward Number: " + wardNumber);

        if (bedNumber.equals("")) {
            System.out.println("Bed Number: Not allocated");
        } else {
            System.out.println("Bed Number: " + bedNumber);
        }
    }
}
