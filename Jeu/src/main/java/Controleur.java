import Architecture.Ecran;

public class Controleur {
    public static void main(String[] args) {
        Ecran ecran = new Ecran();

        ecran.afficherPhrase();

        ecran.definirLaPhrase();

        ecran.afficherEcran();
        for(int i = 0; i<14;i++){
            ecran.afficherUneLettre();
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
