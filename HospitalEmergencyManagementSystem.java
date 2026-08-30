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


// ============================================================
// BINARY SEARCH TREE FOR PATIENT RECORDS
// Key = Patient ID
// ============================================================
class PatientRecordBST {

    private BSTNode root;

    public PatientRecordBST() {
        root = null;
    }

    // --------------------------------------------------------
    // Insert patient
    // --------------------------------------------------------
    public boolean insertPatient(Patient patient) {
        if (searchPatient(patient.getPatientId()) != null) {
            System.out.println("Patient ID already exists.");
            return false;
        }

        root = insertRec(root, patient);
        System.out.println("Patient registered successfully: "
                + patient.getPatientName());

        return true;
    }

    private BSTNode insertRec(BSTNode root, Patient patient) {

        if (root == null) {
            return new BSTNode(patient);
        }

        if (patient.getPatientId()
                .compareTo(root.patient.getPatientId()) < 0) {

            root.left = insertRec(root.left, patient);

        } else if (patient.getPatientId()
                .compareTo(root.patient.getPatientId()) > 0) {

            root.right = insertRec(root.right, patient);
        }

        return root;
    }

    // --------------------------------------------------------
    // Search patient
    // --------------------------------------------------------
    public Patient searchPatient(String patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(BSTNode root, String patientId) {

        if (root == null) {
            return null;
        }

        if (root.patient.getPatientId().equals(patientId)) {
            return root.patient;
        }

        if (patientId.compareTo(root.patient.getPatientId()) < 0) {
            return searchRec(root.left, patientId);
        }

        return searchRec(root.right, patientId);
    }
