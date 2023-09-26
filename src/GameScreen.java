import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameScreen extends JPanel{
    GameScreen(){
        this.setSize(1400, 900);
        this.setBackground(Color.PINK);
        this.setOpaque(true);
    }

    public void paint(Graphics2D g2){

    }

    public void update(){
        System.out.println("PAO COM MORTADELA");
    }

}
