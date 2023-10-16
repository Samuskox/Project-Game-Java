import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable{


    Mouse mouse = new Mouse();
    FrameGame cosinha = new FrameGame();
    GameScreen gameScreen = new GameScreen();
    boolean run =true;
    TitleScreen menu = new TitleScreen();

    PanelGame(){
        this.setSize(1400,900);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        cosinha.setVisible(false);
        this.add(menu);
        this.add(gameScreen);
        //gameScreen.setVisible(false);
        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
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
        if(menu.aparecer == false){
            gameScreen.paint(g2D);
            gameScreen.setVisible(true);
            g2D.dispose();
        } else {
            
        }

    }

    public void update(){
        if(menu.aparecer == false){
            gameScreen.update(this);
        } else {

        }
            
            

    }
}
