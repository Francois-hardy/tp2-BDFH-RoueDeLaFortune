package joueurs;

import architecture.Ecran;
import deroulement.Deroulement;

import java.util.Random;

import static deroulement.Deroulement.*;

public class Candidat {

    private final String nom;
    public int solde;

    public Candidat(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }

    public void ajouterGain(int argent) {
        this.solde += argent;
    }

    public void traiterGain(String gain) {
        try{

            int argent = Integer.parseInt(gain);
            Random rand = new Random();
            char c = (char)(rand.nextInt(26) + 97);
            if(Ecran.afficherToutesLesLettres(c)){
                ajouterGain(argent);
            }
            else{
                System.out.println("Perdu...");
            }


        }
        catch (Exception e){
            if(gain.equals("Banqueroute")){
                this.solde = 0;
                Deroulement.nextCandidat();
            }
            else if(gain.equals("Hold Up")){
                this.solde = candidat1.solde + candidat2.solde + candidat3.solde;
                for (Candidat tabCandidat : tabCandidats) {
                    if (this != tabCandidat) {
                        tabCandidat.solde = 0;
                    }
                }
            }
        }
    }

    public static void afficherLesSoldes() {
        System.out.println("Candidat " + candidat1.getNom() + " : " + candidat1.solde);
        System.out.println("Candidat " + candidat2.getNom() + " : " + candidat2.solde);
        System.out.println("Candidat " + candidat3.getNom() + " : " + candidat3.solde);
    }
}
