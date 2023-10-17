import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Bullet {
    float xBullet;
    float yBullet;
    float xVeloBullet;
    float yVeloBullet;

    int x;
    int y;

    Mouse mouse;
    Angle Angulo;
    Rectangle rectangle = new Rectangle((int)xBullet, (int)yBullet, 10, 5);

    Bullet(int x, int y, Mouse mouse, Angle Angulo){
        this.Angulo = Angulo;
        this.mouse = mouse;
        this.x = x;
        this.y = y;

    }

    public void paintBullet(Graphics2D g){   
            g.setColor(Color.gray);
            g.fillRect((int)xBullet,(int)yBullet, 20, 10);
            //g.draw(rectangle);
    }

    public void update(){
        rectangle = new Rectangle((int)xBullet, (int)yBullet, 20, 10);        
            xBullet = x + 32;
            yBullet = y + 32;
            xVeloBullet = (float) (15*Math.cos(Angulo.angulo));
            yVeloBullet = (float)(15*Math.sin(Angulo.angulo));
        x += xVeloBullet;
        y += yVeloBullet;

    }
}
