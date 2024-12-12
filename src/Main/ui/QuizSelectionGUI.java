package Main.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Main.models.Quiz;
import Main.models.User;
import Main.services.QuizService;

public class QuizSelectionGUI extends JFrame {
    private User user;
    private JList<String> quizList;
    private DefaultListModel<String> quizListModel;

    public QuizSelectionGUI(User user) {
        this.user = user;
        setTitle("Select a Quiz");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
      

        
        quizListModel = new DefaultListModel<>();

       
       List<Quiz> quizzes = QuizService.getAllQuizzes();
        for (Quiz quiz : quizzes) {
            quizListModel.addElement(quiz.getTitle());
        }

        
        quizList = new JList<>(quizListModel);
        quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(quizList);

        JButton startQuizButton = new JButton("Start Quiz");
        
        startQuizButton.setBackground(java.awt.Color.RED);  
        startQuizButton.setForeground(java.awt.Color.WHITE); 

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(startQuizButton, BorderLayout.SOUTH);

        add(panel);

        
        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }

//			@Override

        });
    }

    private void startQuiz() {
        int selectedIndex = quizList.getSelectedIndex();
        if (selectedIndex != -1) {
            Quiz selectedQuiz = QuizService.getAllQuizzes().get(selectedIndex);  
            new QuizGUI(user, selectedQuiz).setVisible(true); 
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Please select a quiz.");
        }
    }


    
}

