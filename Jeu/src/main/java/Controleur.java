import Architecture.Buzzer;
import Architecture.Ecran;

import java.util.concurrent.TimeUnit;

public class Controleur {
    public static void main(String[] args) throws InterruptedException {
        Buzzer Buzzer = new Buzzer();
        Ecran ecran = new Ecran();
        ecran.afficherPhrase();
        ecran.definirLaPhrase();
        ecran.afficherEcran();
        ecran.afficherUneLettre();
        for (int i = 0; i < 13; i++) {
                ecran.afficherUneLettre();
                TimeUnit.SECONDS.sleep(1);
                if (Buzzer.boutonON == 1){
                    i = 15;
                }
        }


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
