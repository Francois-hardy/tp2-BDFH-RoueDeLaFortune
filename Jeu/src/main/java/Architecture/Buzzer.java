package architecture;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Buzzer extends JFrame implements ActionListener {

    public JButton bouton = new JButton("Buzzer");
    public JButton bouton1 = new JButton(new GetAction(this, "Je valide"));
    public static Integer boutonON = 0;

    public JTextField textField;
    public JLabel label;

    public static int getBoutonON(){
        return boutonON;
    }

    public Buzzer(){
        this.setTitle("Roue de la fortune");
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

        textField = new JTextField();
        textField.setColumns(10); //On lui donne un nombre de colonnes à afficher
        label = new JLabel("Votre réponse");

        pan2.add(textField);
        pan2.add(bouton1);
        pan2.add(label);
        this.setContentPane(pan2);
        this.setVisible(true);

    }

    public JTextField getTextField(){
        return textField;
    }

    public JLabel getLabel(){
        return label;
    }
}
