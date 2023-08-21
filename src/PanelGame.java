import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable{

    Keys mouse = new Keys();
    

    boolean run = true;
    int xVelo = 1;
    int yVelo = 1;
    int x = 0;
    int y = 0;

    PanelGame(){
        this.setSize(650,450);
        this.setBackground(Color.PINK);
        this.addMouseListener(mouse);
        this.setDoubleBuffered(true);
        this.addMouseMotionListener(mouse);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
    }

    //Thread gameThread;
    //public void startGameThread(){
    //    gameThread = new Thread(this);
    //    gameThread.start();
    //}


    //Aqui no run() onde tudo que vai ocorrer milisegundo por milisegundo
    @Override
    public void run() {
        while(run == true){
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }
            //System.out.println("oi to rodando porra");
            x = x+xVelo;
            y = y+yVelo;
            repaint();
            update();
        }

     
    }

    public void paint(Graphics g){

    super.paint(g);
      Graphics2D g2D = (Graphics2D) g;
      //g2D.drawImage(robo,50,50,null);
      g2D.fillRect(x, y, 80, 80);
      g2D.dispose();
    }

    public void update(){

    }


}
