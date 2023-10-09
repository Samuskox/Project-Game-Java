import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GameScreen extends JPanel{

    Random random = new Random();
    Mouse mouse = new Mouse();
   

    float Yenemy;
    int variation = 1;
   
    Background fundo = new Background();
    Player player = new Player();

    ArrayList<Enemy> inimigos = new ArrayList<Enemy>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Angle> angulos = new ArrayList<Angle>();
    int posicaoAngle = 0;
    int countdown = 0;

    boolean powPowLiberado = true;
    int bulletDelay = 0;
    
    int tempoDeJogo = 0;
    int segundos = 0;
    int tempoRenderEnemy = 0;
    int tempoInvencivel =0;

    GameScreen(){
        this.setSize(1400, 900);
        // this.setBackground(Color.PINK);
        // this.setOpaque(true);
    }

    public void paint(Graphics2D g2D){
        
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
    }

    public void update(PanelGame panelGame){
        //System.out.println("PAO COM MORTADELA");

        this.mouse = panelGame.mouse;

        fundo.update();
        player.update(panelGame.mouse);

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


        /* INIMIGO DESAPARECER*/
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
