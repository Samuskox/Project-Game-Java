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
    int angulo3;
    

    Image robo;
    Image predios1;
    Image fundo1;
    Image fundo2;
    Image fundo3;

    float arcAng;

    float samuel;


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
            predios1 = ImageIO.read(getClass().getResourceAsStream("predios1.png"));
        } catch (IOException e) {
        }

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
    //velocidade dos fundos (camadas)
    int vx1 = 0;
    int vx2 = 0;
    int vx3 = 0;
    int vx4 = 0;

    public void paint(Graphics g){

        

        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
     g2D.drawImage(fundo1, vx1, 0, null);
     g2D.drawImage(fundo2, vx2, 0, null);
     g2D.drawImage(fundo3, vx3, 0, null);
     //g2D.drawImage(predios1, vx4, 0,null);
    
     g2D.translate(vaiPralaX + 40, vaiPralaY +40);
      g2D.rotate(samuel); 
      //g2D.translate(x, y);
    
      g2D.setColor(Color.pink);
     g2D.fillRect(0, 0, 80, 80);

    // g2D.translate(x, y);
    // g2D.rotate(1);
    
    
      
    //g2D.rotate(arcAng);
      //g2D.fillArc(x, y, 80, 80, ,50);
      //g2D.fillOval(x, y, 40, 40);
      
      
    //   g2D.drawImage(robo,x+32,y+32,null);
      //g2D.fillRect(x, y, 20, 80);
      g2D.dispose();
    }

    public void update(){
        
        //arcAng = ((x-vaiPralaX)+(vaiPralaY-y));
        
        if(tempoDeJogo == 60){
            //  System.out.println(arcAng);
        }
       
        vaiPralaX = mouse.xizinho - 40;
        vaiPralaY = mouse.ypsilinho - 40;

        //float iadmo =(x - vaiPralaX) + (y - vaiPralaY);
       // System.out.println(iadmo);
         if(mouse.click){
             angulo2 = (float) Math.atan2(vaiPralaY + y,vaiPralaX + x) ;
             angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
             xVelo = (float) ((7.5)*Math.cos(angulo));
             yVelo = (float) ((7.5)*Math.sin(angulo));
             x += xVelo;
             y += yVelo;
         }

        samuel = (float) Math.atan2(vaiPralaY - 40, vaiPralaX - 40);

        
                
        if(vx1 <= 0){
            vx1--;
            if(vx1 <= -700){
                vx1 = 0;
            }
        }

        if(vx2 <= 0){
            vx2 -= 2;
            if(vx2 <= -700){
                vx2 = 0;
            }
        }

        if(vx3 <= 0){
            vx3 -= 8;
            if(vx3 <= -700){
                vx3 = 0;
            }
        }

        //if(vx4 <= 0){
        //    vx4 -= 5;
        //    if(vx4 <= -700){
        //        vx4 = 0;
        //    }
        //}




        //System.out.println(tempoDeJogo);
        tempoDeJogo++;
        if(tempoDeJogo > 60){
            tempoDeJogo = 0;
            segundos++;

            //System.out.println(segundos);


        }
    }


}
