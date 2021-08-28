import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    // this is for the theme bgm
    static AudioInputStream audioInputStream;
    static Clip clip;

    static final int GAME_WIDTH = 1000; //width of window
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555)); //height of the window
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    //Creating the panel
    GamePanel(String p1, String p2) {
        newPaddles();//creating the paddles and adding to the panel
        newBall();//creating new ball
        score = new Score(GAME_WIDTH, GAME_HEIGHT, p1, p2);
        this.setFocusable(true);
        this.addKeyListener(new AL());// adding the action listener
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start(); //this is start the execution of the run method
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    //this method is called in the background
    @Override
    public void paint(Graphics g) {
        //Creating image
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation

    }

    public void move() {
        //Updating the position of the paddles and ball
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {

        //bounce ball off top & bottom window edges
        if (ball.y <= 0) {
            playMoveSound();
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            playMoveSound();
            ball.setYDirection(-ball.yVelocity);
        }

        //bounce ball off paddles
        if (ball.intersects(paddle1)) {
            playMoveSound();
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //if the ball hit the player 2's paddle
        if (ball.intersects(paddle2)) {
            playMoveSound();
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }


        //stops paddles at window edges
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        //give a player 1 point and creates new paddles & ball
        if (ball.x <= 0) {
            score.player2++;
            playCrashSound();
            //play the gameover sound and restart the bgm
            clip.close();
            playSound();
            newPaddles();
            newBall();
            System.out.println(score.Player2Name + " score : " + score.player2);
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            playCrashSound();
            //play the gameover sound and restart the bgm
            clip.close();
            playSound();
            newPaddles();
            newBall();
            System.out.println(score.Player1Name + " score : " + score.player1);
        }
    }

    public void run() {
        /*
        A game loop runs continuously during gameplay. Each turn of the loop,
        it processes user input without blocking, updates the game state,
        and renders the game. It tracks the passage of time to control
        the rate of gameplay.
         */

        playSound();// start the bgm
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }


    public static void playMoveSound() {
        //played when the ball hit top/bottom or to player's paddle
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\move.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void playCrashSound() {
        //played when the player misses the ball
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\gameover.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
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

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            //called when the key is pressed
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            //called when the key is released
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}