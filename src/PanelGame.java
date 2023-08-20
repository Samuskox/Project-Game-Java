import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable, ActionListener{

    Keys mouse = new Keys();
    //Thread gameThread;
    //Image robo;

    int xVelo = 1;
    int yVelo = 1;
    int x = 0;
    int y = 0;
    Timer timer;

    PanelGame(){

        
        this.setSize(650,450);
        this.setBackground(Color.BLACK);
        //this.setAlignmentX(CENTER_ALIGNMENT);
        //this.setAlignmentY(CENTER_ALIGNMENT);
        this.addMouseListener(mouse);
        this.setDoubleBuffered(true);
        //robo = new ImageIcon("Player.png").getImage(); 
        timer = new Timer(1000, this);
        
    }

    //public void startGameThread(){
    //    gameThread = new Thread(this);
    //    gameThread.start();
    //}

    @Override
    public void run() {
        
        //while(gameThread != null){
        //    System.out.println("oi to rodando porra");
        //}
    }

    public void paint(Graphics g){

        super.paint(g);
      Graphics2D g2D = (Graphics2D) g;
      //g2D.drawImage(robo,50,50,null);
      g2D.fillRect(x, y, 80, 80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        x = x+xVelo;
        repaint();
    }


}
