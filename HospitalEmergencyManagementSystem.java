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

    // --------------------------------------------------------
    // Delete patient
    // --------------------------------------------------------
    public boolean deletePatient(String patientId) {

        if (searchPatient(patientId) == null) {
            System.out.println("Patient not found.");
            return false;
        }

        root = deleteRec(root, patientId);

        System.out.println("Patient with ID " + patientId
                + " deleted successfully.");

        return true;
    }

    private BSTNode deleteRec(BSTNode root, String patientId) {

        if (root == null) {
            return null;
        }

        if (patientId.compareTo(root.patient.getPatientId()) < 0) {

            root.left = deleteRec(root.left, patientId);

        } else if (patientId.compareTo(root.patient.getPatientId()) > 0) {

            root.right = deleteRec(root.right, patientId);

        } else {

            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Only right child
            if (root.left == null) {
                return root.right;
            }

            // Case 3: Only left child
            if (root.right == null) {
                return root.left;
            }

            // Case 4: Two children
            Patient successor = minValue(root.right);

            root.patient = successor;

            root.right = deleteRec(
                    root.right,
                    successor.getPatientId()
            );
        }

        return root;
    }

     // Find minimum patient in right subtree
    private Patient minValue(BSTNode root) {

        Patient minimum = root.patient;

        while (root.left != null) {
            root = root.left;
            minimum = root.patient;
        }

        return minimum;
    }

    // --------------------------------------------------------
    // In-order traversal
    // --------------------------------------------------------
    public void displayPatients() {

        System.out.println("\n=== Patient Records "
                + "(Ascending Patient ID) ===");

        if (root == null) {
            System.out.println("No patient records available.");
            return;
        }

        inorderRec(root);
    }

    private void inorderRec(BSTNode root) {

        if (root != null) {

            inorderRec(root.left);

            System.out.println(root.patient);

            inorderRec(root.right);
        }
    }
}


// ============================================================
// EMERGENCY REQUEST CLASS
// ============================================================
class EmergencyRequest {

    private String patientId;
    private String emergencyType;
    private String requestTime;

    public EmergencyRequest(String patientId, String emergencyType) {

        this.patientId = patientId;
        this.emergencyType = emergencyType;
        this.requestTime = new Date().toString();
    }

    public String getPatientId() {
        return patientId;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public String getRequestTime() {
        return requestTime;
    }

    @Override
    public String toString() {

        return "Patient ID: " + patientId +
                ", Emergency: " + emergencyType +
                ", Time: " + requestTime;
    }
}


// ============================================================
// EMERGENCY PATIENT QUEUE
// FIFO - First In First Out
// ============================================================
class EmergencyPatientQueue {

    private Queue<EmergencyRequest> queue;

    public EmergencyPatientQueue() {
        queue = new LinkedList<>();
    }

    // --------------------------------------------------------
    // ENQUEUE
    // --------------------------------------------------------
    public void enqueue(EmergencyRequest request) {

        queue.offer(request);

        System.out.println(
                "Patient added to emergency queue: " + request
        );
    }

    // --------------------------------------------------------
    // DEQUEUE
    // --------------------------------------------------------
    public EmergencyRequest dequeue() {

        if (queue.isEmpty()) {

            System.out.println(
                    "No patients are waiting in the emergency queue."
            );

            return null;
        }

        EmergencyRequest request = queue.poll();

        System.out.println(
                "Processing emergency patient: " + request
        );

        return request;
    }
