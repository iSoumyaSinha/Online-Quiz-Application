package Main.ui;

import javax.swing.*;

import Main.models.Question;
import Main.models.Quiz;
import Main.models.User;
import Main.services.QuestionService;
import Main.services.QuizS;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizGUI extends JFrame {
    private User user;
    private Quiz quiz;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    public QuizGUI(User user, Quiz quiz) {
        this.user = user;
        this.quiz = quiz;

       
        this.questions = QuestionService.getQuestionsByQuizId(quiz.getQuizId());

       
        setTitle("Quiz: " + quiz.getTitle());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
      
        questionLabel = new JLabel();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        optionsGroup = new ButtonGroup();
        nextButton = new JButton("Next");

      
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);
        
        nextButton.setBackground(Color.BLUE);  // Set light blue (cyan) background for the "Next" button
        nextButton.setForeground(Color.WHITE);  // Set white text color for the button
        nextButton.setOpaque(true);

      
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(questionLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(nextButton);

        add(panel);

       
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();         
                loadNextQuestion();    
            }
        });

        
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            questionLabel.setText(question.getQuestionText());
            option1.setText(question.getOption1());
            option2.setText(question.getOption2());
            option3.setText(question.getOption3());
            option4.setText(question.getOption4());
            optionsGroup.clearSelection();
            currentQuestionIndex++;
        } else {
            endQuiz(); 
        }
    }

    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex - 1);
        
        String selectedAnswer = null;

        if (option1.isSelected()) selectedAnswer = option1.getText();
        if (option2.isSelected()) selectedAnswer = option2.getText();
        if (option3.isSelected()) selectedAnswer = option3.getText();
        if (option4.isSelected()) selectedAnswer = option4.getText();
//
//        if (selectedAnswer != null && selectedAnswer.equals(question.getCorrectOption())) {
//            score++;
//        }
        if (selectedAnswer != null) {
            if (selectedAnswer.equals(question.getCorrectOption())) {
                score++;
                JOptionPane.showMessageDialog(this, "Correct Answer!", "Feedback", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Answer. The correct answer is: " + question.getCorrectOption(), 
                                              "Feedback", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an answer!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    

   
    currentQuestionIndex++;


}

    private void endQuiz() {
        JOptionPane.showMessageDialog(this, "Your score is: " + score);
        
        QuizS.saveQuizAttempt(user.getUserId(), quiz.getQuizId(), score);

        
        dispose();  
    }
}
