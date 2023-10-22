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
    JButton botao2;
    

    TitleScreen(){
        botao = new JButton();
        botao.setBackground(Color.white);
        botao.setBounds(400, 350, 500, 100);
        botao.addActionListener(this);
        botao.setText("Iniciar");

        botao2 = new JButton();
        botao2.setBackground(Color.white);
        botao2.setBounds(400, 500, 500, 100);
        botao2.addActionListener(this);
        botao2.setText("fechar");

        this.setBounds(0, 0, 1400, 900);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setOpaque(true);
        this.add(botao);
        this.add(botao2);
    }

    

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botao){
            aparecer=false;
            this.setVisible(aparecer);
        }
        if(e.getSource() == botao2){
            System.exit(0);
        }
    }

}
