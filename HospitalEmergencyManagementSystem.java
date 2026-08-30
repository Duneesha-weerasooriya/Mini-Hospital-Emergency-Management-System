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
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
    }