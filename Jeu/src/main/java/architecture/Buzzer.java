package architecture;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Buzzer extends JFrame {

    public JPanel pan = new JPanel();
    public JButton bouton = new JButton("Buzzer");

    public Buzzer(){
        this.setTitle("Roue de ta daronne");
        this.setSize(400, 100);
        this.setLocationRelativeTo(null);

        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();
        //Définition de sa couleur de fond
        pan.setBackground(Color.ORANGE);
        //On prévient notre JFrame que notre JPanel sera son content pane
        pan.add(bouton);
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
