import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Port;
import javax.swing.*;

/**
 * This class is the entry point of the PongGame
 */
public class PongGame implements ActionListener, Runnable {

    static final int FRAME_WIDTH = 1014;
    static final int FRAME_HEIGHT = 615;
    static final String BLACK_COLOR = "#333333";
    static final Dimension btnDimen = new Dimension(110, 25);


    //required for playing the game sound
    static AudioInputStream audioInputStream;
    static Clip clip;
    Thread mainThread;
    Thread progressThread;

    PongGame() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    //Declaring the required components
    JFrame inputFrame = new JFrame("Pong Game");
    static JButton playButton = new JButton("Play");
    JLabel title = new JLabel("Welcome to the Pong Game!");
    static JTextField player1 = new JTextField();
    static JTextField player2 = new JTextField();
    static JTextField scoreToWin = new JTextField();
    JLabel player1Label = new JLabel("Player 1:");
    JLabel player2Label = new JLabel("Player 2:");
    JLabel scoreToWinLabel = new JLabel("To Win:");
    ImageIcon imgIcon = new ImageIcon("images/pongIcon.png");
    static JProgressBar progressBar = new JProgressBar();


    void takeUserNameInput() throws IOException {
        inputFrame.setIconImage(imgIcon.getImage());
        //setting the title
        title.setBounds((FRAME_WIDTH - 450) / 2, 110, 450, 40);
        title.setFont(new Font("bold", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        //setting player 1 label
        player1Label.setBounds(330, 200, 75, 25);
        player1Label.setFont(new Font("poppins", Font.BOLD, 13));
        player1Label.setForeground(Color.WHITE);

        //setting player 2 label
        player2Label.setBounds(330, 250, 75, 25);
        player2Label.setFont(new Font("poppins", Font.BOLD, 13));
        player2Label.setForeground(Color.WHITE);


        //setting score to win label
        scoreToWinLabel.setBounds(330, 300, 75, 25);
        scoreToWinLabel.setFont(new Font("poppins", Font.BOLD, 13));
        scoreToWinLabel.setForeground(Color.WHITE);

        //setting text field
        player1.setBounds(400, 200, 200, 25);
        player1.setFont(new Font("Arial", Font.BOLD, 13));
        player2.setBounds(400, 250, 200, 25);
        player2.setFont(new Font("Arial", Font.BOLD, 13));
        scoreToWin.setBounds(400, 300, 200, 25);
        scoreToWin.setFont(new Font("Arial", Font.BOLD, 13));


        //setting the play button
        playButton.setBounds(450, 355, 100, 30);
        playButton.setFocusable(false);
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        playButton.setSize(btnDimen);
        playButton.addActionListener(this);


        //Setting the ProgressBar
        progressBar.setValue(0);
        progressBar.setBounds((FRAME_WIDTH - 450) / 2, 355, 450, 40);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("", Font.BOLD, 20));
        progressBar.setForeground(Color.red);
        progressBar.setBackground(Color.BLACK);
        progressBar.setVisible(false);

        //adding the background
        inputFrame.setContentPane(new JLabel(getImage()));
//        inputFrame.setBackground(new Color(0x89CFF0));
        //adding components to the frame
        inputFrame.add(title);
        inputFrame.add(player1Label);
        inputFrame.add(player2Label);
        inputFrame.add(scoreToWinLabel);
        inputFrame.add(player1);
        inputFrame.add(player2);
        inputFrame.add(scoreToWin);
        inputFrame.add(playButton);
        inputFrame.add(progressBar);

        inputFrame.setResizable(false);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        inputFrame.setLayout(null);
        inputFrame.setLocationRelativeTo(null);

        inputFrame.setVisible(true);
//        fillProgressBar();


    }


    public static void main(String[] args) {

        PongGame pongGame = new PongGame();
    }

    @Override
    public void run() {
        try {
            this.takeUserNameInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        if (player1Name.isEmpty()) {
            JOptionPane.showMessageDialog(inputFrame, "Player 1 name cant be empty");
            return;
        }
        if (player2Name.isEmpty()) {
            JOptionPane.showMessageDialog(inputFrame, "Player 2 name cant be empty");
            return;
        }
        if (toWin.isEmpty()) {
            JOptionPane.showMessageDialog(inputFrame, "To win cant be empty");
            return;
        }
        playButton.setVisible(false);
        progressBar.setVisible(true);
        ProgressThread progressThread = new ProgressThread();
        progressThread.start();
//        inputFrame.setVisible(false);
//        clip.stop();
//        GameFrame gameFrame = new GameFrame(player1Name, player2Name, Integer.parseInt(toWin));
    }

    //Used for setting the imageIcon
    static ImageIcon getImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("images/pongimg.png"));
        Image scaled = img.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        return icon;
    }


    synchronized static void fillProgressBar() throws InterruptedException {

        // progressBar.setValue();
        int counter = 0;
        while (counter <= 100) {
            progressBar.setValue(counter);
            progressBar.setString(String.valueOf(counter));
            try {
                Thread.sleep(17);
            } catch (Exception e) {
                e.printStackTrace();
            }
            counter += 1;
        }
        progressBar.setString("READY TO PLAY ...!");
        Thread.sleep(500);
        String player1Name = player1.getText();
        String player2Name = player2.getText();
        String toWin = scoreToWin.getText();
        GameFrame gameFrame = new GameFrame(player1Name, player2Name, Integer.parseInt(toWin));
        player1.setText("");
        player2.setText("");
        scoreToWin.setText("");
        progressBar.setString("");
        progressBar.setValue(0);
        progressBar.setVisible(false);
        playButton.setVisible(true);

    }
}

class ProgressThread extends Thread {
    @Override
    public void run() {
        try {
            PongGame.fillProgressBar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



