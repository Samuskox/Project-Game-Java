import java.awt.Color;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class App{
    public static void main(String[] args){
        FrameGame window = new FrameGame();
        PanelGame painel = new PanelGame();
        //TitleScreen menu = new TitleScreen();
        window.add(painel);
        painel.run();
        




    }

   


}
