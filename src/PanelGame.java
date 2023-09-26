import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class PanelGame extends JPanel implements Runnable{

    Random random = new Random();

    GameScreen gameScreen = new GameScreen();

    float Yenemy;
    int variation = 1;
    Mouse mouse = new Mouse();
    Background fundo = new Background();
    Player player = new Player();

    ArrayList<Enemy> inimigos = new ArrayList<Enemy>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Angle> angulos = new ArrayList<Angle>();
    int posicaoAngle = 0;
    int countdown = 0;

    boolean powPowLiberado = true;
    int bulletDelay = 0;
    boolean run = true;
    int tempoDeJogo = 0;
    int segundos = 0;
    int tempoRenderEnemy = 0;
    int tempoInvencivel =0;

    FrameGame cosinha = new FrameGame();

    PanelGame(){
        this.setSize(1400,900);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        //this.add(cringePanel);
        //cringePanel.setVisible(true);
        this.add(gameScreen);
        this.setVisible(true);
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
        mouse.pintar(g2D);
        player.paintPlayer(g2D);

        for(int i=0; i<inimigos.size();i++){
            inimigos.get(i).drawEnemy(g2D);
        }    

        for(int  i = 0; i < bullets.size(); i++){
            bullets.get(i).paintBullet(g2D);
            //System.out.println(i);
        }

        g2D.dispose();
    }

    public void update(){
        cosinha.setVisible(false);
        fundo.update();
        player.update(mouse);

        /* renderização de inimigos */
            tempoRenderEnemy++;
            if(tempoRenderEnemy >= 120){
                Yenemy = random.nextFloat(800)+1;
                variation = random.nextInt(3)+1;
                inimigos.add(new Enemy(variation, Yenemy));
                // System.out.println(variation);
                tempoRenderEnemy = 0;
            }
            for(int  i = 0; i < inimigos.size(); i++){
                inimigos.get(i).update(player);
            }

            /* ATIRAR + RENDERIZAÇÂO DAS BALAS*/
            if(mouse.clicked && powPowLiberado){
                angulos.add(new Angle(mouse, player));
                bullets.add(new Bullet((int) player.x, (int) player.y, mouse, angulos.get(posicaoAngle)));
                posicaoAngle++;
                mouse.clicked = false;
                powPowLiberado = false;

            }
            if(powPowLiberado == false){
                bulletDelay++;
                if(bulletDelay >=10){
                    powPowLiberado = true;
                    bulletDelay =0;
                }
            }
            for(int  i = 0; i < bullets.size(); i++){
                bullets.get(i).update();
            }
            if(bullets.size() >= 1){
                countdown++;
                if(countdown >= 120){
                    for(int i = 0; i < bullets.size(); i++){
                        bullets.remove(i);
                        angulos.remove(i);
                        posicaoAngle--;
                    }
                    countdown = 0;
                }
            }

            


            /* INVENCIBILIDADE */
        if(tempoInvencivel == 0 ){
            for(int i=0;i<inimigos.size();i++){
                if(player.rectangle.intersects(inimigos.get(i).rectangle)){
                    System.out.println("kRLPORRA tomei danonhinho");
                   tempoInvencivel++; 
                }
            }
        } else {
            tempoInvencivel++;
            if(tempoInvencivel >= 60){
                tempoInvencivel = 0;
            }
        }


        /* INIMIGA PA VALA */
       for(int i=0;i<inimigos.size();i++){
        if(inimigos.get(i).vala == true){
            inimigos.remove(i);
        }
       }

        //System.out.println(tempoInvencivel);
        tempoDeJogo++;
        if(tempoDeJogo == 60){
            tempoDeJogo = 0;
            segundos++;
            //System.exit(0);
            //System.out.println(segundos);
            }
        


    }
}
