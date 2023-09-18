import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;


public class Player {
    float x = 0;
    float y = 0;
    float vaiPralaX = 200;
    float vaiPralaY = 200;
    float angulo;
    float xVelo;
    float yVelo;
    double girar = 0;
    int posicaoAngle = 0;
    int tempo;
    Rectangle rectangle = new Rectangle((int)x + 32, (int)y +32, 64, 64);
    Keys mouse = new Keys();
    boolean oane = false;
    Enemy enemigo;
    int countdown = 0;
    boolean invencivel = false;


    // Bullet bullet = new Bullet();

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Angle> angulos = new ArrayList<Angle>();

    Image robo;
    Player(){
        try {
            robo = ImageIO.read(getClass().getResourceAsStream("/assets/Player.png"));
        } catch (IOException e) {
        }

    }

    public void paintPlayer(Graphics2D g){

        for(int  i = 0; i < bullets.size(); i++){
            bullets.get(i).paintBullet(g);
            //System.out.println(i);
        }
       g.rotate(girar, x+32, y+32);
       if(mouse.xizinho > x){
        girar+=0.075;
       } else {
        girar-=0.075;
       }
       
        g.drawImage(robo, (int)x,(int)y, 64,64, null);
        g.draw(rectangle);

        
        g.rotate(-girar, x+32, y+32);

        
    }

    public void update(Keys mouse, Enemy enemigo){

        this.mouse = mouse;
        this.enemigo = enemigo;

        if(mouse.clicked){
            angulos.add(new Angle(mouse, this));
            bullets.add(new Bullet((int) x, (int) y, mouse, angulos.get(posicaoAngle)));
            posicaoAngle++;
            mouse.clicked = false;
            //oane = true;
        }

        for(int i=0;i<angulos.size();i++){
            //System.out.println(angulos.get(i).angulo);
        }

        for(int  i = 0; i < bullets.size(); i++){
            bullets.get(i).update();
        }

        if(bullets.size() >= 1){
            countdown++;
            if(countdown >= 120){
                for(int i = 0; i < bullets.size(); i++){
                    bullets.remove(i);
                    angulos.remove(i);
                    posicaoAngle--;
                }
                countdown = 0;
            }
        }

        rectangle = new Rectangle((int)x, (int)y, 64, 64);
        vaiPralaX = mouse.xizinho - 32;
        vaiPralaY = mouse.ypsilinho - 32;
        if(mouse.moved){
            angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
            xVelo = (float) ((4)*Math.cos(angulo));
            yVelo = (float) ((4)*Math.sin(angulo));
            if(rectangle.intersects(mouse.rectangle)){
               xVelo =0;
               yVelo =0;
               //System.out.println("DESVIA O Menô");
            }
            x += xVelo;
            y += yVelo;
        }

        if(invencivel == true){
            tempo++;
            if(tempo>=30){
                invencivel=false;
                tempo = 0;
            }
        }


        for(int i=0;i<bullets.size();i++){
            if((bullets.get(i).rectangle.intersects(enemigo.rectangle)) && invencivel==false){
                invencivel=true;
                //System.out.println("ATIREI PORRA MORRE LOGO KARALHO");
                enemigo.life--;
                System.out.println(enemigo.life);
            }
        }

        

        //System.out.println(x+" "+y);
        //System.out.println(xVelo+" "+yVelo );
        //System.out.println(vaiPralaX+" "+vaiPralaY );
    }
}
