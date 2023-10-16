import java.awt.event.*;

public class Tecla implements KeyListener{
    Boolean space = false;
    @Override 
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyCode();
        //System.out.println(space);
        //if(code == KeyEvent.VK_SPACE){
        //    space = true;
        //}
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            space = true;
            System.out.println(space);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //int code = e.getKeyCode();
        //if(code == KeyEvent.VK_SPACE){
        //    space = false;
        //    System.out.println(space);
        //}
    }
    
}
