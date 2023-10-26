import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;


public class DeadScreen extends JPanel implements ActionListener{
    JButton botao = new JButton();
    JButton botao2 = new JButton();
    

    boolean reiniciar = false;

    DeadScreen(){

        this.setSize(1400, 900);
        this.setOpaque(false);
        this.setVisible(true);

        this.add(botao);
        this.add(botao2);

        botao.setBounds(450, 225, 500, 100);
        botao.addActionListener(this);
        botao.setText("Reiniciar");

        botao2.setBounds(450, 375, 500, 100);
        botao2.addActionListener(this);
        botao2.setText("Sair");

        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botao){
            reiniciar = true;

        }
        if(e.getSource()==botao2){
            System.exit(0);
        }
    }

    public void paint(Graphics2D g, GameScreen game){

        g.setFont(new Font("Igor", Font.PLAIN, 25));

        g.drawString("Fim de Jogo.", 350, 50);
        g.drawString("Seus pontos foram " + game.pontos, 350, 100);
        if(game.pontos >= 5000){
            g.drawString("Você fez mais de 5000 pontos! parabéns você ganhou um prêmio!", 350, 150);
        }
    }
}
