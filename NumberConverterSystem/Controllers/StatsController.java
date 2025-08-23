package Controllers;

import Models.Conversion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import FinanceTrackerSystem.Controllers.UserController;
import FinanceTrackerSystem.Models.User;
import Helpers.CommonHelper;

public class StatsController {

    public static int PAIRS_TO_BE_DISPLAYED = 5;

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

    public static int getUndoCount(User user) {
        return user.undoCountInCurrentSession;
    }

    public static int totalSuccessfulConversions(ArrayList<Conversion> conversions) {
        int countOfSuccessfulConversions = 0;
        for (Conversion c : conversions) {
            if (c.result.isEmpty()) {
                countOfSuccessfulConversions += 1;
            }
        }
        return countOfSuccessfulConversions;
    }

    public static int totalFailedConversions(ArrayList<Conversion> conversions) {
        int countOfFailedConversions = 0;
        for (Conversion c : conversions) {
            if (c.errorMessage.isEmpty()) {
                countOfFailedConversions += 1;
            }
        }
        return countOfFailedConversions;
    }

    public static HashMap<String, Integer> getFrequentlyUsedBasePairs(ArrayList<Conversion> conversions,
            int pairsToBeDisplayed) {
        HashMap<String, Integer> basePairFrequency = new HashMap<>();
        for (Conversion c : conversions) {
            String key = String.format("%d -> %d", c.sourceBase, c.targetBase);
            basePairFrequency.put(key, basePairFrequency.getOrDefault(key, 0) + 1);
        }
        return CommonHelper.sortByValue(basePairFrequency, pairsToBeDisplayed);
    }

    public static String getMostUsedBase(ArrayList<Conversion> conversions, Boolean flag) {
        HashMap<String, Integer> sourceBaseFrequency = new HashMap<>();
        for (Conversion c : conversions) {
            String key = Integer.toString(flag == true ? c.sourceBase : c.targetBase);
            sourceBaseFrequency.put(key, sourceBaseFrequency.getOrDefault(key, 0) + 1);
        }
        return CommonHelper.sortByValue(sourceBaseFrequency, 1).entrySet().toString();
    }

    public static void printHistoryStats(int userId) {
        User user = UserController.getUserById(userId);
        StatsController.printHistoryStatsByDate(user.conversionHistory);
    }

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
