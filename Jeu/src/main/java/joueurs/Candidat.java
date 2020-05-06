package joueurs;

import architecture.Ecran;
import deroulement.Deroulement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static deroulement.Deroulement.*;

/**
 * Classe permettant de creer les candidats
 */
public class Candidat {
    private final String nom;
    public int solde;
    public static int[] soldeTotalJoueur = new int[3];
    public static int[][] soldeTotal = new int[4][3];
    private static final ArrayList<Character> listeCharUtilise = new ArrayList<>();

    /**
     * Construteur permettant d'initialiser un candidat
     * @param nom nom du candidat
     */
    public Candidat(String nom){
        this.nom = nom;
    }

    /**
     * Permet d'enregistrer les soldes de chaque candidat, avant de commencer une nouvelle manche
     */
    public static void enregistrerSolde() {
        soldeTotal[mancheActuelle-1][0] = candidat1.solde;
        soldeTotal[mancheActuelle-1][1] = candidat2.solde;
        soldeTotal[mancheActuelle-1][2] = candidat3.solde;
    }

    /**
     * Permet d'affiche les soldes toatal de toutes le smanches depuis le début
     */
    public static void afficherLesSoldesTotals() {
        soldeTotalJoueur[0] = soldeTotal[0][0] + soldeTotal[1][0] + soldeTotal[2][0] + soldeTotal[3][0];
        soldeTotalJoueur[1] = soldeTotal[0][1] + soldeTotal[1][1] + soldeTotal[2][1] + soldeTotal[3][1];
        soldeTotalJoueur[2] = soldeTotal[0][2] + soldeTotal[1][2] + soldeTotal[2][2] + soldeTotal[3][2];
        System.out.println("Candidat " + candidat1.getNom() + " : " + soldeTotalJoueur[0]);
        System.out.println("Candidat " + candidat2.getNom() + " : " + soldeTotalJoueur[1]);
        System.out.println("Candidat " + candidat3.getNom() + " : " + soldeTotalJoueur[2]);
    }

    /**
     * Remettre les soldes de manche à 0
     */
    public static void clearSolde() {
        candidat1.solde = 0;
        candidat2.solde = 0;
        candidat3.solde = 0;
    }

    /**
     * Détermine qui est en final
     * @return Le nom du finaliste
     */
    public static String enFinal() {
        if (Candidat.soldeTotalJoueur[0] > Candidat.soldeTotalJoueur[1]){
            if (Candidat.soldeTotalJoueur[0] > Candidat.soldeTotalJoueur[2]){
                return candidat1.getNom();
            }
            else {
                return candidat3.getNom();
            }
        }
        else if (Candidat.soldeTotalJoueur[1] > Candidat.soldeTotalJoueur[2]) {

            return candidat2.getNom();
        }
        else {
            return candidat3.getNom();
        }
    }

    public static void resetManche() {
        listeCharUtilise.clear();
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

            char c;
            Random rand;

            do{
                rand = new Random();
                c = (char)(rand.nextInt(26) + 97);
            } while (listeCharUtilise.contains(c));

            listeCharUtilise.add(c);
            Collections.sort(listeCharUtilise);
            //System.out.println("Liste : " +listeCharUtilise);
            if(Ecran.afficherToutesLesLettres(c)){
                ajouterGain(argent);
            }
            else{
                System.out.println("Perdu...");
                Deroulement.nextCandidat();
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
                choisirLettre();

            }
        }
    }

    private void choisirLettre() {
        char c;
        Random rand;

        do{
            rand = new Random();
            c = (char)(rand.nextInt(26) + 97);
        } while (listeCharUtilise.contains(c));

        listeCharUtilise.add(c);
        Collections.sort(listeCharUtilise);
        //System.out.println("Liste : " +listeCharUtilise);
        if(!Ecran.afficherToutesLesLettres(c)){
            System.out.println("Perdu...");
            Deroulement.nextCandidat();
        }

    }

    public static void afficherLesSoldes() {
        System.out.println("Candidat " + candidat1.getNom() + " : " + candidat1.solde);
        System.out.println("Candidat " + candidat2.getNom() + " : " + candidat2.solde);
        System.out.println("Candidat " + candidat3.getNom() + " : " + candidat3.solde);
    }
}
