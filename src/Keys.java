import org.w3c.dom.events.MouseEvent;
import java.awt.event.*;

public class Keys implements MouseListener, MouseMotionListener{

    float xizinho;
    float ypsilinho;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }




    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }




    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }




    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        System.out.println("olá");
    }




    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }




    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {

    }




    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        xizinho = e.getX();
        ypsilinho = e.getY();

        System.out.println("TO MOVENDO AQUI PORA: X: "+xizinho+" Y: "+ypsilinho);
    }



}
