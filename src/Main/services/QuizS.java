package Main.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Main.database.Database;
import Main.models.QuizAttempt;

public class QuizS {
	
	    public static void saveQuizAttempt(int userId, int quizId, int score) {
	        String sql = "INSERT INTO user_scores (user_id, quiz_id, score) VALUES (?, ?, ?)";
	        try (Connection conn = Database.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, userId);
	            stmt.setInt(2, quizId);
	            stmt.setInt(3, score);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static List<QuizAttempt> getUserQuizAttempts(int userId) {
	        List<QuizAttempt> attempts = new ArrayList<>();
	        String sql = "SELECT quizzes.title, user_scores.score, user_scores.attempt_date " +
	                     "FROM user_scores " +
	                     "JOIN quizzes ON user_scores.quiz_id = quizzes.quiz_id " +
	                     "WHERE user_scores.user_id = ?";
	        try (Connection conn = Database.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                String quizTitle = rs.getString("title");
	                int score = rs.getInt("score");
	                Timestamp attemptDate = rs.getTimestamp("attempt_date");

	                attempts.add(new QuizAttempt(quizTitle, score, attemptDate));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return attempts;
	    }

	}



