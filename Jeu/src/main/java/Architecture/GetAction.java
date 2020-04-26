package architecture;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GetAction extends AbstractAction {
    public Buzzer fenetre;
    public String texteUtilisateur = fenetre.getTextField().getText();

    public GetAction(Buzzer fenetre, String texte){
        super(texte);
        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {

        fenetre.getLabel().setText(texteUtilisateur);
    }
}
