

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

public class Background {
    Image predios1;
    Image fundo1;
    Image fundo2;
    Image fundo3;

    //velocidade dos fundos (camadas)
    int vx1 = 1;
    int vx2 = 2;
    int vx3 = 8;
    int vx4 = 30;

    int x1 = 0;
    int x2 = 0;
    int x3 = 0;
    int x4 = 0;
    

    Background(){
         try {
            predios1 = ImageIO.read(getClass().getResourceAsStream("/assets/predios1.png"));
        } catch (IOException e) {
        }

        try {
            fundo1 = ImageIO.read(getClass().getResourceAsStream("/assets/background1.png"));
        } catch (IOException e) {
        }
        try {
            fundo2 = ImageIO.read(getClass().getResourceAsStream("/assets/background2.png"));
        } catch (IOException e) {
        }
        try {
            fundo3 = ImageIO.read(getClass().getResourceAsStream("/assets/background3.png"));
        } catch (IOException e) {
        }
    }

    public void paintBackground(Graphics2D g){
        
    g.drawImage(fundo1, x1, 0, null);
    g.drawImage(fundo2, x2, 0, null);
    g.drawImage(fundo3, x3, 0, null);
    g.drawImage(predios1, x4, 0, null);
    }

    public void update(){
        if(x1 <= 0){
            x1-= vx1;
            if(x1 <= -700){
                x1 = 0;
            }
        }

        if(x2 <= 0){
            x2 -= vx2;
            if(x2 <= -700){
                x2 = 0;
            }
        }

        if(x3 <= 0){
            x3 -= vx3;
            if(x3 <= -700){
                x3 = 0;
            }
        }

        if(x4 <= 0){
            x4 -= vx4;
            if(x4 <= -700){
                x4 = 0;
            }
        }
    }
    

       

}
