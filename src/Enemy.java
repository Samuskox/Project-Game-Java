import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
    float xEnemy, yEnemy;
    Image enemy;

    Enemy(){
        try {
            enemy = ImageIO.read(getClass().getResourceAsStream("inimigo.png"));
        } catch (IOException e) {
        }
    }

    public void drawEnemy(Graphics2D g){
        g.drawImage(enemy, 0, 0, 64, 64, null);
    }
}
