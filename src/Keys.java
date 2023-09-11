import org.w3c.dom.events.MouseEvent;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics2D;


public class Keys implements MouseListener, MouseMotionListener, KeyListener{

    float xizinho;
    float ypsilinho;
    boolean moved;
    boolean clicked;
    int click;

    Rectangle rectangle = new Rectangle((int)xizinho, (int)ypsilinho, 3, 3);

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        click = e.MOUSE_CLICKED;
        clicked = true;
        //System.out.println(click);
        //System.out.println("X: "+xizinho+"Y:"+ypsilinho);

       
    }




    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        
    }




    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        clicked = false;
        //System.out.println(clicked);
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
        moved = true;
        //System.out.println("TO MOVENDO AQUI PORA: X: "+xizinho+" Y: "+ypsilinho);
    }




    @Override
    public void keyTyped(KeyEvent e) {
        
    }




    @Override
    public void keyPressed(KeyEvent e) {
        
    }




    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    public void pintar(Graphics2D a){
        rectangle = new Rectangle((int)xizinho - 3, (int)ypsilinho - 3, 6, 6);
        a.draw(rectangle);
    }



}
