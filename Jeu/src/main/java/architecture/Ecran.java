package architecture;


import com.sun.xml.ws.util.StringUtils;
import deroulement.Deroulement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Ecran {
    public final static char[][] tableauEcran = new char[4][14];
    public final static ArrayList<int[]> listeIndices = new ArrayList<>();
    public final static char[][] resultatTab = new char[4][14];
    public static String phrase;
    public static String categorie;

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
        System.out.println("Catégorie: "+categorie);
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(tableauEcran[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public static void afficherResultat(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(resultatTab[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public static void choixDeLaPhrase() throws IOException {
        //phrase = selectionnePhrase();
        //phrase = "UN FEU /D'ARTIFICE";
        phrase = "CONNEXION /CLIENT /SERVEUR";
        //phrase = selectionnePhrase();

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

    private static void selectionnePhrase() {

        String[] celebrites = {"ANGELINA /JOLIE","GEORGE /CLOONEY"};
        String[] informatique = {"CONNEXION /CLIENT /SERVEUR","PROTOCOLE /TCP UDP"};
        String[] objets = {"UN BAC A /SABLE","UNE PAIRE /DE /LUNETTES"};
        String[] presidents = {"BARACK /OBAMA","DONALD /TRUMP"};

    }

    public static boolean resteLettre() {

        return listeIndices.size() >= 1;
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
        listeIndices.clear();
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                if((tableauEcran[i][j] != ' ') && (tableauEcran[i][j] != '!') && (tableauEcran[i][j] != '\'' ) && (tableauEcran[i][j] != '?') && (tableauEcran[i][j] != '-')){
                    int[] ajout = {i,j};
                    listeIndices.add(ajout);
                    tableauEcran[i][j] = '_';
                }
            }
        }
        //System.out.println("TAILLE : "+listeIndices.size());
    }


    public static boolean afficherToutesLesLettres(char lettre) {
        int taille = listeIndices.size();
        int[] tableauCaseAAfficher;
        boolean trouve = false;
        System.out.println("Le candidat "+ Deroulement.getCandidatMain().getNom()+" propose la lettre "+lettre);

        for(int i = 0; i<taille;i++){
            taille = listeIndices.size();
            tableauCaseAAfficher = listeIndices.get(i);
            //System.out.println("TAILLE : "+listeIndices.size());
            if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == lettre){
                tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                listeIndices.remove(i);
                i--;
                taille = listeIndices.size();
                trouve = true;
            }
            else if(lettre == 'a'){
                if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'à' || resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'â'){
                    tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                    listeIndices.remove(i);
                    i--;
                    taille = listeIndices.size();
                    trouve = true;
                }

            }
            else if(lettre == 'e'){
                if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'é' || resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'è' || resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'ê'){
                    tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                    listeIndices.remove(i);
                    i--;
                    taille = listeIndices.size();
                    trouve = true;
                }
            }
            else if(lettre == 'o'){
                if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'ô'){
                    tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                    listeIndices.remove(i);
                    i--;
                    taille = listeIndices.size();
                    trouve = true;
                }
            }
            else if(lettre == 'c'){
                if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == 'ç'){
                    tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                    listeIndices.remove(i);
                    i--;
                    taille = listeIndices.size();
                    trouve = true;
                }
            }
        }

        afficherEcran();
        return trouve;
    }

    public static String phraseReponse() {
        String phraseReponse = phrase;
        phraseReponse = phraseReponse.replace("/", "");
        return phraseReponse;
    }
}
