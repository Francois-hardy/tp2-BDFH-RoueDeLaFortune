import architecture.Buzzer;

import architecture.Ecran;
import architecture.RoueJeu;
import deroulement.Deroulement;
import joueurs.Candidat;

public class Controleur {
    public static void main(String[] args) {
        Candidat candidat1 = new Candidat("Pierre");
        Candidat candidat2 = new Candidat("Paul");
        Candidat candidat3 = new Candidat("Jacque");
        Buzzer Buzzer = new Buzzer();
        Ecran.allumerEcran();
        RoueJeu.creerRoueJeu();
        Ecran.choixDeLaPhrase();
        Ecran.inscriptionCacheALEcran();
        Ecran.afficherEcran();
        Ecran.afficherToutesLesLettresUneParUne();
        Deroulement.setCandidatMain(candidat1);

        System.out.println("Manche 1");
        Deroulement.lancerManche();

    }
}
