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
                TimeUnit.MILLISECONDS.sleep(505);
            }
            socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);
        });

        server.addEventListener("action_buzz", String.class, (socketIOClient, s, ackRequest) -> {
            //a remplir par francois le bg

            buzze = true;
        });

        server.addEventListener("envoie_phrase_buzz", String.class, (socketIOClient, s, ackRequest) -> {
            //a remplir par francois le bg

            if (s == "null"){
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
    public static void main(String [] args) throws InterruptedException, IOException {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
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
                if(valide) {
                    try {
                        if (reponseBuzze.equals(Ecran.phraseReponse())) {
                            while (Ecran.listeIndices.size() > 0) {
                                Ecran.afficherUneLettre();
                            }
                            System.out.println("reponse");
                            valide = false;
                            buzze = false;
                            reponseBuzze = " ";

                        } else if (!reponseBuzze.equals("mauvais")) {
                            valide = false;
                            buzze = false;
                            reponseBuzze = " ";
                            System.out.println("perdu");
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
    }
}