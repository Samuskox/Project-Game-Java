import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable{

    Keys mouse = new Keys();
    Fundo fundo = new Fundo();
    Enemy inimigo = new Enemy();
    Player player = new Player();
    //ArrayList<Enemy> listinha = new ArrayList<Enemy>();
    boolean run = true;
    int tempoDeJogo = 0;
    int segundos = 0;

    PanelGame(){
        this.setSize(700,500);
        
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
        while(run){
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }
            repaint();
            update();
        }

     
    }

//aqui vai pegar todos os paint e tacar na tela
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        fundo.paintBackground(g2D);
        inimigo.drawEnemy(g2D);
        mouse.pintar(g2D);
        player.paintPlayer(g2D);

      g2D.setColor(Color.pink);
     
      g2D.dispose();
    }

    public void update(){

        
        //if(timer == 0 ){
            //if(rectangle.intersects(inimigo.rectangle)){
           //     System.out.println("kRLPORRA tomei danonhinho");
        //       timer++;
           // }
        //    
        //} else {
        //    timer++;
        //    if(timer >= 60){
        //        timer = 0;
        //    }
        //}

        

        fundo.update();
        inimigo.update(player);
        player.update(mouse);
       

        //System.out.println(tempoDeJogo);
        tempoDeJogo++;
        if(tempoDeJogo == 60){
            tempoDeJogo = 0;
            segundos++;
            //System.exit(0);
            //System.out.println(segundos);


        }
    }


}
