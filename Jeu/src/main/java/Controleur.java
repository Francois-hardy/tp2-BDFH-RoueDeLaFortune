import Architecture.Buzzer;

import java.util.concurrent.TimeUnit;
import architecture.Ecran;
import architecture.RoueJeu;

public class Controleur {
    public static void main(String[] args) throws InterruptedException {
        new Buzzer();
        Ecran ecran = new Ecran();
        ecran.choixDeLaPhrase();
        ecran.inscriptionALEcran();
        ecran.afficherEcran();
        ecran.afficherUneLettre();
        ecran.afficherToutesLesLettresUneParUne();

        RoueJeu roue = new RoueJeu();

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
