package architecture;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Ecran {
    private final char[][] tableauEcran = new char[4][14];
    private final ArrayList<int[]> listeIndices = new ArrayList<>();
    private final ArrayList<int[]> listeMemeLettre = new ArrayList<>();
    private final char[][] resultatTab = new char[4][14];

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
    }
    public void afficherEcran(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                System.out.print(this.tableauEcran[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public void choixDeLaPhrase() {
        //String phrase = "Un feu /d'artifice";
        //String phrase = "Une /bougie";
        String phrase = "Un bac à /sable";

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

    public void afficherUneLettre() {
        int taille = listeIndices.size();
        int random = (int) (Math.random() * (taille));
        int[] tableauCaseAAfficher = listeIndices.get(random);
        tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
        listeIndices.remove(random);
        afficherEcran();
    }

    public void inscriptionCacheALEcran() {
        for (int i = 0; i<4; i++){
            for (int j = 0; j<14; j++){
                if((tableauEcran[i][j] != ' ') && (tableauEcran[i][j] != '!') && (tableauEcran[i][j] != '\'')){
                    int[] ajout = {i,j};
                    listeIndices.add(ajout);
                    tableauEcran[i][j] = '_';
                }
            }
        }
    }

    public void afficherToutesLesLettresUneParUne() {
        try{
            while (true){
                afficherUneLettre();
                TimeUnit.SECONDS.sleep(1);
                if (Buzzer.getBoutonON() == 1) {
                    return;
                }

            }

        }
        catch (Exception ignored){
        }
    }
    public void afficherToutesLesLettres(char lettre) {
        int taille = listeIndices.size();
        System.out.println("taille: "+taille);
        for(int i = 0; i<taille;i++){
            taille = listeIndices.size();
            int[] tableauCaseAAfficher = listeIndices.get(i);
            if(resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] == lettre){
                tableauEcran[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]] = resultatTab[tableauCaseAAfficher[0]][tableauCaseAAfficher[1]];
                listeIndices.remove(i);
            }
        }

        afficherEcran();
    }
}
