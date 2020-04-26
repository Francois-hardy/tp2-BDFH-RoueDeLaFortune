package deroulement;

import architecture.Ecran;
import architecture.RoueJeu;
import joueurs.Candidat;

public class Deroulement {
    public static Candidat candidatMain;
    public static Candidat[] tabCandidats;
    public static Candidat candidat1;
    public static Candidat candidat2;
    public static Candidat candidat3;
    public static int mancheActuelle = 0;

    public static void setCandidatMain(Candidat candidat){
        candidatMain = candidat;
        System.out.println("Le candidat "+candidatMain.getNom()+" gagne la main !");
    }

    public static void setCandidats(){
        candidat1 = new Candidat("Pierre");
        candidat2 = new Candidat("Paul");
        candidat3 = new Candidat("Jacque");
        tabCandidats = new Candidat[]{candidat1, candidat2, candidat3};
    }

    public static Candidat getCandidatMain(){
        return candidatMain;
    }

    public static void lancerManche() {
        mancheActuelle++;
        System.out.println("Manche "+ mancheActuelle);
        Ecran.choixDeLaPhrase();
        Ecran.inscriptionCacheALEcran();
        Ecran.afficherEcran();
        System.out.println("Le candidat "+candidatMain.getNom()+" tourne la roue...");
        RoueJeu.lancerRoue(getCandidatMain());
    }
}
