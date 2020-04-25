package architecture;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Buzzer extends JFrame implements ActionListener {

    public JPanel pan = new JPanel();
    public JButton bouton = new JButton("Buzzer");
    public static Integer boutonON = 0;

    public static int getBoutonON(){
        return boutonON;
    }

    public Buzzer(){
        this.setTitle("Roue de ta daronne");
        this.setSize(400, 100);
        this.setLocationRelativeTo(null);

        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();
        //Définition de sa couleur de fond
        pan.setBackground(Color.ORANGE);
        bouton.addActionListener(this);
        //On prévient notre JFrame que notre JPanel sera son content pane

        pan.add(bouton);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boutonON = 1;
        System.out.println("Vous avez buzzer");
    }
}
