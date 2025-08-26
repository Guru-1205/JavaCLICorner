package NumberConverterSystem.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import NumberConverterSystem.Models.User;
import NumberConverterSystem.Models.Conversion;

/**
 * The {@code FileController} class provides static utility methods for
 * file-based operations
 * related to number conversion sessions and histories for users. It enables
 * batch processing
 * of conversions from input files, and exporting session and historical
 * conversion data to files.
 * <p>
 * This controller is designed to facilitate integration with external files for
 * both input and output,
 * supporting features such as batch conversion, session export, and historical
 * export by date.
 * </p>
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Batch processing of conversions from a CSV file, allowing multiple
 * conversions in one operation.</li>
 * <li>Exporting the current session's conversions for a user to a designated
 * output file.</li>
 * <li>Exporting the user's entire conversion history, grouped by date, to a
 * designated output file.</li>
 * <li>Graceful error handling for file I/O operations, with informative
 * messages.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Batch process conversions from a file for a user
 * FileController.batchProcessingThroughFile(userId, "input.csv");
 *
 * // Export current session conversions for a user
 * boolean success = FileController.exportCurrentSessionConversions(userId);
 *
 * // Export historical conversions grouped by date for a user
 * boolean historyExported = FileController.exportHistoryConversionsByDate(userId);
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class FileController {

    /**
     * Processes a batch of number conversions for a specific user by reading
     * conversion data from a file.
     * <p>
     * The input file should contain lines in CSV format, where each line represents
     * a conversion request:
     * 
     * <pre>
     * value,sourceBase,targetBase
     * </pre>
     * 
     * For each line, the method parses the value, source base, and target base,
     * then performs the conversion
     * using {@link ConverterController#convertNumber(int, String, int, int)}. The
     * result of each conversion
     * is printed to the standard output.
     * </p>
     * <p>
     * If the file cannot be read or contains invalid data, an error message is
     * printed.
     * </p>
     *
     * @param userId   The unique identifier of the user performing the batch
     *                 conversions.
     * @param filePath The path to the input file containing conversion requests in
     *                 CSV format.
     * @throws IllegalArgumentException if the file format is invalid or conversion
     *                                  fails.
     */
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

    /**
     * Exports the current session's conversions for a specific user to a designated
     * output file.
     * <p>
     * Each conversion performed in the current session is written to the file, one
     * after another,
     * using the {@code toString()} representation of the {@link Conversion} object.
     * The output file is located at:
     * 
     * <pre>
     * E:\CODE GALLATA\OVERALL NOTES\LONG CODING & DESIGN PATTERNS\SAMPLE CODE\NumberConverterSystem\Files\TestingFiles\TestExportCurrentSession.txt
     * </pre>
     * </p>
     * <p>
     * If the export operation fails due to file I/O errors, an error message is
     * printed and {@code false} is returned.
     * Otherwise, {@code true} is returned upon successful export.
     * </p>
     *
     * @param userId The unique identifier of the user whose session conversions are
     *               to be exported.
     * @return {@code true} if the export was successful; {@code false} if an error
     *         occurred during export.
     */
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

    /**
     * Exports the user's entire conversion history, grouped by date, to a
     * designated output file.
     * <p>
     * The output file contains entries for each date on which conversions were
     * performed. For each date,
     * the date is written as a header, followed by the {@code toString()}
     * representations of all conversions
     * performed on that date. Each date's conversions are separated by a blank
     * line.
     * The output file is located at:
     * 
     * <pre>
     * E:\CODE GALLATA\OVERALL NOTES\LONG CODING & DESIGN PATTERNS\SAMPLE CODE\NumberConverterSystem\Files\TestingFiles\TestExportHistoryConversions.txt
     * </pre>
     * </p>
     * <p>
     * If the export operation fails due to file I/O errors, an error message is
     * printed and {@code false} is returned.
     * Otherwise, {@code true} is returned upon successful export.
     * </p>
     *
     * @param userId The unique identifier of the user whose conversion history is
     *               to be exported.
     * @return {@code true} if the export was successful; {@code false} if an error
     *         occurred during export.
     */
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
