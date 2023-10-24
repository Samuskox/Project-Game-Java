import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.awt.Rectangle;


public class Player {
    float acelerarX = 5;
    float acelerarY = 5;
    float x = 0;
    float y = 0;
    float vaiPralaX = 200;
    float vaiPralaY = 200;
    float angulo;
    float xVelo;
    float yVelo;
    double girar = 0;
    int tempo;
    Rectangle rectangle = new Rectangle((int)x + 32, (int)y +32, 64, 64);
    Mouse mouse = new Mouse();
    Tecla teclas = new Tecla();
    boolean invencivel = false;
    int timerDash;
    boolean dash;
    //FrameGame TelaTecla2 = new FrameGame();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Angle> angulos = new ArrayList<Angle>();

    int lifeX = 100;
    int combustivel = 100;
    boolean tirarCombustivel = false;

    boolean modoRapido = false;
    int modoRapidoCount = 0;
    

    Image HUDrapido;
    Image robo;
    Player(){
        try {
            robo = ImageIO.read(getClass().getResourceAsStream("/assets/Player.png"));
        } catch (IOException e) {
        }

        try {
            HUDrapido = ImageIO.read(getClass().getResourceAsStream("/assets/HUDRAPIDO.png"));
        } catch (IOException e) {
        }
        //TelaTecla2.setVisible(false);
    }

    public void paintPlayer(Graphics2D g){
       g.rotate(girar, x+32, y+32);
       if(mouse.xizinho > x){
        girar+=0.075;
       } else {
        girar-=0.075;
       }
        g.drawImage(robo, (int)x,(int)y, 64,64, null);
        //g.draw(rectangle);
        g.rotate(-girar, x+32, y+32);
        if(mouse.xizinho > x){
            g.rotate(0.075, x + 32, y + 32);
           } else {
            g.rotate(-0.075, x + 32, y + 32);
        }

        /* HUD DA VIDA */
        g.setColor(Color.GRAY);
        g.fillRoundRect(10,10,300,30, 30, 30);
        g.setColor(new Color(168, 29, 29));
        g.fillRoundRect(10,10,lifeX*3,30, 30, 30);
        g.setColor(new Color(211, 41, 41));
        g.fillRoundRect(10,15,lifeX*3,20, 30, 30);
        g.setColor(new Color(249, 79, 79));
        g.fillRoundRect(10,20,lifeX*3,10, 30, 30);
        g.setColor(Color.BLACK);
        g.drawRoundRect(10, 10, 300, 30, 30, 30);
        g.drawRoundRect(9, 10, 300, 30, 30, 30);

        /* HUD DO COMBUSTIVEL */

        g.setColor(Color.gray);
        g.fillRoundRect(10,50,300,30, 30, 30);
        //g.drawRect(10, 70, 200,50);
        g.setColor(new Color(24,130,180));
        g.fillRoundRect(10,50,combustivel*3,30, 30, 30);
        g.setColor(new Color(38,173,236));
        g.fillRoundRect(10,55,combustivel*3,20, 30, 30);
        g.setColor(new Color(81,200,255));
        g.fillRoundRect(10,60,combustivel*3,10, 30, 30);
        g.setColor(Color.BLACK);
        g.drawRoundRect(10, 50, 300, 30, 30, 30);
        g.drawRoundRect(9, 50, 300, 30, 30, 30);

        /* HUD RAPIDEZ */

        if(modoRapido){
            g.drawImage(HUDrapido, 320, 3, 100, 90, null);
            
        }

        //g.fillRect(10, 70, combustivel*2, 50);
    }

    public void update(Mouse mouse, Tecla tecla){
        this.mouse = mouse;
        this.teclas = tecla;
        rectangle = new Rectangle((int)x, (int)y, 64, 64);

        for(int i=0;i<angulos.size();i++){
            //System.out.println(angulos.get(i).angulo);
        }
        



        /* VELOCIDADE DO PERSONAGEM + MOVIMENTAÇÂO COM MOUSE */   
        //vaiPralaX = mouse.xizinho - 32;
        //vaiPralaY = mouse.ypsilinho - 32;
        //if(mouse.moved){
        //    angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
        //    xVelo = (float) ((acelerarX)*Math.cos(angulo));
        //    yVelo = (float) ((acelerarY)*Math.sin(angulo));
        //    if(rectangle.intersects(mouse.rectangle)){
        //       xVelo =0;
        //       yVelo =0;
        //    }
        //    x += xVelo;
        //    y += yVelo;
        //}


        /*VELOCIDADE DO PERSONAGEM + MOVIMENTAÇÃO COM TECLAS */
        if(tecla.up){
            y -= acelerarY; 
        }
        if(tecla.left){
            x -= acelerarX; 
        }
        if(tecla.down){
            y += acelerarY; 
        }
        if(tecla.right){
            x += acelerarX;
        }

        /* limites do mapa */
        if(y < 0){
            y+=acelerarY;
            tecla.up = false;
        }
        if(x < 0){
            x+=acelerarX;
            tecla.left = false;
        }
        if(y > 900){
            y-=acelerarY;
            tecla.down = false;
        }
        if(x > 1400){
            x -= acelerarX;
            tecla.right = false;
        }

            /* HABILIDADE DASH DO PERSONAGEM */
        if(tecla.space == true && combustivel >= 30 && dash == false){
            if(timerDash == 0){
                combustivel -= 30;
            }
            timerDash++;
            acelerarX = 18;
            acelerarY = 18;
            dash = true;
            tecla.space = false;
            
        }
        if(dash == true){
            timerDash++;
            
        }
        if(timerDash == 20){
                acelerarX = 5;
                acelerarY = 5;
                timerDash = 0;
                dash = false;
            }
        

        /* cooldown de dano de inimigo em relação ao persogangem */

        if(invencivel == true){
            tempo++;
            if(tempo>=30){
                invencivel=false;
                tempo = 0;
            }
        }
        //System.out.println(x+" "+y);
        //System.out.println(xVelo+" "+yVelo );
        //System.out.println(vaiPralaX+" "+vaiPralaY );
    }
}
