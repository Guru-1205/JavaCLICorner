package NumberConverterSystem.Controllers;

import NumberConverterSystem.Models.User;
import NumberConverterSystem.Models.Conversion;

/**
 * The {@code ConverterController} class provides static utility methods for
 * converting numbers
 * between different bases, handling both integer and fractional parts. It also
 * manages conversion
 * history for users, including undo functionality.
 * <p>
 * This controller is designed to be used in a session-based environment where
 * each user can perform
 * multiple conversions and maintain a history of their actions. It supports
 * conversion of both
 * integer-only and decimal numbers, with error handling for invalid input
 * values.
 * </p>
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Detects if a number is integer-only or contains a fractional part.</li>
 * <li>Converts integer numbers from a source base to a target base.</li>
 * <li>Converts fractional numbers from a source base to a target base with
 * configurable precision.</li>
 * <li>Handles conversion errors gracefully and provides error messages.</li>
 * <li>Tracks conversion history for each user and allows undoing the last
 * conversion.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * String result = ConverterController.convertNumber(userId, "101.11", 2, 10);
 * boolean undone = ConverterController.undoLastConversion(userId);
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class ConverterController {

    /**
     * Checks if the provided string value represents an integer-only number (i.e.,
     * does not contain a decimal point).
     *
     * @param value The string representation of the number to check.
     * @return {@code true} if the value does not contain a decimal point and is
     *         thus integer-only; {@code false} otherwise.
     */
    public static boolean isIntegerOnly(String value) {
        return !value.contains(".");
    }

    /**
     * Converts an integer value from a specified source base to a target base.
     * <p>
     * This method parses the input string as an integer in the source base, then
     * converts it to the target base.
     * If the input is invalid for the given source base, an error message is
     * returned.
     * </p>
     *
     * @param inputValue The string representation of the integer value to convert.
     * @param sourceBase The base in which the input value is represented (e.g., 2
     *                   for binary, 10 for decimal).
     * @param targetBase The base to which the value should be converted.
     * @return The converted value as a string in the target base, or an error
     *         message if conversion fails.
     */
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

    /**
     * Converts the fractional part of a number from a specified source base to base
     * 10.
     * <p>
     * For example, given the fraction part "101" in base 2, this method computes
     * its base 10 equivalent.
     * Each digit is divided by the source base raised to the power of its position
     * (starting from 1).
     * </p>
     *
     * @param fractionPart The string representation of the fractional part (digits
     *                     after the decimal point).
     * @param sourceBase   The base in which the fraction part is represented.
     * @return The base 10 equivalent of the fractional part as a double.
     */
    private static double convertFractionToBase10(String fractionPart, int sourceBase) {
        double base10Fraction = 0.0;
        for (int i = 0; i < fractionPart.length(); i++) {
            char c = fractionPart.charAt(i);
            int digit = Character.digit(c, sourceBase);
            base10Fraction += digit / Math.pow(sourceBase, i + 1);
        }
        return base10Fraction;
    }

    /**
     * Converts a base 10 fractional value to a target base with specified
     * precision.
     * <p>
     * This method repeatedly multiplies the fractional part by the target base,
     * extracts the integer part,
     * and appends its digit representation to the result. The process continues
     * until the fraction becomes zero
     * or the desired precision is reached.
     * </p>
     *
     * @param fraction   The base 10 fractional value to convert.
     * @param targetBase The base to which the fraction should be converted.
     * @param precision  The maximum number of digits to generate in the target
     *                   base.
     * @return The string representation of the converted fractional part in the
     *         target base.
     */
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

    /**
     * Converts a decimal number (with both integer and fractional parts) from a
     * source base to a target base.
     * <p>
     * The method splits the input value into integer and fractional parts, converts
     * each part separately,
     * and then combines them into the final result. The fractional part is
     * converted with a fixed precision of 5 digits.
     * </p>
     *
     * @param inputValue The string representation of the decimal number to convert
     *                   (e.g., "101.11").
     * @param sourceBase The base in which the input value is represented.
     * @param targetBase The base to which the value should be converted.
     * @return The converted value as a string in the target base, combining both
     *         integer and fractional parts.
     */
    public static String convertDecimalToTargetBase(String inputValue, int sourceBase, int targetBase) {
        String[] parts = inputValue.split("\\.");
        String integerPart = convertIntegerToTargetBase(parts[0], sourceBase, targetBase);
        double base10Fraction = convertFractionToBase10(parts[1], sourceBase);
        String fractionalPart = convertBase10FractionToTarget(base10Fraction, targetBase, 5);

        return String.format("%s.%s", integerPart, fractionalPart);
    }

    /**
     * Converts a number (integer or decimal) from a source base to a target base
     * for a specific user.
     * <p>
     * This method determines whether the input value is integer-only or contains a
     * fractional part,
     * performs the appropriate conversion, and records the conversion in the user's
     * session history.
     * If the user is not found, an error message is printed and an empty string is
     * returned.
     * </p>
     *
     * @param userId     The unique identifier of the user performing the
     *                   conversion.
     * @param inputValue The string representation of the number to convert.
     * @param sourceBase The base in which the input value is represented.
     * @param targetBase The base to which the value should be converted.
     * @return The converted value as a string in the target base, or an error
     *         message if conversion fails.
     */
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

    /**
     * Undoes the last conversion performed by the specified user in the current
     * session.
     * <p>
     * This method removes the most recent conversion from the user's session
     * history and increments
     * the user's undo count. If there are no conversions to undo, it returns
     * {@code false}.
     * </p>
     *
     * @param userId The unique identifier of the user requesting the undo
     *               operation.
     * @return {@code true} if the last conversion was successfully undone;
     *         {@code false} if there were no conversions to undo.
     */
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
