import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

public class Fundo {
    Image predios1;
    Image fundo1;
    Image fundo2;
    Image fundo3;

    //velocidade dos fundos (camadas)
    int vx1 = 0;
    int vx2 = 0;
    int vx3 = 0;
    int vx4 = 0;

    Fundo(){
         try {
            predios1 = ImageIO.read(getClass().getResourceAsStream("predios1.png"));
        } catch (IOException e) {
        }

        try {
            fundo1 = ImageIO.read(getClass().getResourceAsStream("background1.png"));
        } catch (IOException e) {
        }
        try {
            fundo2 = ImageIO.read(getClass().getResourceAsStream("background2.png"));
        } catch (IOException e) {
        }
        try {
            fundo3 = ImageIO.read(getClass().getResourceAsStream("background3.png"));
        } catch (IOException e) {
        }
    }

    public void paintBackground(Graphics2D g){
        
    g.drawImage(fundo1, vx1, 0, null);
    g.drawImage(fundo2, vx2, 0, null);
    g.drawImage(fundo3, vx3, 0, null);
    }

    public void update(){
        if(vx1 <= 0){
            vx1--;
            if(vx1 <= -700){
                vx1 = 0;
            }
        }

        if(vx2 <= 0){
            vx2 -= 2;
            if(vx2 <= -700){
                vx2 = 0;
            }
        }

        if(vx3 <= 0){
            vx3 -= 8;
            if(vx3 <= -700){
                vx3 = 0;
            }
        }
    }
    

       

}
