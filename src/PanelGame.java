import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable{

    Keys mouse = new Keys();
    

    boolean run = true;
    int xVelo = 1;
    int yVelo = 1;
    int x = 0;
    int y = 0;
    int tempoDeJogo = 0;
    int segundos = 0;
    boolean direita = true;
    

    Image robo ;

    PanelGame(){
        this.setSize(650,450);
        this.setBackground(Color.PINK);
        this.addMouseListener(mouse);
        this.setDoubleBuffered(true);
        this.addMouseMotionListener(mouse);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        

        try {
            robo = ImageIO.read(getClass().getResourceAsStream("Player.png"));
        } catch (IOException e) {
        }
    }

    //Thread gameThread;
    //public void startGameThread(){
    //    gameThread = new Thread(this);
    //    gameThread.start();
    //}


    //Aqui no run() onde tudo que vai ocorrer milisegundo por milisegundo
    @Override
    public void run() {
        while(run){
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
            }

            repaint();
            update();
        }

     
    }

    public void paint(Graphics g){

        super.paint(g);
      Graphics2D g2D = (Graphics2D) g;
      g2D.drawImage(robo,x,y,null);
      //g2D.fillRect(x, y, 20, 80);
      g2D.dispose();
    }

    public void update(){
        //System.out.println("oi to rodando porra");
        
        x += xVelo;
     while(direita){
        x += xVelo;
     }

     //while(direita == false){
     //   x += -xVelo;
     //}
     //if(x == 650-robo.getWidth(null)){
     //   direita = false;
     //}

     //if(x == 0+robo.getWidth(null)){
     //   direita = true;
     //}


     
         //x += xVelo;
     
     
       
     
     //if(y>=450-64){
     //   y += -yVelo;
     //}
     
        
        //System.out.println(tempoDeJogo);
        tempoDeJogo++;
        if(tempoDeJogo > 60){
            tempoDeJogo = 0;
            segundos++;

            System.out.println(segundos);


        }
    }


}
