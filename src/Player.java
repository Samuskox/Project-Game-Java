import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;


public class Player {
    float x = 0;
    float y = 0;
    float vaiPralaX = 200;
    float vaiPralaY = 200;
    float angulo;
    float xVelo;
    float yVelo;
    double girar = 0.5;
    int tempo;
    Rectangle rectangle = new Rectangle((int)x + 32, (int)y +32, 64, 64);
    Keys mouse = new Keys();

    Image robo;
    Player(){
        try {
            robo = ImageIO.read(getClass().getResourceAsStream("Player.png"));
        } catch (IOException e) {
        }

    }

    public void paintPlayer(Graphics2D g){
       //g.rotate(girar, x-32, y-32);
        g.drawImage(robo, (int)x,(int)y, 64,64, null);
        g.draw(rectangle);
    }

    public void update(Keys mouse){

        this.mouse = mouse;
        rectangle = new Rectangle((int)x, (int)y, 64, 64);
        vaiPralaX = mouse.xizinho - 32;
        vaiPralaY = mouse.ypsilinho - 32;
        if(mouse.moved){
            angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
            xVelo = (float) ((7.5)*Math.cos(angulo));
            yVelo = (float) ((7.5)*Math.sin(angulo));
            if(rectangle.intersects(mouse.rectangle)){
               xVelo =0;
               yVelo =0;
               
               //System.out.println("DESVIA O MênO");
            }
            x += xVelo;
            y += yVelo;
        }

        tempo++;
        if(tempo == 30){
            
            tempo = 0;
        }

        //System.out.println(x+" "+y);
        //System.out.println(xVelo+" "+yVelo );
        //System.out.println(vaiPralaX+" "+vaiPralaY );
    }
}
