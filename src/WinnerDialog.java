import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinnerDialog extends JFrame {
    static final int FRAME_WIDTH = 400; //width of window
    static final int FRAME_HEIGHT = (int) (FRAME_WIDTH * (0.5555)); //height of the window
    JLabel winnerName;
    JButton restart;
    JButton Quit;
    ImageIcon imgIcon = new ImageIcon("images/pongIcon.png");
    GamePanel panel;

    WinnerDialog(String wName) {


        this.winnerName = new JLabel(wName);
        winnerName.setBounds(70, 40, 400, 30);
//        winnerName.setLocation(75, 30);
        winnerName.setFont(new Font("poppoins", Font.BOLD, 22));


        restart = new JButton();
        restart.setBounds(50, 100, 120, 40);
        restart.setText("Restart");
        restart.setFocusable(false);
        restart.setFont(new Font("poppoins", Font.BOLD, 18));
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameFrame.restart();
            }
        });


        Quit = new JButton();
        Quit.setBounds(230, 100, 120, 40);
        Quit.setText("Quit");
        Quit.setFont(new Font("poppoins", Font.BOLD, 18));
        Quit.setFocusable(false);
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameFrame.quit();
            }
        });


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.add(winnerName);
        this.add(restart);
        this.add(Quit);
        this.setIconImage(imgIcon.getImage());
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        WinnerDialog dialog = new WinnerDialog("The Winner is Player 1");

    }

    public static void showWinnerDialog(String winnerName) {
        WinnerDialog dialog = new WinnerDialog(winnerName);
    }


    static ImageIcon getImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("images/pongimg.png"));
        Image scaled = img.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        return icon;

    }
}