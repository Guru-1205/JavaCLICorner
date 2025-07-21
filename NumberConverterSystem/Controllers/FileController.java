package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import Models.Conversion;
import Models.User;

public class FileController {

    public static void batchProcessingThroughFile(int userId, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));) {
            while (br.ready()) {
                String[] valuesForConversion = br.readLine().split(",");
                String value = valuesForConversion[0];
                int sourceBase = Integer.parseInt(valuesForConversion[1]);
                int targetBase = Integer.parseInt(valuesForConversion[2]);
                System.out.printf("\n%s", ConverterController.convertNumber(userId, value, sourceBase, targetBase));
            }
        } catch (Exception e) {
            System.out.println("There was an problem while reading the file" + e.getMessage());
        }
    }

    public static boolean exportCurrentSessionConversions(int userId) {
        User user = UserController.getUserById(userId);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                "E:\\CODE GALLATA\\OVERALL NOTES\\LONG CODING & DESIGN PATTERNS\\SAMPLE CODE\\NumberConverterSystem\\Files\\TestingFiles\\TestExportCurrentSession.txt"));) {
            for (Conversion c : user.currentSessionConversions) {
                bw.append(c.toString());
            }
        } catch (Exception e) {
            System.out.println("There was an problem while exporting to the file " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean exportHistoryConversionsByDate(int userId) {
        User user = UserController.getUserById(userId);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                "E:\\CODE GALLATA\\OVERALL NOTES\\LONG CODING & DESIGN PATTERNS\\SAMPLE CODE\\NumberConverterSystem\\Files\\TestingFiles\\TestExportHistoryConversions.txt"))) {
            for (Map.Entry<LocalDate, ArrayList<Conversion>> entry : user.conversionHistory.entrySet()) {
                bw.append(String.format("%s\n", (entry.getKey().toString())));
                for (Conversion c : entry.getValue()) {
                    bw.append(c.toString());
                }
                bw.append("\n");
            }
        } catch (Exception e) {
            System.out.println("There was an problem while exporting to the file " + e.getMessage());
            return false;
        }
        return true;
    }
}
