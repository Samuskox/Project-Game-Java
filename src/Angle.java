public class Angle {
    float angulo;
    float xVeloBullet;
    float yVeloBullet;
    Angle(Keys mouse, Player player){
        angulo = (float) Math.atan2(mouse.ypsilinho - player.y,mouse.xizinho - player.x);
        //System.out.println(angulo);
    }
}
