public class Angle {
    float angulo;
    float xVeloBullet;
    float yVeloBullet;
    Angle(Keys mouse, Player player){
        angulo = (float) Math.atan2(mouse.ypsilinho - player.y,mouse.xizinho - player.x);
        xVeloBullet = (float) (15*Math.cos(angulo));
        yVeloBullet = (float)(15*Math.sin(angulo));
        //System.out.println(angulo);
    }
}
