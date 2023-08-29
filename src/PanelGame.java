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
    float xVelo = 4;
    float yVelo = 4;
    int x = 0;
    int y = 0;

    int x2 = 0;
    int y2 = 0;
    int tempoDeJogo = 0;
    int segundos = 0;
    boolean direita = true;
    float vaiPralaX = 200;
    float vaiPralaY = 200;
    float angulo;
    float cooldown;
    float angulo2;
    

    Image robo ;
    Image predios;

    PanelGame(){
        this.setSize(700,500);
        
        this.addMouseListener(mouse);
        this.setDoubleBuffered(true);
        this.addMouseMotionListener(mouse);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        

        try {
            robo = ImageIO.read(getClass().getResourceAsStream("Player.png"));
        } catch (IOException e) {
        }

        try {
            predios = ImageIO.read(getClass().getResourceAsStream("background.png"));
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
      g2D.drawImage(predios,x2,0, null);
      
      g2D.setColor(Color.pink);
      //g2D.translate(x2, y2);
      
    //g2D.rotate((vaiPralaX - x) - (vaiPralaY - y));
      
      
      g2D.fillArc(x, y, 50, 50, (int)((x - vaiPralaX) + (y - vaiPralaY)),50);
      //g2D.rotate(0.77);
      
      
      //g2D.drawImage(robo,x,y,null);
      //g2D.fillRect(x, y, 20, 80);
      g2D.dispose();
    }

    public void update(){
        if(mouse.click){
            angulo2 = (float) Math.atan2(vaiPralaY + y,vaiPralaX + x) ;
            vaiPralaX = mouse.xizinho - 32;
            vaiPralaY = mouse.ypsilinho - 2;
            angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
            xVelo = (float) ((8)*Math.cos(angulo));
            yVelo = (float) ((8 )*Math.sin(angulo));
            x += xVelo;
            y += yVelo;
        }

        if(x2 >= 0){
            x2++;

        }

        //System.out.println(tempoDeJogo);
        tempoDeJogo++;
        if(tempoDeJogo > 60){
            tempoDeJogo = 0;
            segundos++;

            //System.out.println(segundos);


        }
    }


}
