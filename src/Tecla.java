import java.awt.event.*;

public class Tecla implements KeyListener{
    Boolean space = false;
    boolean up = false;
    boolean left = false;
    boolean down = false;
    boolean right = false;
    @Override 
    public void keyTyped(KeyEvent e) {
        //int code = e.getKeyCode();
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
            //System.out.println(space);
        }
        //System.out.println(code);
        if(code == KeyEvent.VK_W){
            up = true;
            //System.out.println("up: "+up);
        }
        if(code == KeyEvent.VK_A){
            left = true;
            //System.out.println("left: "+left);
        }
        if(code == KeyEvent.VK_S){
            down = true;
            //System.out.println("down: "+down);
        }
        if(code == KeyEvent.VK_D){
            right = true;
            //System.out.println("right: "+right);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //if(code == KeyEvent.VK_SPACE){
        //    space = false;
        //    System.out.println(space);
        //}
        //System.out.println(code);
        if(code == KeyEvent.VK_W){
            up = false;
            //System.out.println("up: "+up);
        }
        if(code == KeyEvent.VK_A){
            left = false;
            //System.out.println("left: "+left);
        }
        if(code == KeyEvent.VK_S){
            down = false;
            //System.out.println("down: "+down);
        }
        if(code == KeyEvent.VK_D){
            right = false;
            //System.out.println("right: "+right);
        }
    }
    
}
