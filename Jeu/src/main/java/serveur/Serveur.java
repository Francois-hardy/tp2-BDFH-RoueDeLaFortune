package serveur;

import architecture.Ecran;
import architecture.RoueJeu;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import deroulement.Deroulement;

import java.io.*;
import java.util.concurrent.TimeUnit;


/**
 * @author Grégory BARTOLO
 */

public class Serveur {

    static SocketIOServer server;
    private static boolean buzze = false;
    private static String reponseBuzze = "mauvais";
    private static boolean valide = false;


    /**
     * Constructeur qui va permettre d'instancier le serveur pour communiquer
     * @param config Configuration
     */
    public Serveur(Configuration config) {
        //Création du serveur
        server = new SocketIOServer(config);

        //Démarre un thread, le programme ne s'arrêtera pas tant que le serveur n'est pas terminé
        server.start();

        server.addEventListener("demarrage", String.class, (socketIOClient, s, ackRequest) -> {
            while (Ecran.listeIndices.size() > 0){
                socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);
                TimeUnit.MILLISECONDS.sleep(500);
            }
            socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);
        });

        server.addEventListener("action_buzz", String.class, (socketIOClient, s, ackRequest) -> {
            //a remplir par francois le bg

            buzze = true;
        });

        server.addEventListener("retourNombre", String.class, (socketIOClient, s, ackRequest) ->
                System.out.println(s));

        server.addEventListener("demande_categorie", String.class, (socketIOClient, s, ackRequest) ->
                socketIOClient.sendEvent("categorie", Ecran.categorie));

        server.addEventListener("envoie_phrase_buzz", String.class, (socketIOClient, s, ackRequest) -> {
            //a remplir par francois le bg
            try {
                if (s.equals("null")){
                    s = "mauvais";
                }
            }
            catch (Exception ignored){
                s = "mauvais";
            }

            reponseBuzze = s;
            valide = true;

        });
    }

    //Définition de l'encodage et du modèle d'affichage des prints
    //Configuration des paramètres du serveur et démarrage

    /**
     * Programme principale gérant les options du serveur
     * @param args Arguments
     */
    public static void main(String [] args) throws InterruptedException {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("polymnie.unice.fr");
        config.setPort(10101);

        config.setHttpCompression(false);
        config.setWebsocketCompression(false);
        Serveur serveur = new Serveur(config);


        Deroulement.setCandidats();
        Ecran.creerEcran();
        RoueJeu.creerRoueJeu();
        Ecran.choixDeLaPhrase();
        Ecran.inscriptionCacheALEcran();
        Ecran.afficherEcran();
        TimeUnit.MILLISECONDS.sleep(3000);

        while (Ecran.listeIndices.size() > 0){
            if(buzze){
                System.out.println("BUZZ");
                if(valide) {
                    System.out.println("VALIDE");
                    try {
                        if (reponseBuzze.equalsIgnoreCase(Ecran.phraseReponse())) {
                            System.out.println("Bonne réponse");
                            while (Ecran.listeIndices.size() > 0) {
                                Ecran.afficherUneLettre();
                            }
                            valide = false;
                            buzze = false;
                            reponseBuzze = " ";

                        } else  {
                            valide = false;
                            buzze = false;
                            reponseBuzze = " ";
                            System.out.println("Mauvaise réponse");
                        }
                    }
                    catch (Exception ignored){
                        valide = false;
                        buzze = false;
                        reponseBuzze = " ";
                        System.out.println("perdu");
                    }
                }
            }
            else {
                Ecran.afficherUneLettre();
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        TimeUnit.MILLISECONDS.sleep(3000);


    }
}