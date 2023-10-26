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
    int tempoRenderEnemyCoolDown = 0;
    int tempoInvencivel =0;

    int pontos;
    int num;
    int addpontos = 10;

    boolean fastGame = false;
    int countFastGame = 0;
    int quantidadeEnimigo = 1;

    boolean fimDeJogo = false;


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

        fundo.paintBackgroundPoeira(g2D);
        g2D.setColor(new Color(131, 137, 222));
        g2D.setFont(new Font("Igor", Font.PLAIN, 50));
        g2D.drawString("Pontos: "+pontos, 1000, 61);
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
                //System.out.println(Xenemy);
                variation = random.nextInt(3)+1;
                inimigos.add(new Enemy(variation, Yenemy, Xenemy));
                //EnemyWave();
                EnemysWaves(quantidadeEnimigo);
                //quantidadeEnimigo++;
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
                if(bulletDelay >=7){
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
                    player.lifeX -= 5;
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
                int num2 = random.nextInt(3)+1;
                item.add(new Item(inimigos.get(i).xEnemy, inimigos.get(i).yEnemy, num2));
            }
            inimigos.remove(i);
        }
       }

       /* item render + hitbox */
       for(int i=0;i<item.size();i++){
        item.get(i).update();
            if(item.get(i).rectangle.intersects(player.rectangle)){
                if(item.get(i).type == 1 && player.lifeX < 100){
                    player.lifeX += 10;
                } else if((item.get(i).type == 2) && (fastGame == false)){
                    fastGame = true;
                    player.modoRapido = true;
                    player.modoRapidoCount = 1000;
                } else if(item.get(i).type == 3 && player.combustivel < 100){
                    player.combustivel += 20;
                }
                item.remove(i);
        }
       }

       /* limite Combustivel */
       if(player.combustivel > 100){
            player.combustivel = 100;
        }

        /* remove item ao chegar embaixo da tela */

        for(int i=0;i<item.size();i++){
        if(item.get(i).y > 1000){
            item.remove(i);
        }
        }


       /* MODO DE JOGO RÁPIDO */

       if(fastGame){
            //System.out.println("ola ativei");
            countFastGame++;
            addpontos = 50;
            player.modoRapidoCount--;
            for(int i=0;i<inimigos.size();i++){
                inimigos.get(i).aceleraçaoX = 20;
            }
            fundo.vx1 = 4;
            fundo.vx2 = 6;
            fundo.vx3 = 12;
            fundo.vx4 = 44;

            for(int i=0;i<item.size();i++){
                item.get(i).aceleracaoX = 13;
                item.get(i).aceleracaoY = 5;
            }
       }

       if(countFastGame >= 960){
            fastGame = false;
            for(int i=0;i<inimigos.size();i++){
                inimigos.get(i).aceleraçaoX = 10;
            }
            fundo.vx1 = 1;
            fundo.vx2 = 2;
            fundo.vx3 = 8;
            fundo.vx4 = 30;

            for(int i=0;i<item.size();i++){
                item.get(i).aceleracaoX = 7;
                item.get(i).aceleracaoY = 3;
            }
            //System.out.println("ola desativei");
            countFastGame = 0;
            addpontos = 10;
            player.modoRapido = false;
       }

       /* CONTAGEM DE JOGO */

        //System.out.println(tempoInvencivel);
        tempoDeJogo++;
        if(tempoDeJogo == 60){
            tempoDeJogo = 0;
            segundos++;
            if(fastGame){
                pontos += 3;
            } else {
                pontos += 1;
            }
            
            tempoRenderEnemyCoolDown++;
            //System.exit(0);
            //System.out.println(segundos);
            }
        
        if(tempoRenderEnemyCoolDown == 10){
            quantidadeEnimigo++;
            tempoRenderEnemyCoolDown = 0;
        }

        /* MORTE */
        if(player.lifeX < 0){
            fimDeJogo = true;
            //System.exit(0);
        }
    }

    public void EnemyWave(){
        float y = random.nextFloat(800)+1;
        float x = random.nextFloat(1400)+1400;
        int v = random.nextInt(3)+1;
        inimigos.add(new Enemy(v, y, x));
        y = random.nextFloat(800)+1;
        x = random.nextFloat(1400)+1400;
        v = random.nextInt(3)+1;
        inimigos.add(new Enemy(v,y,x));
        y = random.nextFloat(800)+1;
        x = random.nextFloat(1400)+1400;
        v = random.nextInt(3)+1;
        inimigos.add(new Enemy(v,y,x));
        y = random.nextFloat(800)+1;
        x = random.nextFloat(1400)+1400;
        v = random.nextInt(3)+1;
        inimigos.add(new Enemy(v,y,x));
        y = random.nextFloat(800)+1;
        x = random.nextFloat(1400)+1400;
        v = random.nextInt(3)+1;
        inimigos.add(new Enemy(v,y,x));
    }

    public void EnemysWaves(int quant){
        for(int i=0; i<quant;i++){
            float y = random.nextFloat(800)+1;
            float x = random.nextFloat(1400)+1400;
            int v = random.nextInt(3)+1;
            inimigos.add(new Enemy(v,y,x));
        }
    }

}
