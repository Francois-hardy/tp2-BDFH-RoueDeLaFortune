package serveur;

import architecture.Ecran;
import architecture.RoueJeu;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import deroulement.Deroulement;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author Grégory BARTOLO, François HARDY
 */

public class Serveur {

    static SocketIOServer server;
    private static boolean buzze = false;
    private static String reponseBuzze = "mauvais";
    private static boolean valide = false;
    private static final ArrayList<UUID> session_connectee = new ArrayList<>();


    /**
     * Constructeur qui va permettre d'instancier le serveur pour communiquer
     * @param config Configuration
     */
    public Serveur(Configuration config) {
        //Création du serveur
        server = new SocketIOServer(config);

        //Démarre un thread, le programme ne s'arrêtera pas tant que le serveur n'est pas terminé
        server.start();

        server.addEventListener("ajout_jeu", String.class, (socketIOClient, s, ackRequest) -> {
            if (!session_connectee.contains(socketIOClient.getSessionId())) {
                session_connectee.add(socketIOClient.getSessionId());
                socketIOClient.joinRoom("attente");
            }

            if (session_connectee.size() >= 3) {
                //test d'envoi a chaque user de son id server
                /*for (UUID user : session_connectee) {
                    server.getClient(user).sendEvent("ton_id", user);
                }*/
                server.getRoomOperations("attente").sendEvent("lancement", "lancement");
            }
        });

        server.addEventListener("demarrage", String.class, (socketIOClient, s, ackRequest) -> {
            //System.out.println(socketIOClient.getSessionId());
            while (Ecran.listeIndices.size() > 0){
                socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);
                TimeUnit.MILLISECONDS.sleep(500);
            }
            socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);
        });

        server.addEventListener("action_buzz", String.class, (socketIOClient, s, ackRequest) -> buzze = true);

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
     * @throws InterruptedException erreur impossible
     */
    public static void main(String [] args) throws InterruptedException {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("127.0.0.1"); //134.59.2.116
        config.setPort(10101);

        config.setHttpCompression(false);
        config.setWebsocketCompression(false);

        Serveur serveur = new Serveur(config);


        //lancement réel du jeu
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