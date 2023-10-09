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
        botao.setBackground(Color.GREEN);
        botao.setBounds(50, 50, 100, 50);
        botao.addActionListener(this);
        botao.setText("KKK joga logo");

        this.setBounds(0, 0, 1400, 900);
        //this.setSize(1400,900);
        this.setBackground(Color.blue);
        this.setVisible(true);
        this.setOpaque(true);
        this.add(botao);
    }

    public void update(){
        System.out.println("wtf");
    }

    public void paint(Graphics2D g2){
        // g2.setBackground(Color.BLUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botao){
            aparecer=true;
        }
    }

}
