import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class is used for creating the GameFrame
 */

public class GameFrame {

    static GamePanel panel;
    static JFrame frame;
    ImageIcon imgIcon = new ImageIcon("images/pongIcon.png");
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem restart = new JMenuItem("restart");
    JMenuItem close = new JMenuItem("close");
    static String player1;
    static String player2;
    static int toWin;


    //Adding the GamePanel to the window
    GameFrame(String p1, String p2, int tw) {
        player1 = p1;
        player2 = p2;
        toWin = tw;
        panel = new GamePanel(p1, p2, toWin);
        frame = new JFrame();


        restart.addActionListener(restartAction);
        close.addActionListener(closeAction);

        menu.add(restart);
        menu.add(close);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.setIconImage(imgIcon.getImage());
        frame.add(panel);
        frame.setTitle("Pong Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        frame.setBackground(new Color(0x89CFF0));
        frame.setBackground(Color.BLACK);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // align to center
//        System.out.println(frame.getWidth() + " " + getHeight());
    }


    //when user restart the game
    GameFrame() {
        panel = new GamePanel(player1, player2, toWin);
        frame = new JFrame();
        restart.addActionListener(restartAction);
        close.addActionListener(closeAction);

        menu.add(restart);
        menu.add(close);

        menuBar.add(menu);


        frame.setJMenuBar(menuBar);
        frame.setIconImage(imgIcon.getImage());
        frame.add(panel);
        frame.setTitle("Pong Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        frame.setBackground(new Color(0x89CFF0));
        frame.setBackground(Color.BLACK);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // align to center
    }


    ActionListener closeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.close(0);
            frame.dispose();

            panel.restart();
        }
    };


    ActionListener restartAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            panel.restart();
            new GameFrame();
        }
    };

    public static void restart() {
        frame.dispose();
        panel.restart();
        new GameFrame();

    }

    public static void quit() {
        frame.dispose();
        panel.restart();
    }


}