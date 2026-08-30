package HospitalEmergencyManagementSystem;

import java.util.*;

// ============================================================
// PATIENT CLASS
// Represents an individual patient
// ============================================================
class Patient {
    private String patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;

    public Patient(String patientId, String patientName, int age,
                   String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                ", Name: " + patientName +
                ", Age: " + age +
                ", Contact: " + contactNumber +
                ", Condition: " + medicalCondition;
    }
}


// ============================================================
// BST NODE
// ============================================================
class BSTNode {
    Patient patient;
    BSTNode left;
    BSTNode right;

    public BSTNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}
