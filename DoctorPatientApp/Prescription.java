import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Map.Entry;

class PrescriptionRecord {
    private String mname, dosage, freq, duration, instructions;

    public PrescriptionRecord(String mname, String dosage, String freq, String duration, String instructions) {
        this.mname = mname;
        this.dosage = dosage;
        this.duration = duration;
        this.freq = freq;
        this.instructions = instructions;
    }

    public String toString() {
        return "Medicine Name : " + mname + " dosage : " + dosage + " frequency :" + freq + " duration : " + duration
                + " instructions : " + instructions;
    }
}

public class Prescription {

    static {
        try {
            loadPrescriptionDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadPrescriptionDetails() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRESCRIPTION_FILE))) {
            prescriptionRecords = (HashMap<String, List<PrescriptionRecord>>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void savePrescriptionDetails() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRESCRIPTION_FILE))) {
            oos.writeObject(prescriptionRecords);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String PRESCRIPTION_FILE = "E:/DoctorPatientApp/prescriptionDetails.txt";
    private static HashMap<String, List<PrescriptionRecord>> prescriptionRecords = new HashMap<>();

    public static void creation(String docname, String patname) {
        Scanner sc = new Scanner(System.in);
        List<PrescriptionRecord> pl = prescriptionRecords.getOrDefault(docname + "," + patname, new ArrayList<>());
        while (true) {
            System.out.println("Enter 1 for adding the medicine details");
            System.out.println("Enter 2 for completing the prescription");
            sc.nextLine();
            int x = sc.nextInt();
            sc.nextLine();
            switch (x) {
                case 1 -> {
                    System.out.println("Enter the medicine details :");
                    System.out.println("Enter the medcine name :");
                    String mname = sc.nextLine();
                    System.out.println("Enter the medcine dosage :");
                    String dosage = sc.nextLine();
                    System.out.println("Enter the medcine frequency :");
                    String freq = sc.nextLine();
                    System.out.println("Enter the medcine duration :");
                    String duration = sc.nextLine();
                    String instructions = sc.nextLine();
                    pl.add(new PrescriptionRecord(mname, dosage, freq, duration, instructions));
                }
                case 2 -> {
                    savePrescriptionDetails();
                    return;
                }
            }
        }
    }

    public static void display(String patname) {
        for (Entry<String, List<PrescriptionRecord>> ml : prescriptionRecords.entrySet()) {
            if (ml.getKey().split(",")[1].equals(patname)) {
                System.out.println(ml.getValue());
            }
        }
    }

}
