package NumberConverterSystem.Models;

/**
 * The {@code QuizQuestion} class represents a single question in a number base
 * conversion quiz
 * within the Number Converter System. It encapsulates all relevant data for a
 * quiz question,
 * including the value to be converted, source and target bases, the correct
 * answer, the user's answer,
 * and whether the user's answer was correct.
 * <p>
 * This class is designed to facilitate interactive quizzes, allowing for the
 * generation, display,
 * and verification of user responses to conversion questions. Each instance
 * stores both the question
 * parameters and the user's response, supporting detailed feedback and score
 * tracking.
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Stores the value to be converted, source base, and target base for the
 * quiz question.</li>
 * <li>Records the correct answer for the conversion, as well as the user's
 * submitted answer.</li>
 * <li>Tracks whether the user's answer matches the correct answer.</li>
 * <li>Provides a method to verify the user's answer and update correctness
 * status.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Create a new quiz question
 * QuizQuestion q = new QuizQuestion("101", "5", 2, 10);
 *
 * // User submits an answer
 * q.verifyAnswer("5");
 *
 * // Check if the answer was correct
 * if (q.isCorrect) {
 *     System.out.println("Correct!");
 * } else {
 *     System.out.println("Incorrect. The correct answer is " + q.correctAnswer);
 * }
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class QuizQuestion {
    /**
     * The value to be converted in the quiz question.
     * <p>
     * This is the raw input presented to the user, in the format appropriate for
     * the {@link #sourceBase}.
     * </p>
     * <b>Example:</b> "101" (binary), "A3" (hexadecimal)
     */
    public String value;

    /**
     * The base in which the {@link #value} is represented.
     * <p>
     * This integer specifies the source base for the conversion (e.g., 2 for
     * binary, 10 for decimal).
     * </p>
     */
    public int sourceBase;

    /**
     * The base to which the {@link #value} should be converted.
     * <p>
     * This integer specifies the target base for the conversion (e.g., 10 for
     * decimal, 16 for hexadecimal).
     * </p>
     */
    public int targetBase;

    /**
     * The correct answer for the conversion.
     * <p>
     * This is the expected result of converting {@link #value} from
     * {@link #sourceBase} to {@link #targetBase}.
     * </p>
     * <b>Example:</b> "5" (decimal), "101" (binary)
     */
    public String correctAnswer;

    /**
     * The answer submitted by the user.
     * <p>
     * This field is set when the user responds to the quiz question. It is compared
     * to {@link #correctAnswer}
     * to determine correctness.
     * </p>
     */
    public String userAnswer;

    /**
     * Indicates whether the user's answer matches the correct answer.
     * <p>
     * This boolean is updated when {@link #verifyAnswer(String)} is called.
     * </p>
     * <b>True:</b> User's answer is correct.<br>
     * <b>False:</b> User's answer is incorrect.
     */
    public boolean isCorrect;

    /**
     * Constructs a new {@code QuizQuestion} object with the specified value,
     * correct answer,
     * source base, and target base.
     * <p>
     * Initializes the question parameters and sets {@link #userAnswer} to
     * {@code null} and {@link #isCorrect} to {@code false}.
     * </p>
     *
     * @param value         The value to be converted in the quiz question.
     * @param correctAnswer The correct answer for the conversion.
     * @param sourceBase    The base in which the value is represented.
     * @param targetBase    The base to which the value should be converted.
     */
    public QuizQuestion(String value, String correctAnswer, int sourceBase, int targetBase) {
        this.value = value;
        this.correctAnswer = correctAnswer;
        this.sourceBase = sourceBase;
        this.targetBase = targetBase;
    }

    /**
     * Verifies the user's answer against the correct answer.
     * <p>
     * Sets the {@link #userAnswer} field to the provided value and updates
     * {@link #isCorrect}
     * to {@code true} if the user's answer matches {@link #correctAnswer}, or
     * {@code false} otherwise.
     * </p>
     *
     * @param userAnswer The answer submitted by the user.
     */
    public void verifyAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        this.isCorrect = this.userAnswer.equals(this.correctAnswer);
    }

}
