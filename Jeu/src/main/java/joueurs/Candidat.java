package joueurs;

public class Candidat {

    private final String nom;

    public Candidat(String nom){
        this.nom = nom;
    }
    
    public void ajouterGain(int argent) {
    }

    public void traiterGain(String gain) {
        try{
            int argent = Integer.parseInt(gain);
            ajouterGain(argent);
        }
        catch (Exception e){

        }
    }
}
