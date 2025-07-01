package Controllers;

import java.util.Random;
import java.util.Scanner;

import Models.QuizQuestion;

public class QuizController {
    public static int NUMBER_OF_QUESTIONS = 5;
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

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
