package architecture;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

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

        JLabel label = new JLabel(" Il faut buzzer si vous avez le mot");




        pan.add(label);
        pan.add(bouton);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boutonON = 1;
        System.out.println("Vous avez buzzer");

        this.setTitle("Votre réponse");
        this.setSize(400, 100);
        this.setLocationRelativeTo(null);
        JPanel pan2 = new JPanel();
        JTextField textField = new JTextField();
        textField.setColumns(10); //On lui donne un nombre de colonnes à afficher
        pan2.add(textField);
        this.setContentPane(pan2);
        this.setVisible(true);
    }
}
