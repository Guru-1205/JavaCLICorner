package NumberConverterSystem.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import NumberConverterSystem.Models.User;
import NumberConverterSystem.Models.Conversion;
import NumberConverterSystem.Helpers.CommonHelper;

/**
 * The {@code StatsController} class provides static utility methods for
 * calculating,
 * aggregating, and displaying statistics related to number conversion sessions
 * and histories
 * for users. It supports both current session statistics and historical
 * statistics grouped by date.
 * <p>
 * This controller is designed to help users and administrators analyze
 * conversion activity,
 * including success/failure rates, most frequently used base pairs, and undo
 * operations.
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Display statistics for the current session, including total conversions,
 * successful/failed conversions, undo count, and most used bases.</li>
 * <li>Display historical statistics grouped by date, including conversion
 * counts and base usage.</li>
 * <li>Calculate the frequency of base pairs used in conversions.</li>
 * <li>Determine the most used source and target bases.</li>
 * <li>Count undo operations performed in the current session.</li>
 * <li>Graceful handling of empty or missing data.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Print current session statistics for a user
 * StatsController.printCurrentSessionStats(userId);
 *
 * // Print historical statistics for a user
 * StatsController.printHistoryStats(userId);
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class StatsController {

    /**
     * The number of most frequently used base pairs to display in statistics.
     * <p>
     * This value can be adjusted to show more or fewer base pairs in the output.
     * Default is 5.
     * </p>
     */
    public static int PAIRS_TO_BE_DISPLAYED = 5;

    /**
     * Prints detailed statistics for the current session of a specific user.
     * <p>
     * Statistics include:
     * <ul>
     * <li>Total conversions performed in the session.</li>
     * <li>Number of successful conversions.</li>
     * <li>Number of failed conversions.</li>
     * <li>Number of undo operations performed.</li>
     * <li>Most frequently used source base.</li>
     * <li>Most frequently used target base.</li>
     * <li>Most frequently used base pairs (source -> target) and their
     * frequencies.</li>
     * </ul>
     * </p>
     * <p>
     * Output is printed to the standard output stream.
     * </p>
     *
     * @param userId The unique identifier of the user whose session statistics are
     *               to be displayed.
     */
    public static void printCurrentSessionStats(int userId) {
        User user = UserController.getUserById(userId);
        int undoCountInCurrentSession = StatsController.getUndoCount(user);
        int countOfSuccessfulConversions = StatsController.totalSuccessfulConversions(user.currentSessionConversions);
        int countOfFailedConversions = StatsController.totalFailedConversions(user.currentSessionConversions);
        int totalConversions = countOfFailedConversions + countOfSuccessfulConversions;
        HashMap<String, Integer> mostUsedBasePairs = getFrequentlyUsedBasePairs(user.currentSessionConversions,
                PAIRS_TO_BE_DISPLAYED);
        for (Map.Entry<String, Integer> pair : mostUsedBasePairs.entrySet()) {
            System.out.printf("\n %s (Frequency - %d)", pair.getKey(), pair.getValue());
        }
        String mostUsedSourceBase = StatsController.getMostUsedBase(user.currentSessionConversions, true);
        String mostUsedTargetBase = StatsController.getMostUsedBase(user.currentSessionConversions, false);
        System.out.printf(
                "\nTotal Conversions - %d\n Total Successful Conversions - %d\n Total Failed Conversions - %d\n Total Undo Operations - %d\n Most Used Source Base - %s\n Most Used Target Base - %s\n",
                totalConversions, countOfSuccessfulConversions, countOfFailedConversions, undoCountInCurrentSession,
                mostUsedSourceBase,
                mostUsedTargetBase);
    }

    /**
     * Retrieves the number of undo operations performed by the user in the current
     * session.
     *
     * @param user The {@link User} object whose undo count is to be retrieved.
     * @return The number of undo operations performed in the current session.
     */
    public static int getUndoCount(User user) {
        return user.undoCountInCurrentSession;
    }

    /**
     * Calculates the total number of successful conversions in a given list of
     * conversions.
     * <p>
     * A conversion is considered successful if its {@code result} field is not
     * empty.
     * </p>
     *
     * @param conversions The list of {@link Conversion} objects to analyze.
     * @return The count of successful conversions.
     */
    public static int totalSuccessfulConversions(ArrayList<Conversion> conversions) {
        int countOfSuccessfulConversions = 0;
        for (Conversion c : conversions) {
            if (c.result.isEmpty()) {
                countOfSuccessfulConversions += 1;
            }
        }
        return countOfSuccessfulConversions;
    }

    /**
     * Calculates the total number of failed conversions in a given list of
     * conversions.
     * <p>
     * A conversion is considered failed if its {@code errorMessage} field is not
     * empty.
     * </p>
     *
     * @param conversions The list of {@link Conversion} objects to analyze.
     * @return The count of failed conversions.
     */
    public static int totalFailedConversions(ArrayList<Conversion> conversions) {
        int countOfFailedConversions = 0;
        for (Conversion c : conversions) {
            if (c.errorMessage.isEmpty()) {
                countOfFailedConversions += 1;
            }
        }
        return countOfFailedConversions;
    }

    /**
     * Determines the most frequently used base pairs (source base to target base)
     * in a list of conversions.
     * <p>
     * The method counts the frequency of each base pair and returns the top N pairs
     * as specified.
     * </p>
     *
     * @param conversions        The list of {@link Conversion} objects to analyze.
     * @param pairsToBeDisplayed The number of top base pairs to return.
     * @return A {@link HashMap} mapping base pair strings (e.g., "2 -> 10") to
     *         their frequency counts.
     */
    public static HashMap<String, Integer> getFrequentlyUsedBasePairs(ArrayList<Conversion> conversions,
            int pairsToBeDisplayed) {
        HashMap<String, Integer> basePairFrequency = new HashMap<>();
        for (Conversion c : conversions) {
            String key = String.format("%d -> %d", c.sourceBase, c.targetBase);
            basePairFrequency.put(key, basePairFrequency.getOrDefault(key, 0) + 1);
        }
        return CommonHelper.sortByValue(basePairFrequency, pairsToBeDisplayed);
    }

    /**
     * Determines the most used source or target base in a list of conversions.
     * <p>
     * The method counts the frequency of each base (source or target, depending on
     * the flag)
     * and returns the most frequently used base as a string.
     * </p>
     *
     * @param conversions The list of {@link Conversion} objects to analyze.
     * @param flag        If {@code true}, analyzes source bases; if {@code false},
     *                    analyzes target bases.
     * @return The most frequently used base as a string.
     */
    public static String getMostUsedBase(ArrayList<Conversion> conversions, Boolean flag) {
        HashMap<String, Integer> sourceBaseFrequency = new HashMap<>();
        for (Conversion c : conversions) {
            String key = Integer.toString(flag == true ? c.sourceBase : c.targetBase);
            sourceBaseFrequency.put(key, sourceBaseFrequency.getOrDefault(key, 0) + 1);
        }
        return CommonHelper.sortByValue(sourceBaseFrequency, 1).entrySet().toString();
    }

    /**
     * Prints detailed statistics for the user's entire conversion history, grouped
     * by date.
     * <p>
     * For each date, statistics include:
     * <ul>
     * <li>List of conversions performed on that date.</li>
     * <li>Number of successful conversions.</li>
     * <li>Number of failed conversions.</li>
     * <li>Most used source base.</li>
     * <li>Most used target base.</li>
     * <li>Most frequently used base pairs and their frequencies.</li>
     * </ul>
     * </p>
     * <p>
     * Output is printed to the standard output stream.
     * </p>
     *
     * @param userId The unique identifier of the user whose historical statistics
     *               are to be displayed.
     */
    public static void printHistoryStats(int userId) {
        User user = UserController.getUserById(userId);
        StatsController.printHistoryStatsByDate(user.conversionHistory);
    }

    /**
     * Prints statistics for conversion history grouped by date.
     * <p>
     * For each date in the history map, prints:
     * <ul>
     * <li>All conversions performed on that date.</li>
     * <li>Number of successful conversions.</li>
     * <li>Number of failed conversions.</li>
     * <li>Most used source base.</li>
     * <li>Most used target base.</li>
     * <li>Most frequently used base pairs and their frequencies.</li>
     * </ul>
     * </p>
     * <p>
     * Output is printed to the standard output stream.
     * </p>
     *
     * @param history A {@link HashMap} mapping {@link LocalDate} to lists of
     *                {@link Conversion} objects.
     */
    public static void printHistoryStatsByDate(HashMap<LocalDate, ArrayList<Conversion>> history) {
        for (Map.Entry<LocalDate, ArrayList<Conversion>> conversionsListByDate : history.entrySet()) {
            System.out.println(conversionsListByDate.getValue());
            System.out.printf("\nTotal Successful Conversions - %d",
                    StatsController.totalSuccessfulConversions(conversionsListByDate.getValue()));
            System.out.printf("\nTotal Failed Conversions - %d",
                    StatsController.totalFailedConversions(conversionsListByDate.getValue()));
            System.out.printf("\nMost Used Source Base - %s",
                    StatsController.getMostUsedBase(conversionsListByDate.getValue(), true));
            System.out.printf("\nMost Used Target Base - %s",
                    StatsController.getMostUsedBase(conversionsListByDate.getValue(), false));
            HashMap<String, Integer> mostUsedBasePairs = getFrequentlyUsedBasePairs(conversionsListByDate.getValue(),
                    PAIRS_TO_BE_DISPLAYED);
            for (Map.Entry<String, Integer> pair : mostUsedBasePairs.entrySet()) {
                System.out.printf("\n %s (Frequency - %d)", pair.getKey(), pair.getValue());
            }
        }
    }
}
