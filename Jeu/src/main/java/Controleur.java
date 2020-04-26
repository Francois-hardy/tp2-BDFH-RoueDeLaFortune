import architecture.Buzzer;

import architecture.Ecran;
import architecture.RoueJeu;
import deroulement.Deroulement;
import joueurs.Candidat;

import static deroulement.Deroulement.candidat1;

public class Controleur {
    public static void main(String[] args) {

        Buzzer Buzzer = new Buzzer();
        Deroulement.setCandidats();
        Ecran.creerEcran();
        RoueJeu.creerRoueJeu();
        Ecran.choixDeLaPhrase();
        Ecran.inscriptionCacheALEcran();
        Ecran.afficherEcran();
        Ecran.afficherToutesLesLettresUneParUne();
        Deroulement.setCandidatMain(candidat1);
        Deroulement.lancerManche();
    }
}
