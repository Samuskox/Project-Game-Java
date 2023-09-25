import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable{

    Random random = new Random();


    int variation = 1;
    Mouse mouse = new Mouse();
    Background fundo = new Background();
    Player player = new Player();

    ArrayList<Enemy> inimigos = new ArrayList<Enemy>();
    boolean run = true;
    int tempoDeJogo = 0;
    int segundos = 0;
    int tempoRenderEnemy = 0;
    
    //CringePanel cringePanel = new CringePanel();
    FrameGame cosinha = new FrameGame();

    PanelGame(){
        this.setSize(1400,900);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        //this.add(cringePanel);
        //cringePanel.setVisible(true);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        //this.addKeyListener(tecla);
        //this.setFocusable(true);
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
        //inimigo.drawEnemy(g2D);
        mouse.pintar(g2D);
        for(int i=0; i<inimigos.size();i++){
            inimigos.get(i).drawEnemy(g2D);
        }    
        player.paintPlayer(g2D);
        g2D.dispose();
    }

    public void update(){
            //if(player.rectangle.intersects(inimigo.rectangle)){
            //System.out.println("tomei danonin papai");
            //}
            tempoRenderEnemy++;
            if(tempoRenderEnemy >= 120){
                variation = random.nextInt(3)+1;
                inimigos.add(new Enemy(variation));
                System.out.println(variation);
                tempoRenderEnemy = 0;
            }
            for(int  i = 0; i < inimigos.size(); i++){
                inimigos.get(i).update(player);
            }

            cosinha.setVisible(false);
            //System.out.println(cosinha.teclas.space);
            //System.out.println(cosinha.teclas.code);

        //if(timer == 0 ){
            //if(rectangle.intersects(inimigo.rectangle)){
           //     System.out.println("kRLPORRA tomei danonhinho");
        //       timer++; 
        //} else {
        //    timer++;
        //    if(timer >= 60){
        //        timer = 0;
        //    }
        //}

       //System.out.println(controladorBalas.numBullets.size());

        fundo.update();
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
