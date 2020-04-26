package joueurs;

import architecture.Ecran;

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
        System.out.println("Nouveu solde : "+this.solde);
    }

    public void traiterGain(String gain) {
        try{
            if(Ecran.afficherToutesLesLettres('a')){
                int argent = Integer.parseInt(gain);
                ajouterGain(argent);
            }
            else{
                System.out.println("Perdu...");
            }

        }
        catch (Exception e){
            if(gain.equals("Banqueroute")){
                this.solde = 0;
                System.out.println("Nouveu solde : "+this.solde);
            }
        }
    }
}
