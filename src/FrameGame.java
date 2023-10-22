import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class FrameGame extends JFrame{
    Tecla teclas = new Tecla();

    public FrameGame(){
        this.setSize(1400, 900);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        // this.setTitle("Olá Mundo, robôs querem pegar lixo");
        this.setTitle("Ultimate Mega Trash Robot: Revolution");
        this.setLocationRelativeTo(null);
        this.addKeyListener(teclas);
        this.setResizable(false);
        //this.setLayout(null);
    }
}
