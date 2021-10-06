import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public  class WinnerDialog extends JFrame {
    static final int FRAME_WIDTH= 300; //width of window
    static final int FRAME_HEIGHT = (int) (FRAME_WIDTH * (0.5555)); //height of the window
    GameFrame gameFrame;
//    GameFrame GameFrame = new GameFrame();
    JLabel winnerName;
    JButton restart;
    JButton Quit;
    ImageIcon imgIcon = new ImageIcon("images/pongIcon.png");
    GamePanel panel;
    WinnerDialog(String wName,GameFrame frame)
    {


        this.gameFrame = frame;
        this.winnerName = new JLabel(wName);
        winnerName.setBounds(30,30,250,30);


        restart =new JButton();
        restart.setBounds(30,90,100,20);
        restart.setText("Restart");
        restart.setFocusable(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.restart();
            }
        });




        Quit=new JButton();
        Quit.setBounds(160,90,100,20);
        Quit.setText("Quit");
        Quit.setFocusable(false);
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.quit();
            }
        });



        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
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
//        WinnerDialog dialog = new WinnerDialog("Me");

    }


    static ImageIcon getImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("images/pongimg.png"));
        Image scaled = img.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        return icon;

    }
}