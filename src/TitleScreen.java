import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.AncestorListener;
import javax.swing.event.MouseInputListener;
import java.awt.Graphics2D;
import org.w3c.dom.events.MouseEvent;

public class TitleScreen extends JPanel implements ActionListener{
    boolean aparecer = true;
    JButton botao;

    TitleScreen(){
        botao = new JButton();
        botao.setBackground(Color.white);
        botao.setBounds(400, 350, 500, 100);
        botao.addActionListener(this);
        botao.setText("Iniciar");


        this.setBounds(0, 0, 1400, 900);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        this.setOpaque(true);
        this.add(botao);
    }

    

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botao){
            aparecer=false;
            this.setVisible(aparecer);
        }
    }

}
