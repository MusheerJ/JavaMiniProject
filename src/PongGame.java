import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class PongGame implements ActionListener, Runnable {

    static final int FRAME_WIDTH = 1014;
    static final int FRAME_HEIGHT = 615;
    static final String BLACK_COLOR = "#333333";
    static final Dimension btnDimen = new Dimension(110, 25);

    static AudioInputStream audioInputStream;
    static Clip clip;
    Thread mainThread;

    PongGame() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    JFrame inputFrame = new JFrame("Pong Game");
    JButton playButton = new JButton("Play");
    JLabel title = new JLabel("Welcome to the Pong Game!");
    JTextField player1 = new JTextField();
    JTextField player2 = new JTextField();
    JTextField scoreToWin = new JTextField();
    JLabel player1Label = new JLabel("Player 1:");
    JLabel player2Label = new JLabel("Player 2:");
    JLabel scoreToWinLabel = new JLabel("To Win:");


    void takeUserNameInput() {
        //setting the title
        title.setBounds((FRAME_WIDTH - 450) / 2, 110, 450, 40);
        title.setFont(new Font("bold", Font.BOLD, 30));
        title.setForeground(Color.getColor(BLACK_COLOR));

        //setting player 1 label
        player1Label.setBounds(330, 200, 75, 25);
        player1Label.setFont(new Font("poppins", Font.BOLD, 13));
        player1Label.setForeground(Color.getColor(BLACK_COLOR));

        //setting player 2 label
        player2Label.setBounds(330, 250, 75, 25);
        player2Label.setFont(new Font("poppins", Font.BOLD, 13));
        player2Label.setForeground(Color.getColor(BLACK_COLOR));


        //setting score to win label
        scoreToWinLabel.setBounds(330, 300, 75, 25);
        scoreToWinLabel.setFont(new Font("poppins", Font.BOLD, 13));
        scoreToWinLabel.setForeground(Color.getColor(BLACK_COLOR));

        //setting text field
        player1.setBounds(400, 200, 200, 25);
        player1.setFont(new Font("Arial", Font.BOLD, 13));
        player2.setBounds(400, 250, 200, 25);
        player2.setFont(new Font("Arial", Font.BOLD, 13));
        scoreToWin.setBounds(400, 300, 200, 25);
        scoreToWin.setFont(new Font("Arial", Font.BOLD, 13));


        //setting the play button
        playButton.setBounds(450, 355, 100, 25);
        playButton.setFocusable(false);
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        playButton.setSize(btnDimen);
        playButton.addActionListener(this);

        //adding the background
        inputFrame.setContentPane(new JLabel(new ImageIcon("C:\\College Material\\T.Y.Btech\\JavaMiniProject\\images\\baby_blue.jpg")));
        inputFrame.setBackground(new Color(0x89CFF0));
        //adding components to the frame
        inputFrame.add(title);
        inputFrame.add(player1Label);
        inputFrame.add(player2Label);
        inputFrame.add(scoreToWinLabel);
        inputFrame.add(player1);
        inputFrame.add(player2);
        inputFrame.add(scoreToWin);
        inputFrame.add(playButton);

        inputFrame.setResizable(false);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        inputFrame.setLayout(null);
        inputFrame.setLocationRelativeTo(null);

        inputFrame.setVisible(true);


    }


    public static void main(String[] args) {

        PongGame pongGame = new PongGame();
    }

    @Override
    public void run() {
        this.takeUserNameInput();
//        playSound();
    }

    public static void playSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\music.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String player1Name = player1.getText();
        String player2Name = player2.getText();
        String toWin = scoreToWin.getText();
        player1.setText("");
        player2.setText("");
        scoreToWin.setText("");
        if (player1Name.isEmpty()) {
            return;
        }
        if (player2Name.isEmpty()) {
            return;
        }
        if (toWin.isEmpty()) {
            return;
        }
//        inputFrame.setVisible(false);
//        clip.stop();
        GameFrame gameFrame = new GameFrame(player1Name, player2Name,Integer.parseInt(toWin));
    }
}
