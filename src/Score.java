import java.awt.*;

public class Score extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    String Player1Name;
    String Player2Name;


    Score(int GAME_WIDTH, int GAME_HEIGHT, String player1Name, String player2Name) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
        this.Player1Name = player1Name;
        this.Player2Name = player2Name;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 600; i += 20) {

            g.drawLine(500, i, 500, i + 10);     // to draw the dotted line in middle
        }

        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.BOLD, 30));
        g.drawString(this.Player1Name, (GAME_WIDTH / 2) - 460, 90);
        g.drawString(this.Player2Name, (GAME_WIDTH / 2) + 340, 90);
        g.setFont(new Font("Consolas", Font.PLAIN, 40));

        //g.drawLine(GAME_WIDTH / 2, 1, GAME_WIDTH / 2, GAME_HEIGHT);

        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (GAME_WIDTH / 2) - 420, 50);
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (GAME_WIDTH / 2) + 400, 50);
    }


}