import java.awt.Color;
import java.awt.Font;
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
    int tempoRenderEnemyCoolDown = 120;
    int tempoInvencivel =0;

    int pontos;


    GameScreen(){
        this.setSize(1400, 900);
        //this.setBackground(Color.PINK);
        this.setOpaque(true);
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
        }
        g2D.setFont(new Font("MV Boli", Font.PLAIN, 50));
        g2D.drawString("Pontos: "+pontos, 950, 61);
    }

    public void update(PanelGame panelGame){
        

        this.mouse = panelGame.mouse;

        fundo.update();
        player.update(panelGame.mouse, panelGame.cosinha.teclas);

        /* renderização de inimigos */
            tempoRenderEnemy++;
            if(tempoRenderEnemy >= 120){
                Yenemy = random.nextFloat(800)+1;
                variation = random.nextInt(3)+1;
                inimigos.add(new Enemy(variation, Yenemy));
                EnemyWave();
                tempoRenderEnemy = 0;
            }
            for(int  i = 0; i < inimigos.size(); i++){
                inimigos.get(i).update(player);
            }

            /* ATIRAR + RENDERIZAÇÂO DAS BALAS */
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

            /* dano de bala ao inimigo */
            //System.out.println(bullets.size());
            if(bullets.size() > 0){
                for(int i = 0; i<bullets.size() ;i++){
                    for(int j = 0; j<inimigos.size();j++){
                        if(bullets.get(i).rectangle.intersects(inimigos.get(j).rectangle)){
                            //System.out.println(inimigos.get(j).life);
                            inimigos.get(j).life--;
                            //.out.println(inimigos.get(j).life);
                            bullets.remove(i);
                            pontos += 10;
                            break;
                        }
                    }
                }
            }


            /* INVENCIBILIDADE */
        if(tempoInvencivel == 0 ){
            for(int i=0;i<inimigos.size();i++){
                if(player.rectangle.intersects(inimigos.get(i).rectangle) && tempoInvencivel == 0){
                    tempoInvencivel++;
                    //System.out.println("TOMEI DANONINHO");
                    player.LifeX -= 5;
                }
            }
        } else {
            tempoInvencivel++;
            if(tempoInvencivel >= 60){
                tempoInvencivel = 0;
            }
        }


        /* INIMIGO DESAPARECER */
       for(int i=0;i<inimigos.size();i++){
        if(inimigos.get(i).vala == true){
            inimigos.remove(i);
        }
        if(inimigos.get(i).life <= 0){
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
        if(segundos%10 == 0){
            tempoRenderEnemyCoolDown -= 10;
        }
        
    }

    public void EnemyWave(){
        inimigos.add(new Enemy(2, 800));
        inimigos.add(new Enemy(1, 600));
        inimigos.add(new Enemy(2, 400));
        inimigos.add(new Enemy(3, 300));
        inimigos.add(new Enemy(1, 100));
    }

}
