package architecture;

import joueurs.Candidat;

public class RoueJeu {
    String[] tableauDeLaRoue;

    public RoueJeu(){
        tableauDeLaRoue = new String[]{"100", "350", "250", "100", "1000", "Hold Up", "150", "250", "500", "Banqueroute", "1500", "150", "250", "Passe", "400", "2000", "100", "0", "150", "250", "300", "150", "200", "Banqueroute"};
        System.out.println(tableauDeLaRoue.length);
    }
    public void lancerRoue(Candidat candidat){
        int random = (int) (Math.random() * (24));
        String gain = tableauDeLaRoue[random];
        System.out.println("La roue tombe sur : " + gain);
        candidat.traiterGain(gain);
    }
}
