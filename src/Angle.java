public class Angle {
    float angulo;
    Angle(Mouse mouse, Player player){
        angulo = (float) Math.atan2((mouse.ypsilinho - 32) - player.y,(mouse.xizinho - 32) - player.x);
    }
}
