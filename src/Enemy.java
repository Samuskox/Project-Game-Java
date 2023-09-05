import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Enemy {
    float xEnemy = 700;
    float yEnemy = 10;
    Image enemy;
    Rectangle rectangle = new Rectangle((int)xEnemy, (int)yEnemy, 64, 64);
    float angulo;
    float yveloEnemy;

    Enemy(){
        try {
            enemy = ImageIO.read(getClass().getResourceAsStream("inimigo.png"));
        } catch (IOException e) {
        }
    }

    public void drawEnemy(Graphics2D g){
        g.drawImage(enemy, (int)xEnemy, (int)yEnemy, 64, 64, null);
        g.setColor(Color.BLUE);
        g.draw(rectangle);
    }

    public void update(Player player){
        rectangle = new Rectangle((int)xEnemy, (int)yEnemy, 64, 64);
        if(xEnemy > -100){
            xEnemy-= 10;
        }

        if(xEnemy <= -100){
            xEnemy = 700;
        }
        angulo = (float)(Math.atan2(player.y - yEnemy,player.x - xEnemy));
        yveloEnemy = (float) ((3)*Math.sin(angulo));
        
        yEnemy += yveloEnemy;





    }
}
