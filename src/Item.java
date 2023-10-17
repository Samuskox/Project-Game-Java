import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Item {
    int x;
    int y;
    int tempo;
    Rectangle rectangle = new Rectangle((int)x - 50, (int)y - 50, 50, 50);
    Image RaioCura;

    Item(int x, int y){
        this.x = x;
        this.y = y;

        try {
            RaioCura = ImageIO.read(getClass().getResourceAsStream("/assets/RaioCura.png"));
        } catch (IOException e) {
        }
    }


    public void paint(Graphics2D g){
        //g.draw(rectangle);
        g.drawImage(RaioCura, x, y, 50,50,null);
    }

    public void update(){
        rectangle = new Rectangle((int)x, (int)y, 50, 50);
        y++;
        x--;
    }
}
