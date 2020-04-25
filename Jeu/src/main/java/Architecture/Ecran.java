package Architecture;


import java.util.HashMap;

public class Ecran {
    private char[][] tableauEcran = new char[4][14];
    private String phrase;

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
        afficherEcran();
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
        for(int i = 0; i<phrase.length();i++){
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
    public void afficherUneLettre() {

    }
    public void definirLaPhrase() {
        this.phrase = "Un feu /d'artifice";
        afficherPhrase();
        HashMap<Integer, Integer> pairIndicesPhrase = new HashMap<>();
        int k = 0;
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                if(this.tableauEcran[i][j] != ' '){
                    pairIndicesPhrase.put(i,j);
                    this.tableauEcran[i][j] = ' ';

                }
            }
        }
        afficherEcran();

    }
}
