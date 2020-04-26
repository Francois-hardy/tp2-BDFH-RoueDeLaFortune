import architecture.Buzzer;

import architecture.Ecran;
import architecture.RoueJeu;
import joueurs.Candidat;

import java.util.concurrent.TimeUnit;

public class Controleur {
    public static void main(String[] args) throws InterruptedException {
        Candidat candidat1 = new Candidat("Pierre");
        Candidat candidat2 = new Candidat("Paul");
        Candidat candidat3 = new Candidat("Jacque");
        Buzzer Buzzer = new Buzzer();
        Ecran ecran = new Ecran();
        ecran.choixDeLaPhrase();
        ecran.inscriptionCacheALEcran();
        ecran.afficherEcran();
        ecran.afficherToutesLesLettres('a');
        ecran.afficherToutesLesLettres('b');

        ecran.afficherToutesLesLettresUneParUne();

        //RoueJeu roue = new RoueJeu();

        //roue.lancerRoue(candidat1);




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
