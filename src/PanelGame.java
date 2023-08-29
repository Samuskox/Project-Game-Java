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
    //Image predios;

    Image fundo1;
    Image fundo2;
    Image fundo3;


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

        //try {
        //    predios = ImageIO.read(getClass().getResourceAsStream("background.png"));
        //} catch (IOException e) {
        //}

        try {
            fundo1 = ImageIO.read(getClass().getResourceAsStream("background1.png"));
        } catch (IOException e) {
        }
        try {
            fundo2 = ImageIO.read(getClass().getResourceAsStream("background2.png"));
        } catch (IOException e) {
        }
        try {
            fundo3 = ImageIO.read(getClass().getResourceAsStream("background3.png"));
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

    int vx1 = 0;
    int vx2 = 0;
    int vx3 = 0;

    public void paint(Graphics g){

        

        super.paint(g);
      Graphics2D g2D = (Graphics2D) g;
     // g2D.drawImage(predios,x2,0, null);

     g2D.drawImage(fundo1, vx1, 0, null);
     g2D.drawImage(fundo2, vx2, 0, null);
     g2D.drawImage(fundo3, vx3, 0, null);
      
      g2D.setColor(Color.pink);
      //g2D.translate(x, y);
      
    //g2D.rotate(0.2);
      
      
      g2D.fillArc(x, y, 80, 80, (int)((x-vaiPralaX)+(vaiPralaY-y)),50);
      //g2D.rotate(0.77);
      
      
      //g2D.drawImage(robo,x,y,null);
      //g2D.fillRect(x, y, 20, 80);
      g2D.dispose();
    }

    public void update(){
        vaiPralaX = mouse.xizinho - 32;
        vaiPralaY = mouse.ypsilinho - 2;

        //float iadmo =(x - vaiPralaX) + (y - vaiPralaY);
        //System.out.println(iadmo);
        if(mouse.click){
            angulo2 = (float) Math.atan2(vaiPralaY + y,vaiPralaX + x) ;
            angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
            xVelo = (float) ((7.5)*Math.cos(angulo));
            yVelo = (float) ((7.5)*Math.sin(angulo));
            x += xVelo;
            y += yVelo;
        }

        //if(x2 <= 0){
        //    x2 -=  10;
        //    if(x2 == -500){
        //        x2 = 0;
        //    }
        //}

        if(vx1 <= 0){
            vx1--;
            if(vx1 <= -500){
                vx1 = 0;
            }
        }

        if(vx2 <= 0){
            vx2 -= 2;
            if(vx2 <= -500){
                vx2 = 0;
            }
        }

        if(vx3 <= 0){
            vx3 -= 5;
            if(vx3 <= -500){
                vx3 = 0;
            }
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
