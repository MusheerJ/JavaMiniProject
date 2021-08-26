import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.logging.Handler;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class PongGame implements ActionListener {

    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = (int) (FRAME_WIDTH * (0.5555));
    static final String BLACK_COLOR = "#333333";
    static final Dimension btnDimen = new Dimension(110, 25);

    static AudioInputStream audioInputStream;
    static Clip clip;

    JFrame inputFrame = new JFrame("Pong Game");
    JButton playButton = new JButton("Play");
    JLabel title = new JLabel("Welcome to the Pong Game!");
    JTextField player1 = new JTextField();
    JTextField player2 = new JTextField();
    JLabel player1Label = new JLabel("Player 1:");
    JLabel player2Label = new JLabel("Player 2:");


    void takeUserNameInput() {
        //setting the title
        title.setBounds(275, 60, 450, 40);
        title.setFont(new Font("bold", Font.BOLD, 30));
        title.setForeground(Color.getColor(BLACK_COLOR));

        //setting player 1 label
        player1Label.setBounds(330, 160, 75, 25);
        player1Label.setFont(new Font("poppins", Font.BOLD, 13));
        player1Label.setForeground(Color.getColor(BLACK_COLOR));

        //setting player 2 label
        player2Label.setBounds(330, 210, 75, 25);
        player2Label.setFont(new Font("poppins", Font.BOLD, 13));
        player2Label.setForeground(Color.getColor(BLACK_COLOR));

        //setting text field
        player1.setBounds(400, 160, 200, 25);
        player1.setFont(new Font("Arial", Font.BOLD, 13));
        player2.setBounds(400, 210, 200, 25);
        player2.setFont(new Font("Arial", Font.BOLD, 13));


        //setting the play button
        playButton.setBounds(450, 260, 100, 25);
        playButton.setFocusable(false);
        playButton.setFont(new Font("Arial", Font.BOLD, 13));
        playButton.setSize(btnDimen);
        playButton.addActionListener(this);

        //adding the background
        inputFrame.setContentPane(new JLabel(new ImageIcon("C:\\College Material\\T.Y.Btech\\JavaMiniProject\\images\\baby_blue.jpg")));

        //adding components to the frame
        inputFrame.add(title);
        inputFrame.add(player1Label);
        inputFrame.add(player2Label);
        inputFrame.add(player1);
        inputFrame.add(player2);
        inputFrame.add(playButton);

        inputFrame.setResizable(false);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        inputFrame.setLayout(null);
        inputFrame.setLocationRelativeTo(null);

        inputFrame.setVisible(true);


    }


    public static void main(String[] args) {

        new PongGame().takeUserNameInput();
        playSound();


    }

    public static void playSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\music.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();


            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // A GUI element to prevent the Clip's daemon Thread
                    // from terminating at the end of the main()
//                    JOptionPane.showMessageDialog(null, "Close to exit!");

                }
            });

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String player1Name = player1.getText();
        String player2Name = player2.getText();
        if (player1Name.isEmpty()) {
            return;
        }
        if (player2Name.isEmpty()) {
            return;
        }
        inputFrame.setVisible(false);
        clip.stop();
        GameFrame gameFrame = new GameFrame(player1Name, player2Name);


    }
}
