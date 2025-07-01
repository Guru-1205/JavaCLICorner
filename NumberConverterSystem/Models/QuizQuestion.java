package Models;

public class QuizQuestion {
    public String value;
    public int sourceBase, targetBase;
    public String correctAnswer;
    public String userAnswer;
    public boolean isCorrect;

    public QuizQuestion(String value, String correctAnswer, int sourceBase, int targetBase) {
        this.value = value;
        this.correctAnswer = correctAnswer;
        this.sourceBase = sourceBase;
        this.targetBase = targetBase;
    }

    public void verifyAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        this.isCorrect = this.userAnswer.equals(this.correctAnswer);
    }

}
