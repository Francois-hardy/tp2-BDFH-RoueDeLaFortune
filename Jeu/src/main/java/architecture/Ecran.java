package architecture;


import deroulement.Deroulement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Ecran {
    private final static char[][] tableauEcran = new char[4][14];
    private final static ArrayList<int[]> listeIndices = new ArrayList<>();
    private final static ArrayList<int[]> listeMemeLettre = new ArrayList<>();
    private final static char[][] resultatTab = new char[4][14];

    public static void creerEcran(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                tableauEcran[i][j] = ' ';
                resultatTab[i][j] = ' ';
            }
        }
        tableauEcran[0][0] = '!';
        tableauEcran[0][13] = '!';
        tableauEcran[3][0] = '!';
        tableauEcran[3][13] = '!';

        resultatTab[0][0] = '!';
        resultatTab[0][13] = '!';
        resultatTab[3][0] = '!';
        resultatTab[3][13] = '!';
    }
    public static void afficherEcran(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(tableauEcran[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    private static void afficherResultat(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(resultatTab[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public static void choixDeLaPhrase() {
        //String phrase = "Un feu /d'artifice";
        //String phrase = "Une /bougie";
        String phrase = "un bac à /sable";

        int col = 3;
        int ligne = 2;
        for(int i = 0; i< phrase.length(); i++){
            if(phrase.charAt(i) == '/'){
                ligne++;
                col = 3;
            }
            else{
                tableauEcran[ligne-1][col-1] = phrase.charAt(i);
                resultatTab[ligne-1][col-1] = phrase.charAt(i);
                if(col >= 13){
                    ligne++;
                    col = 3;
                }
                else {
                    col++;
                }
            }
        }
    }

    public static boolean resteLettre() {
        if(listeIndices.size() <= 0) {
            return false;
        }
        else{
            return true;
        }
    }

    public static void afficherUneLettre() {
        int taille = listeIndices.size();
        int random = (int) (Math.random() * (taille));
        int[] tableauCaseAAfficher = listeIndices.get(random);
        tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
        listeIndices.remove(random);
        afficherEcran();
    }

    public static void inscriptionCacheALEcran() {
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                if((tableauEcran[i][j] != ' ') && (tableauEcran[i][j] != '!') && (tableauEcran[i][j] != '\'' ) && (tableauEcran[i][j] != '?') && (tableauEcran[i][j] != '-')){
                    int[] ajout = {i,j};
                    listeIndices.add(ajout);
                    tableauEcran[i][j] = '_';
                }
            }
        }
    }

    public static void afficherToutesLesLettresUneParUne() {
        try{
            while (true){
                afficherUneLettre();
                TimeUnit.SECONDS.sleep(1);
                if (Buzzer.getBoutonON() == 1) {
                    afficherResultat();
                    return;
                }

            }

        }
        catch (Exception ignored){
        }
    }
    public static boolean afficherToutesLesLettres(char lettre) {
        int taille = listeIndices.size();
        boolean trouve = false;
        System.out.println("Le candidat "+ Deroulement.getCandidatMain().getNom()+" propose la lettre "+lettre);

        for(int i = 0; i<taille;i++){
            taille = listeIndices.size();
            int[] tableauCaseAAfficher = listeIndices.get(i);
            if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == lettre){
                tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                listeIndices.remove(i);
                trouve = true;
            }
            else if(lettre == 'a'){
                tableauCaseAAfficher = listeIndices.get(i);
                if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'à' || resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'â'){
                    tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                    listeIndices.remove(i);
                    trouve = true;
                }
            }
            else if(lettre == 'e'){
                tableauCaseAAfficher = listeIndices.get(i);
                if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'é' || resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'è' || resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'ê'){
                    tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                    listeIndices.remove(i);
                    trouve = true;
                }
            }
        }
        afficherEcran();
        return trouve;
    }
}
