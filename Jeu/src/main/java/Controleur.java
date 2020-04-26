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
        RoueJeu roue = new RoueJeu();
        Ecran.choixDeLaPhrase();
        Ecran.inscriptionCacheALEcran();
        Ecran.afficherEcran();
        Ecran.afficherToutesLesLettresUneParUne();
        Deroulement.setCandidatMain(candidat1);

        System.out.println("Manche 1");
        Deroulement.setRoue(roue);
        Deroulement.lancerManche();










            //lancement du jeu
            //lancemnt d'un tour de main
            //apparition des cases 1 par 1
            //celui qui trouve prend la main
            //manche 1
            //affichage de la pharse


            //Tableau de 4 * 14
            //24 cases sur la grande roue
            //24 cases sur la petite roue

    }
}
