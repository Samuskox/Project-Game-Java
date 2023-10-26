import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Image;

public class TitleScreen extends JPanel implements ActionListener{
    boolean aparecer = true;
    JButton botao;
    JButton botao2;
    Image image;
    

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
        botao2.setText("Sair");
        botao.setVisible(true);
        botao2.setVisible(true);

        this.setBounds(0, 0, 1400, 900);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setOpaque(true);
        this.add(botao);
        this.add(botao2);


         try {
            image = ImageIO.read(getClass().getResourceAsStream("/assets/LOGO.png"));
        } catch (IOException e) {
        }
        this.setLayout(null);
    }

    // public void paint(Graphics g){
    //     super.paintComponent(g);
    //     g.drawImage(image, 0,0, null);
    // }

    public void draw(Graphics2D g2){
        g2.drawImage(image, 100, 0, null);
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
