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
    //FrameGame TelaTecla2 = new FrameGame();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Angle> angulos = new ArrayList<Angle>();

    int LifeX = 100;


    Image robo;
    Player(){
        try {
            robo = ImageIO.read(getClass().getResourceAsStream("/assets/Player.png"));
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
        g.setColor(Color.RED);
        g.fillRect(10, 10, 100*2, 50);
        g.setColor(Color.BLUE);
        g.fillRect(10, 10, LifeX*2, 50);
    }

    public void update(Mouse mouse, Tecla tecla){
        this.mouse = mouse;
        this.teclas = tecla;
        rectangle = new Rectangle((int)x, (int)y, 64, 64);

        for(int i=0;i<angulos.size();i++){
            //System.out.println(angulos.get(i).angulo);
        }



        /* VELOCIDADE DO PERSONAGEM + MOVIMENTAÇÂO COM MOUSE */   
        vaiPralaX = mouse.xizinho - 32;
        vaiPralaY = mouse.ypsilinho - 32;
        if(mouse.moved){
            angulo = (float)Math.atan2(vaiPralaY - y, vaiPralaX - x);
            xVelo = (float) ((acelerarX)*Math.cos(angulo));
            yVelo = (float) ((acelerarY)*Math.sin(angulo));
            if(rectangle.intersects(mouse.rectangle)){
               xVelo =0;
               yVelo =0;
               //System.out.println("DESVIA O Menô");
            }
            x += xVelo;
            y += yVelo;
        }

            /* HABILIDADE DASH DO PERSONAGEM */
        if(tecla.space == true){
            timerDash++;
            System.out.println(timerDash);
            acelerarX = 22;
            acelerarY = 22;
            if(timerDash == 15){
                acelerarX = 5;
                acelerarY = 5;
                timerDash = 0;
                System.out.println(acelerarX+" "+acelerarY);
                tecla.space = false;
            }
        }
        

        /* cooldown de dano de inimigo em relação ao persogangem */

        if(invencivel == true){
            tempo++;
            if(tempo>=30){
                invencivel=false;
                tempo = 0;
            }
        }


        //for(int i=0;i<bullets.size();i++){
        //    if((bullets.get(i).rectangle.intersects(enemigo.rectangle)) && invencivel==false){
        //        invencivel=true;
        //        //System.out.println("ATIREI PORRA MORRE LOGO KARALHO");
        //        enemigo.life--;
        //        System.out.println(enemigo.life);
        //    }
        //}

        

        //System.out.println(x+" "+y);
        //System.out.println(xVelo+" "+yVelo );
        //System.out.println(vaiPralaX+" "+vaiPralaY );
    }
}
