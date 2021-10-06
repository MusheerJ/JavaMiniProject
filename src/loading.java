import javax.swing.*;
import java.awt.*;

public class loading extends JFrame{
    //JFrame Frame= new JFrame();
    JProgressBar progressBar=new JProgressBar();

    loading(){
        progressBar.setValue(0);
        progressBar.setBounds(0,450,1014,75);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("MV boli",Font.BOLD,25));
        progressBar.setForeground(Color.red);
        progressBar.setBackground(Color.BLACK);

        this.add(progressBar);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1014,615);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        fill();


    }

    private void add(ImageIcon img) {
        return;
    }

    public  void fill(){
        // progressBar.setValue();
        int counter=0;
        while (counter<=100)
        {
            progressBar.setValue(counter);
            try {
                Thread.sleep(50);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            counter+=1;
        }
        progressBar.setString("READY TO PLAY ...!");

    }

    public static void main(String[] args) {


        new loading();

    }



}
