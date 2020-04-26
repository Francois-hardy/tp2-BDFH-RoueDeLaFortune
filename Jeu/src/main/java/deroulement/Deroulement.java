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
        candidatMain.ajouterGain(500);
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
        if(mancheActuelle <4){
            mancheActuelle++;
            System.out.println("Manche "+ mancheActuelle);
            Ecran.choixDeLaPhrase();
            Ecran.inscriptionCacheALEcran();
            Ecran.afficherEcran();
            while (Ecran.resteLettre()){
                System.out.println("Le candidat "+candidatMain.getNom()+" tourne la roue...");
                RoueJeu.lancerRoue(getCandidatMain());
                //Candidat.afficherLesSoldes();
            }
            System.out.println("Fin de la manche " + mancheActuelle);
            Candidat.enregistrerSolde();
            Candidat.clearSolde();
            Candidat.resetManche();
            lancerManche();
        }
        else {
            Candidat.afficherLesSoldesTotals();
            System.out.println("Le finaliste est : " + Candidat.enFinal());
        }


    }

    public static void nextCandidat() {
        if (tabCandidats[0] == candidatMain){
            setCandidatMain(tabCandidats[1]);
        }
        else if (tabCandidats[1] == candidatMain){
            setCandidatMain(tabCandidats[2]);
        }
        else if (tabCandidats[2] == candidatMain){
            setCandidatMain(tabCandidats[0]);
        }
        System.out.println("Suivant...");
    }
}
