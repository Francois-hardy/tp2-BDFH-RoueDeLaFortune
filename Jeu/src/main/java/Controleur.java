import architecture.Ecran;
import architecture.RoueJeu;

public class Controleur {
    public static void main(String[] args) {
        Ecran ecran = new Ecran();
        ecran.choixDeLaPhrase();
        ecran.inscriptionALEcran();
        ecran.afficherEcran();
        ecran.afficherUneLettre();
        ecran.afficherToutesLesLettresUneParUne();

        RoueJeu roue = new RoueJeu();

        //celui qui trouve prend la main
        //manche 1
        //affichage de la pharse




        //Tableau de 4 * 14
        //24 cases sur la grande roue
        //24 cases sur la petite roue
    }
}
