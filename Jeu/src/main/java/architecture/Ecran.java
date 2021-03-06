package architecture;

import deroulement.Deroulement;
import java.util.ArrayList;

/**
 * Classe static permettant de créer, gérer, afficher l'écran
 */
public class Ecran {
    public final static char[][] tableauEcran = new char[4][14];
    public final static ArrayList<int[]> listeIndices = new ArrayList<>();
    public final static char[][] resultatTab = new char[4][14];
    public static String phrase;
    public static String categorie;

    /**
     * Permet de créer l'écran
     */
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

    /**
     * Affiche le contenu de l'acran
     */
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

    /**
     * Affcihe le résultat (réponse)
     */
    public static void afficherResultat(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(resultatTab[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    /**
     * Permet de faire un traitement de la pharse pour l'afficher à l'écran
     */
    public static void choixDeLaPhrase()  {
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

    /**
     * Permet de sélectionne une phrase et une catégorie
     */
    public static void selectionnePhrase() {
        
        String[] celebrites = {"Célébrités","ANGELINA /JOLIE","GEORGE /CLOONEY", "BRAD /PITT", "TOM /HANKS", "KATY /PERRY", "MARILYN /MONROE", "CELINE /DION", "ELTON /JOHN", "ELVIS /PRESLEY", "ROBERT /DOWNEY", "DAVID /BECKHAM", "CRISTIANO /RONALDO", "TAYLOR /SWIFT", "JENNIFER /LOPEZ", "DWAYNE /JOHNSON", "JOHNNY /DEPP", "TIGER WOODS", "JUSTIN /BIEBER", "STEVEN /SPIELBERG", "HARRISON /FORD", "GORDON /RAMSAY", "MEGAN /FOX", "MICHAEL /JACKSON", "LADY GAGA", "MUHAMMAD /ALI", "ZINEDINE /ZIDANE", "LIONEL /MESSI"};
        String[] informatique = {"Informatique","CONNEXION /CLIENT /SERVEUR","PROTOCOLE /TCP UDP","DECONNEXION /BRUTALE","UN PAQUET /ICMP","UN ROUTER /CISCO","MECANISME /DE /TRANSLATION","UNE REQUETE /HTTP","BOITES DE /DEPOTS","SYSTEMES /ET /RESEAUX"};
        String[] objets = {"Objets","UN BAC A /SABLE","UNE PAIRE /DE /LUNETTES","UN ETUI /A /LUNETTES","UN CAHIER /DE /BROUILLON","UNE PAIRE /DE /CISEAUX","UNE /TONDEUSE /ELECTRIQUE","UN SET /DE /COUVERTS","UNE RATION /DE /NOURRITURE","UNE TROUSSE /DE SURVIE","UNE TROUSSE /DE SOINS","UN SAC A /DOS /ETANCHE","UN SPRAY /AU POIVRE","UNE /SERVIETTE /LARGE","UNE PAIRE /DE /CHAUSSURE","UNE PAIRE /DE /CLAQUETTES","UN GILET /DE SECURITE","UN /TROUSSEAU /DE CLES"};
        String[] presidents = {"Présidents","BARACK /OBAMA","DONALD /TRUMP","EMMANUEL /MACRON","FRANÇOIS /HOLLANDE","NICOLAS /SARKOZY","JACQUES /CHIRAC","FRANÇOIS /MITTERRAND","VALERY /GISCARD /D'ESTAING","CHARLES DE /GAULLE","RENE COTY","VINCENT /AURIOL","GEORGE /WALKER /BUSH","BILL /CLINTON","GEORGE /HERBERT /WALKER BUSH","RONALD /REAGAN","JIMMY /CARTER","GERALD /FORD","RICHARD /NIXON"};
        
        String tmpPhrase;
        String[][] tabCategorie = {celebrites,informatique,objets,presidents};
        int randomCategorie = (int) (Math.random() * (tabCategorie.length));
        int randomPhrase = (int) (1+Math.random() * (tabCategorie[randomCategorie].length-1));
        System.out.println("Emplacement : "+randomCategorie + "  "+randomPhrase);
        tmpPhrase = tabCategorie[randomCategorie][randomPhrase];
        categorie = tabCategorie[randomCategorie][0];
        phrase = tmpPhrase;
        choixDeLaPhrase();
    }

    /**
     * Vérifie s'il reste des lettres à trouver ou si tout a été affiché
     * @return true s'il reste des lettres, ou au contraire false
     */
    public static boolean resteLettre() {
        return listeIndices.size() >= 1;
    }

    /**
     * Affcihe une lettre au hasard
     */
    public static void afficherUneLettre() {
        int taille = listeIndices.size();
        int random = (int) (Math.random() * (taille));
        int[] tableauCaseAAfficher = listeIndices.get(random);
        tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
        listeIndices.remove(random);
        afficherEcran();
    }

    /**
     * Liste les lettres à trouver
     */
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
    }

    /**
     * Affiche toutes les lettres du char en attribut
     * @param lettre La lettre à afficher
     * @return Si oui ou non la lettre donné est bien présente sur l'écran
     */
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

    /**
     * Recoustruit la phrase en langage courant (fr)
     * @return La réponse
     */
    public static String phraseReponse() {
        String phraseReponse = phrase;
        phraseReponse = phraseReponse.replace("/", "");
        return phraseReponse;
    }

    public static void vide() {
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                tableauEcran[i][j] = ' ';
            }
        }
        tableauEcran[0][0] = '!';
        tableauEcran[0][13] = '!';
        tableauEcran[3][0] = '!';
        tableauEcran[3][13] = '!';
    }

    public static void prochaineManche() {
        phrase = "PROCHAINE/MANCHE...";
        int col = 3;
        int ligne = 2;
        for(int i = 0; i< phrase.length(); i++){
            if(phrase.charAt(i) == '/'){
                ligne++;
                col = 3;
            }
            else{
                tableauEcran[ligne-1][col-1] = phrase.charAt(i);
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

}
