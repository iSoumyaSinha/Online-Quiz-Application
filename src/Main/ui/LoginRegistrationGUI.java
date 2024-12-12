package Main.ui;

import javax.swing.*;

import Main.models.User;
import Main.services.UserService;

import java.awt.*;
import java.awt.event.*;

public class LoginRegistrationGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginRegistrationGUI() {
        setTitle("Login / Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE); 
        registerButton.setBackground(Color.BLUE); 
        registerButton.setForeground(Color.WHITE);

      
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(3, 2));
        
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

       
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

      
        User user = UserService.loginUser(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

             new QuizSelectionGUI(user).setVisible(true);
            dispose();  
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username or password cannot be empty.");
            return;
        }

       
       User newUser = new User(0,username, password);
        UserService.registerUser(newUser);

        JOptionPane.showMessageDialog(this, "Registration Successful!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegistrationGUI().setVisible(true);
            }
        });
    }
}

