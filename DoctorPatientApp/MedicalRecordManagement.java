
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

class MedicalRecord implements Serializable {
    private String patname, docname, diagnosis, treatmentPlan, medicationPrescribed;

    public MedicalRecord(String docname, String patname, String diagnosis, String treatmentPlan,
            String medicationPrescribed) {
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.medicationPrescribed = medicationPrescribed;
        this.patname = patname;
        this.docname = docname;
    }

    public String getDocname() {
        return docname;
    }

    public String getPatname() {
        return patname;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatmentPlan = treatment;
    }

    public void setMedication(String medicationPre) {
        this.medicationPrescribed = medicationPre;
    }

    public String toString() {
        return " patient Name :" + patname + " diagnosis : " + diagnosis
                + " treatment plan : " + treatmentPlan + " medication prescribed : " + medicationPrescribed;
    }
}

public class MedicalRecordManagement {
    private static String MEDICAL_FILE = "E:/DoctorPatientApp/medicalRecord.txt";
    private static HashMap<String, List<MedicalRecord>> medicalRecords = new HashMap<>();

    static {
        loadMedicalRecord();
    }

    @SuppressWarnings("unchecked")
    public static void loadMedicalRecord() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEDICAL_FILE))) {
            medicalRecords = (HashMap<String, List<MedicalRecord>>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Medical record file not found. Creating a new one.");
            medicalRecords = new HashMap<>();
        }
    }

    public static void saveMedicalRecord() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEDICAL_FILE))) {
            oos.writeObject(medicalRecords);
        } catch (IOException e) {
            System.out.println("Error saving medical records: " + e.getMessage());
        }
    }

    public static void createRecord(String docname, MedicalRecord record) {
        List<MedicalRecord> records = medicalRecords.getOrDefault(docname, new ArrayList<>());
        records.add(record);
        medicalRecords.put(docname, records);
        saveMedicalRecord();
    }

    public static void displayMedicalRecord(String docname) {
        List<MedicalRecord> records = medicalRecords.get(docname);
        if (records != null) {
            System.out.println("Medical records for " + docname + ":");
            for (MedicalRecord record : records) {
                System.out.println(record);
            }
        } else {
            System.out.println("No medical records found for " + docname);
        }
    }

}
