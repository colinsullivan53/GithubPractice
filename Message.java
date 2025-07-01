import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private static final String message = "hello, world! do you like programming?";

    private static final String[] pickup_lines = {
        "are you a repository? cuz i wanna commit to you",
        "you conflict my merge, cuz i can't resolve my feelings 4 u",
        "are you \"main\"? cuz i wanna push my branch into you",
        "i'd merge for 1000 hours to resolve our differences, cuz ur worth it" ,
        "i care about you, so i'd never force push, only pull request",
        "not even \"git add .\" can stage all my feelings for you",
        "seeing you runs \"git rm\" on all my worries"
    };

    public static String randLine() {
        return pickup_lines[((int) (1000 * Math.random())) % pickup_lines.length];
    }

    public static void buttonPressed() {
        int rand = ((int) (1000 * Math.random())) % pickup_lines.length;
        Object[] options = {"Ok", "Another!"};  
        int choice = JOptionPane.showOptionDialog(null, 
            pickup_lines[rand], 
            "Pickup Line #" + (rand+1), 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            options, 
            options[0]);   
 
        if (choice == 0) {
            return;
        } else if (choice == 1) {
            buttonPressed();
        }  
    }
    public static void main(String[] args) { 
        JFrame frame = new JFrame("Message");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 130);
        frame.setLayout(new FlowLayout());
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
 
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(message); 
 
        JCheckBox yes = new JCheckBox("Yup");
        JCheckBox no = new JCheckBox("Yes!!!!!");
 
        ButtonGroup group = new ButtonGroup();
        group.add(yes);
        group.add(no);

        questionPanel.add(label);
        questionPanel.add(yes);
        questionPanel.add(no);
        
        JPanel buttonPanel = new JPanel();
        JButton pickupLineButton = new JButton("Random Pickup Line!");
        pickupLineButton.addActionListener(e -> {
            buttonPressed();
        }); 
        buttonPanel.add(pickupLineButton);

        displayPanel.add(questionPanel); 
        displayPanel.add(buttonPanel);

        frame.add(displayPanel);
 
        pickupLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
 
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Response.txt", false))) {
                    writer.write(message + "\n" + randLine());
                    writer.newLine();
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }); 
  
        
        displayPanel.add(questionPanel); 
        displayPanel.add(pickupLineButton);

        frame.add(displayPanel); 
 
        frame.setVisible(true);
    }
}