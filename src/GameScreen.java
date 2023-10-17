import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GameScreen extends JPanel{

    Random random = new Random();
    Mouse mouse = new Mouse();
   

    float Yenemy;
    float Xenemy;
    int variation = 1;
   
    Background fundo = new Background();
    Player player = new Player();

    ArrayList<Enemy> inimigos = new ArrayList<Enemy>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Angle> angulos = new ArrayList<Angle>();
    ArrayList<Item> item = new ArrayList<Item>();

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
    int num;

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

        for(int i=0;i<item.size();i++){
            item.get(i).paint(g2D);
        }
        g2D.setColor(new Color(131, 137, 222));
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
                Xenemy = random.nextFloat(1400)+1400;
                System.out.println(Xenemy);
                variation = random.nextInt(3)+1;
                inimigos.add(new Enemy(variation, Yenemy, Xenemy));
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
            num = random.nextInt(5)+1;
            //System.out.println("oi: "+num);
            if(num == 5){
                item.add(new Item((int)inimigos.get(i).xEnemy, (int)inimigos.get(i).yEnemy));
            }
            inimigos.remove(i);
        }
       }

       for(int i=0;i<item.size();i++){
        item.get(i).update();
        if(player.LifeX < 100){
            if(item.get(i).rectangle.intersects(player.rectangle)){
                player.LifeX += 10;
                item.remove(i);
            }
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
        inimigos.add(new Enemy(2, 800, 1800));
        inimigos.add(new Enemy(1, 600,1950));
        inimigos.add(new Enemy(2, 400, 1960));
        inimigos.add(new Enemy(3, 300, 1900));
        inimigos.add(new Enemy(1, 100, 1500));
    }

}
