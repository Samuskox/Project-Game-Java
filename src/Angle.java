public class Angle {
    float angulo;
    Angle(Mouse mouse, Player player){
        angulo = (float) Math.atan2(mouse.ypsilinho - player.y,mouse.xizinho - player.x);
        //System.out.println(angulo);
    }
}
