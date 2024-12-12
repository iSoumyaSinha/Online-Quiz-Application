package Main.models;

import java.sql.Timestamp;

public class QuizAttempt {
    private String quizTitle;
    private int score;
    private Timestamp attemptDate;

    public QuizAttempt(String quizTitle, int score, Timestamp attemptDate) {
        this.quizTitle = quizTitle;
        this.score = score;
        this.attemptDate = attemptDate;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public int getScore() {
        return score;
    }

    public Timestamp getAttemptDate() {
        return attemptDate;
    }
}
