import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel panel;

    //Adding the GamePanel to the window
    GameFrame(String p1, String p2) {
        panel = new GamePanel(p1, p2);
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.cyan);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // align to center
    }
}