import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        GamePlay gamePlay=new GamePlay();
        frame.setBounds(10, 10, 700, 700);
        frame.setTitle("Break Out Ball");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);
      
    }
}