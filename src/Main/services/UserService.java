package Main.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import Main.database.Database;
import Main.models.User;

public class UserService {
	
	   
	 public static boolean registerUser(User user) {
	        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
	        try (Connection conn = Database.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            
	            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

	            stmt.setString(1, user.getUsername());
	            stmt.setString(2, hashedPassword);

	          
	            int rowsInserted = stmt.executeUpdate();
	            return rowsInserted > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	  
	    public static User loginUser(String username, String password) {
	        String query = "SELECT * FROM users WHERE username = ?";
	        try (Connection conn = Database.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, username);
	            ResultSet rs = stmt.executeQuery();

	           
	            if (rs.next()) {
	                String storedHashedPassword = rs.getString("password");

	               
	                if (BCrypt.checkpw(password, storedHashedPassword)) {
	                    
	                    return new User(rs.getInt("user_id"), username, storedHashedPassword);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
	}



