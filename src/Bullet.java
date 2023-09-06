import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Bullet {

    double angulo;
    float xBullet;
    float yBullet;
    float xVeloBullet;
    float yVeloBullet;

    Bullet(){


    }

    public void paintBullet(Graphics2D g){   
            g.fillRect((int)xBullet,(int)yBullet, 10, 5);
    }

    public void update(Player player, Keys mouse){

        if(mouse.click == 500){
            angulo = (float) Math.atan2(mouse.ypsilinho - player.y,mouse.xizinho - player.x);
            xVeloBullet = (float) (5*Math.cos(angulo));
            yVeloBullet = (float)(5*Math.sin(angulo));
            
            mouse.click = 0;
        }

        xBullet += xVeloBullet;
        yBullet += yVeloBullet;
    }
}
