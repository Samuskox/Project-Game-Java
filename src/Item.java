import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Item {
    float x;
    float y;
    int tempo;
    Rectangle rectangle = new Rectangle((int)x - 50, (int)y - 50, 50, 50);
    Image Item;
    int type;
    Image RaioCombustivel;
    float aceleracaoX = 7;
    float aceleracaoY = 3;

    Item(float x, float y, int type){
        this.x = x;
        this.y = y;
        this.type = type;

        if(type == 1){
            try {
            Item = ImageIO.read(getClass().getResourceAsStream("/assets/Raio.png"));
        } catch (IOException e) {
            }
        } else if(type == 2){
            try {
            Item = ImageIO.read(getClass().getResourceAsStream("/assets/Rapidez.png"));
        } catch (IOException e) {
            }
        } else if(type == 3){
            try {
            Item = ImageIO.read(getClass().getResourceAsStream("/assets/Combustivel.png"));
        } catch (IOException e) {
            }
        }
        
    }


    public void paint(Graphics2D g){
        //g.draw(rectangle);
        g.drawImage(Item, (int)x, (int)y, 50,50,null);
    }

    public void update(){
        rectangle = new Rectangle((int)x, (int)y, 50, 50);
        y += aceleracaoY;
        x -= aceleracaoX;
    }
}
