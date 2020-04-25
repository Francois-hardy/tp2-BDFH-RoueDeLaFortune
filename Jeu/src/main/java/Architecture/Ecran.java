package Architecture;


import java.util.ArrayList;
import java.util.HashMap;

public class Ecran {
    private char[][] tableauEcran = new char[4][14];
    private String phrase = "Un feu /d'artifice";
    private HashMap<Integer, Integer> pairIndicesPhrase = new HashMap<>();
    private ArrayList<int[]> liste = new ArrayList<>();
    private char[][] resultatTab;

    public Ecran(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                this.tableauEcran[i][j] = ' ';
            }
        }
        this.tableauEcran[0][0] = '!';
        this.tableauEcran[0][13] = '!';
        this.tableauEcran[3][0] = '!';
        this.tableauEcran[3][13] = '!';
        //afficherEcran();
    }
    public void afficherEcran(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(this.tableauEcran[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void afficherPhrase() {
        //String phrase = "Un feu /d'artifice";
        //String phrase = "Une /bougie";
        //String this.phrase = "Un bac à /sable";

        int col = 3;
        int ligne = 2;
        for(int i = 0; i<this.phrase.length();i++){
            if(this.phrase.charAt(i) == '/'){
                ligne++;
                col = 3;
            }
            else{
                tableauEcran[ligne-1][col-1] = this.phrase.charAt(i);

                if(col >= 13){
                    ligne++;
                    col = 3;
                }
                else {
                    col++;
                }
            }
        }
        resultatTab = tableauEcran;
        System.out.println("A = "+ resultatTab[1][6]);

    }
    public void afficherUneLettre() {
        int taille = liste.size();
        int random = (int) (Math.random() * (taille));
        //System.out.println(Arrays.toString(liste.get(5)));
        int[] tableauCaseAAfficher = liste.get(5);
        //System.out.println(tableauCaseAAfficher[0] + " " +tableauCaseAAfficher[1]);
        System.out.println("C = "+ resultatTab[1][6]);
        this.tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
        afficherEcran();
    }
    public void definirLaPhrase() {
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                if(tableauEcran[i][j] != ' '){
                    int[] tab1 = {i,j};
                    liste.add(tab1);
                    tableauEcran[i][j] = '_';
                    System.out.println("B = "+ resultatTab[1][6]);

                }
            }
        }
    }
}
