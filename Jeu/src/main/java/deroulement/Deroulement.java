package deroulement;

import architecture.RoueJeu;
import joueurs.Candidat;

public class Deroulement {
    public static Candidat candidatMain;
    public static RoueJeu roue;

    public static void setCandidatMain(Candidat candidat){
        System.out.println("Le candidat "+candidatMain+" gagne la main !");
        candidatMain = candidat;
    }

    private static Candidat getCandidatMain(){
        return candidatMain;
    }

    public static void lancerManche() {
        System.out.println("Le candidat "+candidatMain+" tourne la roue...");
        roue.lancerRoue(getCandidatMain());
    }

    public static void setRoue(RoueJeu roueADef) {
        roue = roueADef;
    }
}
