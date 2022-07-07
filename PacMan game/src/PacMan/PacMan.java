package PacMan;

import javax.swing.*;

public class PacMan extends JFrame{

    public PacMan()
    {
        add(new PacManModel());
    }


    public static void main(String[] args)
    {
        PacMan pac = new PacMan();
        pac.setVisible(true);
        pac.setTitle("PacManGame");
        pac.setSize(600,600);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);

    }

}

