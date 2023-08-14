import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class PanelGame extends JFrame  implements KeyListener, MouseListener, MouseMotionListener{

    int i;

    JLabel quadradoAmbulante;

    public PanelGame(){
        this.setSize(700, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setTitle("Olá Mundo, robôs querem pegar lixo");
        this.setLayout(null);
        this.addKeyListener(this);
        this.isDoubleBuffered();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLocationRelativeTo(null);

        
        quadradoAmbulante = new JLabel();
        quadradoAmbulante.setBackground(Color.CYAN);
        quadradoAmbulante.setBounds(100, 50, 150,150);
        quadradoAmbulante.setOpaque(true);

        

        this.add(quadradoAmbulante);
    }




    public void keyTyped(KeyEvent e){
        switch(e.getKeyChar()){
            case 'a': quadradoAmbulante.setLocation(quadradoAmbulante.getX()-20, quadradoAmbulante.getY());
                break;
            case 'w': quadradoAmbulante.setLocation(quadradoAmbulante.getX(), quadradoAmbulante.getY()-20);
                break;
            case 's': quadradoAmbulante.setLocation(quadradoAmbulante.getX(), quadradoAmbulante.getY()+20);
                break;
            case 'd': quadradoAmbulante.setLocation(quadradoAmbulante.getX()+20, quadradoAmbulante.getY());
                break;
        }
    }

    public void keyPressed(KeyEvent e){//sobrescrevendo
    }

    public void keyReleased(KeyEvent e){
    }




    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click");
    }




    @Override
    public void mousePressed(MouseEvent e) {
    }




    @Override
    public void mouseReleased(MouseEvent e) {
    }




    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }




    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }




    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }




    @Override
    public void mouseMoved(MouseEvent e) {
        for(i = 0; i <= 300; i++){

            //System.out.println(i);

        }

        if(i == 300){
            i = 0;
        }

        quadradoAmbulante.setLocation(e.getX() - 75, e.getY() - 75);
        System.out.println("cu");
    }

}
