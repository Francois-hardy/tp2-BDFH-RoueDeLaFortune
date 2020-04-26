package deroulement;

import architecture.RoueJeu;
import joueurs.Candidat;

public class Deroulement {
    public static Candidat candidatMain;
    public static RoueJeu roue;

    public static void setCandidatMain(Candidat candidat){
        candidatMain = candidat;
        System.out.println("Le candidat "+candidatMain.getNom()+" gagne la main !");
    }

    private static Candidat getCandidatMain(){
        return candidatMain;
    }

    public static void lancerManche() {
        System.out.println("Le candidat "+candidatMain.getNom()+" tourne la roue...");
        RoueJeu.lancerRoue(getCandidatMain());
    }
}
