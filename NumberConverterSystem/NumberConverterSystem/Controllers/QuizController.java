package NumberConverterSystem.Controllers;

import java.util.Random;
import java.util.Scanner;

import NumberConverterSystem.Models.QuizQuestion;

/**
 * The {@code QuizController} class provides static methods for managing and
 * conducting
 * interactive quizzes related to number base conversions. It generates random
 * questions,
 * collects user input, verifies answers, and tracks the user's score.
 * <p>
 * This controller is intended for use in command-line environments, allowing
 * users to
 * test and improve their understanding of number base conversions. It supports
 * automatic
 * question generation with random values and bases, and provides immediate
 * feedback on
 * user responses.
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Configurable number of quiz questions per session.</li>
 * <li>Random generation of quiz questions, including value, source base, and
 * target base.</li>
 * <li>Interactive user input collection via standard input.</li>
 * <li>Answer verification and feedback for each question.</li>
 * <li>Score tracking and summary at the end of the quiz.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Start a quiz session
 * QuizController.startQuiz();
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class QuizController {

    /**
     * The number of questions to be asked in each quiz session.
     * <p>
     * This value can be modified to change the length of the quiz.
     * Default is 5.
     * </p>
     */
    public static int NUMBER_OF_QUESTIONS = 5;

    /**
     * A shared {@link Random} instance used for generating random values and bases
     * for quiz questions.
     */
    public static Random random = new Random();

    /**
     * A shared {@link Scanner} instance used for reading user input from the
     * standard input stream.
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Starts an interactive quiz session for the user.
     * <p>
     * The quiz consists of {@link #NUMBER_OF_QUESTIONS} randomly generated
     * questions.
     * For each question, the user is presented with a value, source base, and
     * target base,
     * and is prompted to enter the converted value. The user's answer is verified,
     * and
     * feedback is provided immediately. The user's score is tracked and displayed
     * at the end.
     * </p>
     * <p>
     * <b>Quiz Flow:</b>
     * <ol>
     * <li>For each question:
     * <ul>
     * <li>Generate a random {@link QuizQuestion}.</li>
     * <li>Display the value, source base, and target base to the user.</li>
     * <li>Prompt the user for their answer.</li>
     * <li>Verify the answer using {@link QuizQuestion#verifyAnswer(String)}.</li>
     * <li>Provide feedback: indicate if the answer was correct or display the
     * correct answer.</li>
     * </ul>
     * </li>
     * <li>After all questions, display the user's total score.</li>
     * </ol>
     * </p>
     *
     * <p>
     * <b>Example Output:</b>
     * 
     * <pre>
     *  Value : 101 - Source Base : 2 - Target Base : 10
     *  [User enters answer]
     *  The Answer was correct
     *  ...
     *  Your Score out of 5 was 4
     * </pre>
     * </p>
     */
    public static void startQuiz() {
        int score = 0;
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            QuizQuestion q = QuizController.generateQuestion();
            System.out.printf("\n Value : %s - Source Base : %d - Target Base : %d", q.value, q.sourceBase,
                    q.targetBase);
            String userAnswer = scanner.nextLine();
            q.verifyAnswer(userAnswer);
            if (q.isCorrect) {
                System.out.print("\nThe Answer was correct");
                score++;
            } else {
                System.out.printf("\n Invalid Answer, The actual answer was %s", q.correctAnswer);
            }
        }
        System.out.printf("\nYour Score out of 5 was %d", score);
    }

    /**
     * Generates a random {@link QuizQuestion} for the quiz.
     * <p>
     * The method randomly selects:
     * <ul>
     * <li>A source base between 2 and 36 (inclusive).</li>
     * <li>A target base between 2 and 36 (inclusive).</li>
     * <li>An integer value between 10 and 999 (inclusive), which is then converted
     * to the source base.</li>
     * </ul>
     * The correct answer is computed by converting the value from the source base
     * to the target base
     * using
     * {@link ConverterController#convertIntegerToTargetBase(String, int, int)}.
     * The resulting {@link QuizQuestion} contains the value (in source base), the
     * correct answer,
     * the source base, and the target base.
     * </p>
     *
     * <p>
     * <b>Example:</b>
     * 
     * <pre>
     *   sourceBase = 16
     *   targetBase = 2
     *   valueBase10 = 255
     *   value = "ff" (in base 16)
     *   answer = "11111111" (in base 2)
     * </pre>
     * </p>
     *
     * @return A randomly generated {@link QuizQuestion} containing the value,
     *         correct answer,
     *         source base, and target base.
     */
    public static QuizQuestion generateQuestion() {
        // base 2 to 36
        int sourceBase = random.nextInt(2, 37);
        int targetBase = random.nextInt(2, 37);
        int valueBase10 = random.nextInt(10, 1000);
        String value = Integer.toString(valueBase10, sourceBase);
        String answer = ConverterController.convertIntegerToTargetBase(value, sourceBase, targetBase);
        return new QuizQuestion(value, answer, sourceBase, targetBase);
    }
}
