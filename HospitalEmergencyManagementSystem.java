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

    // --------------------------------------------------------
    // DISPLAY QUEUE
    // --------------------------------------------------------
    public void displayQueue() {

        System.out.println(
                "\n=== Emergency Patient Queue (FIFO) ==="
        );

        if (queue.isEmpty()) {

            System.out.println("No patients are currently waiting.");

            return;
        }

        int position = 1;

        for (EmergencyRequest request : queue) {

            System.out.println(
                    position + ". " + request
            );

            position++;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


// ============================================================
// TREATMENT RECORD
// Used by Treatment Stack
// ============================================================
class TreatmentRecord {

    private String patientId;
    private String patientName;
    private String doctorName;
    private String treatment;
    private String treatmentDate;

    public TreatmentRecord(String patientId,
                           String patientName,
                           String doctorName,
                           String treatment) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatment = treatment;
        this.treatmentDate = new Date().toString();
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getTreatmentDate() {
        return treatmentDate;
    }

    @Override
    public String toString() {

        return "Patient ID: " + patientId +
                ", Patient: " + patientName +
                ", Doctor: " + doctorName +
                ", Treatment: " + treatment +
                ", Date: " + treatmentDate;
    }
}


// ============================================================
// TREATMENT STACK
// LIFO - Last In First Out
// ============================================================
class TreatmentStack {

    private Stack<TreatmentRecord> stack;

    public TreatmentStack() {
        stack = new Stack<>();
    }

    // --------------------------------------------------------
    // PUSH
    // --------------------------------------------------------
    public void push(TreatmentRecord record) {

        stack.push(record);

        System.out.println(
                "Treatment completed and added to treatment stack."
        );
    }

    // --------------------------------------------------------
    // POP
    // --------------------------------------------------------
    public TreatmentRecord pop() {

        if (stack.isEmpty()) {

            System.out.println(
                    "No treatment records in the stack."
            );

            return null;
        }

        TreatmentRecord record = stack.pop();

        System.out.println(
                "Most recent treatment removed from stack:"
        );

        System.out.println(record);

        return record;
    }

    // --------------------------------------------------------
    // DISPLAY STACK
    // --------------------------------------------------------
    public void displayStack() {

        System.out.println(
                "\n=== Treatment History Stack (LIFO) ==="
        );

        if (stack.isEmpty()) {

            System.out.println(
                    "No completed treatment records."
            );

            return;
        }

        int position = 1;

        // Display from top to bottom
        for (int i = stack.size() - 1; i >= 0; i--) {

            System.out.println(
                    position + ". " + stack.get(i)
            );

            position++;
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}


// ============================================================
// VISIT NODE
// ============================================================
class VisitNode {

    String visitId;
    String visitDate;
    String doctorName;
    String diagnosis;
    String treatment;

    VisitNode next;

    public VisitNode(String visitId,
                     String visitDate,
                     String doctorName,
                     String diagnosis,
                     String treatment) {

        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    @Override
    public String toString() {

        return "Visit ID: " + visitId +
                "\nVisit Date: " + visitDate +
                "\nDoctor: " + doctorName +
                "\nDiagnosis: " + diagnosis +
                "\nTreatment: " + treatment;
    }
}


// ============================================================
// SINGLY LINKED LIST FOR PATIENT VISIT HISTORY
// ============================================================
class PatientVisitHistory {

    private VisitNode head;
    private String patientId;

    public PatientVisitHistory(String patientId) {

        this.patientId = patientId;
        this.head = null;
    }

    // --------------------------------------------------------
    // ADD VISIT
    // --------------------------------------------------------
    public void addVisit(String visitId,
                          String visitDate,
                          String doctorName,
                          String diagnosis,
                          String treatment) {

        VisitNode newNode = new VisitNode(
                visitId,
                visitDate,
                doctorName,
                diagnosis,
                treatment
        );

        // Add new visit at beginning
        newNode.next = head;
        head = newNode;

        System.out.println(
                "Visit added successfully for patient "
                        + patientId
        );
    }

    // --------------------------------------------------------
    // REMOVE VISIT
    // --------------------------------------------------------
    public boolean removeVisit(String visitId) {

        if (head == null) {

            System.out.println("No visits available.");

            return false;
        }

        // If first node needs to be removed
        if (head.visitId.equals(visitId)) {

            head = head.next;

            System.out.println(
                    "Visit " + visitId + " removed successfully."
            );

            return true;
        }

        VisitNode current = head;

        while (current.next != null) {

            if (current.next.visitId.equals(visitId)) {

                current.next = current.next.next;

                System.out.println(
                        "Visit " + visitId
                                + " removed successfully."
                );

                return true;
            }

            current = current.next;
        }

        System.out.println(
                "Visit ID " + visitId + " not found."
        );

        return false;
    }

    // --------------------------------------------------------
    // SEARCH VISIT
    // --------------------------------------------------------
    public VisitNode searchVisit(String visitId) {

        VisitNode current = head;

        while (current != null) {

            if (current.visitId.equals(visitId)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    // --------------------------------------------------------
    // DISPLAY VISIT HISTORY
    // --------------------------------------------------------
    public void displayHistory() {

        System.out.println(
                "\n=== Patient " + patientId
                        + " Visit History ==="
        );

        if (head == null) {

            System.out.println(
                    "No previous hospital visits."
            );

            return;
        }

        VisitNode current = head;

        int count = 1;

        while (current != null) {

            System.out.println(
                    "\nVisit " + count
            );

            System.out.println(current);

            current = current.next;

            count++;
        }
    }
}


// ============================================================
// MAIN HOSPITAL EMERGENCY MANAGEMENT SYSTEM
// ============================================================
public class HospitalEmergencyManagementSystem {

    private PatientRecordBST patientRecords;
    private EmergencyPatientQueue emergencyQueue;
    private TreatmentStack treatmentStack;

    private Map<String, PatientVisitHistory> visitHistories;

    private Scanner scanner;

    // --------------------------------------------------------
    // CONSTRUCTOR
    // --------------------------------------------------------
    public HospitalEmergencyManagementSystem() {

        patientRecords = new PatientRecordBST();

        emergencyQueue = new EmergencyPatientQueue();

        treatmentStack = new TreatmentStack();

        visitHistories = new HashMap<>();

        scanner = new Scanner(System.in);

        initializeSampleData();
    }

    // --------------------------------------------------------
    // SAMPLE PATIENT DATA
    // --------------------------------------------------------
    private void initializeSampleData() {

        patientRecords.insertPatient(
                new Patient(
                        "P001",
                        "John Silva",
                        35,
                        "0712345678",
                        "Chest Pain"
                )
        );

        patientRecords.insertPatient(
                new Patient(
                        "P003",
                        "Kamal Perera",
                        42,
                        "0771234567",
                        "High Fever"
                )
        );

        patientRecords.insertPatient(
                new Patient(
                        "P002",
                        "Nimal Fernando",
                        28,
                        "0759876543",
                        "Fracture"
                )
        );
    }

    // --------------------------------------------------------
    // DISPLAY MENU
    // --------------------------------------------------------
    public void displayMenu() {

        System.out.println(
                "\n" + "=".repeat(60)
        );

        System.out.println(
                "       MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM"
        );

        System.out.println(
                "=".repeat(60)
        );

        System.out.println(
                "1.  Register New Patient"
        );

        System.out.println(
                "2.  Delete Patient"
        );

        System.out.println(
                "3.  Display All Patients (BST In-order)"
        );

        System.out.println(
                "4.  Search Patient by ID"
        );

        System.out.println(
                "5.  Add Patient to Emergency Queue"
        );

        System.out.println(
                "6.  Process Next Emergency Patient (Dequeue)"
        );

        System.out.println(
                "7.  Display Emergency Waiting Queue"
        );

        System.out.println(
                "8.  Complete Treatment (Push to Stack)"
        );

        System.out.println(
                "9.  Process Most Recent Treatment (Pop)"
        );

        System.out.println(
                "10. Display Treatment History (Stack)"
        );

        System.out.println(
                "11. Add Patient Visit"
        );

        System.out.println(
                "12. Remove Patient Visit"
        );

        System.out.println(
                "13. Search Patient Visit"
        );

        System.out.println(
                "14. Display Patient Visit History"
        );

        System.out.println(
                "0.  Exit"
        );

        System.out.println(
                "=".repeat(60)
        );

        System.out.print(
                "Enter your choice: "
        );
    }

    // --------------------------------------------------------
    // MAIN PROGRAM LOOP
    // --------------------------------------------------------
    public void run() {

        System.out.println(
                "\nWelcome to Mini Hospital Emergency Management System!"
        );

        while (true) {

            displayMenu();

            try {

                int choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {

                    case 1:
                        registerPatient();
                        break;

                    case 2:
                        deletePatient();
                        break;

                    case 3:
                        patientRecords.displayPatients();
                        break;

                    case 4:
                        searchPatient();
                        break;

                    case 5:
                        addEmergencyRequest();
                        break;

                    case 6:
                        processEmergencyPatient();
                        break;

                    case 7:
                        emergencyQueue.displayQueue();
                        break;

                    case 8:
                        completeTreatment();
                        break;

                    case 9:
                        processTreatment();
                        break;

                    case 10:
                        treatmentStack.displayStack();
                        break;

                    case 11:
                        addPatientVisit();
                        break;

                    case 12:
                        removePatientVisit();
                        break;

                    case 13:
                        searchPatientVisit();
                        break;

                    case 14:
                        displayPatientVisitHistory();
                        break;

                    case 0:

                        System.out.println(
                                "\nThank you for using the Hospital "
                                        + "Emergency Management System!"
                        );

                        return;

                    default:

                        System.out.println(
                                "Invalid choice! Please try again."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter a valid number."
                );

                scanner.nextLine();
            }
        }
    }