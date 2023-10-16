import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Enemy {
    float xEnemy = 1500;
    float yEnemy;
    Image enemy;
    Rectangle rectangle = new Rectangle((int)xEnemy, (int)yEnemy, 64, 64);
    float angulo;
    float yveloEnemy;
    int life = 1;
    int variacao;
    boolean vala = false;

    Enemy(int variacao, float yEnemy){
        this.yEnemy = yEnemy;
        //System.out.println(yEnemy);
        if(variacao == 1){
            try {
                enemy = ImageIO.read(getClass().getResourceAsStream("/assets/inimigo.png"));
            } catch (IOException e) {
            }
        } else if(variacao == 2){
            try {
                enemy = ImageIO.read(getClass().getResourceAsStream("/assets/inimigo1.png"));
            } catch (IOException e) {
            }
        } else if(variacao == 3){
            try {
                enemy = ImageIO.read(getClass().getResourceAsStream("/assets/inimigo2.png"));
            } catch (IOException e) {
            } 
        }
       
    }

    public void drawEnemy(Graphics2D g){
        g.drawImage(enemy, (int)xEnemy, (int)yEnemy, 64, 64, null);
        g.setColor(Color.BLUE);
        //g.draw(rectangle);
    }

    public void update(Player player){
        rectangle = new Rectangle((int)xEnemy, (int)yEnemy, 64, 64);
        if(xEnemy > -100){
            xEnemy-= 10;
        }
        if(xEnemy <= -100){
            xEnemy = 1500;
            vala = true;
        }
        angulo = (float)(Math.atan2(player.y - yEnemy,player.x - xEnemy));
        yveloEnemy = (float) ((3)*Math.sin(angulo));
        
        yEnemy += yveloEnemy;


    }
}
