package Main.services;

import java.sql.*;
import java.util.*;

import Main.database.Database;
import Main.models.Question;


public class QuestionService {
    
    public static List<Question> getQuestionsByQuizId(int quizId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question WHERE quiz_id = ?";  
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setInt(1, quizId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int questionId = rs.getInt("question_id");
                    String questionText = rs.getString("question_text");
                    String option1 = rs.getString("option1");
                    String option2 = rs.getString("option2");
                    String option3 = rs.getString("option3");
                    String option4 = rs.getString("option4");
                    String correctAnswer = rs.getString("correct_option");

                   
                    Question question = new Question(questionId, quizId, questionText, option1, option2, option3, option4, correctAnswer);
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

