package Controllers;

import Models.Conversion;
import Models.User;

public class ConverterController {

    public static boolean isIntegerOnly(String value) {
        return !value.contains(".");
    }

    public static String convertIntegerToTargetBase(String inputValue, int sourceBase, int targetBase) {
        int base10Value = 0;
        String targetBaseValue;
        String errorMessage = new String();

        try {
            base10Value = Integer.parseInt(inputValue, sourceBase);
        } catch (NumberFormatException nfe) {
            errorMessage = String.format("Error - Please Give a Valid Value for the given Base\n%s", nfe.getMessage());
        }

        targetBaseValue = Integer.toString(base10Value, targetBase);
        return errorMessage.isEmpty() ? targetBaseValue : errorMessage;
    }

    private static double convertFractionToBase10(String fractionPart, int sourceBase) {
        double base10Fraction = 0.0;
        for (int i = 0; i < fractionPart.length(); i++) {
            char c = fractionPart.charAt(i);
            int digit = Character.digit(c, sourceBase);
            base10Fraction += digit / Math.pow(sourceBase, i + 1);
        }
        return base10Fraction;
    }

    private static String convertBase10FractionToTarget(double fraction, int targetBase, int precision) {
        StringBuilder convertedFraction = new StringBuilder();

        while (fraction != 0.0 && precision > 0) {
            fraction *= targetBase;
            int digit = (int) fraction;
            convertedFraction.append(Character.forDigit(digit, targetBase));
            fraction -= digit;
            precision--;
        }
        return convertedFraction.toString();
    }

    public static String convertDecimalToTargetBase(String inputValue, int sourceBase, int targetBase) {
        String[] parts = inputValue.split("\\.");
        String integerPart = convertIntegerToTargetBase(parts[0], sourceBase, targetBase);
        double base10Fraction = convertFractionToBase10(parts[1], sourceBase);
        String fractionalPart = convertBase10FractionToTarget(base10Fraction, targetBase, 5);

        return String.format("%s.%s", integerPart, fractionalPart);
    }

    public static String convertNumber(int userId, String inputValue, int sourceBase, int targetBase) {
        Conversion c = new Conversion(inputValue, sourceBase, targetBase);
        User user = UserController.getUserById(userId);
        if (user == null) {
            System.out.println("User not found !!!");
            return "";
        }
        user.currentSessionConversions.add(c);
        if (isIntegerOnly(inputValue)) {
            String result = convertIntegerToTargetBase(inputValue, sourceBase, targetBase);
            if (result.contains("error")) {
                c.errorMessage = result;
            } else {
                c.result = result;
            }
            return result;
        } else {
            String result = convertDecimalToTargetBase(inputValue, sourceBase, targetBase);
            if (result.contains("error")) {
                c.errorMessage = result;
            } else {
                c.result = result;
            }
            return result;
        }
    }

    public static boolean undoLastConversion(int userId) {
        User currentUser = UserController.getUserById(userId);
        if (currentUser.currentSessionConversions.size() >= 1) {
            currentUser.currentSessionConversions.remove(currentUser.currentSessionConversions.size() - 1);
            currentUser.undoCountInCurrentSession += 1;
            return true;
        } else {
            return false;
        }
    }
}
