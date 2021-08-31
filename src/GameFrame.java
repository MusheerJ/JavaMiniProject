import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel panel;
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

        restart.addActionListener(restartAction);
        close.addActionListener(closeAction);

        menu.add(restart);
        menu.add(close);

        menuBar.add(menu);

        this.setJMenuBar(menuBar);

        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        this.setBackground(new Color(0x89CFF0));
        this.setBackground(Color.BLACK);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // align to center
//        System.out.println(this.getWidth() + " " + getHeight());
    }


    //when user restart the game
    GameFrame() {
        panel = new GamePanel(player1, player2, toWin);

        restart.addActionListener(restartAction);
        close.addActionListener(closeAction);

        menu.add(restart);
        menu.add(close);

        menuBar.add(menu);

        this.setJMenuBar(menuBar);

        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        this.setBackground(new Color(0x89CFF0));
        this.setBackground(Color.BLACK);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // align to center
    }


    ActionListener closeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.close(0);
            dispose();
            panel.restart();
        }
    };


    ActionListener restartAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            panel.restart();
            new GameFrame();

        }
    };
}