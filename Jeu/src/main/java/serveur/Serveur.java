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

        // Récupère les joueurs qui seront mis en file d'attente
        server.addEventListener("ajout_jeu", String.class, (socketIOClient, s, ackRequest) -> {
            // Ajoute le joueur arrivant dans la file d'attente
            if (!session_connectee.contains(socketIOClient.getSessionId())) {
                session_connectee.add(socketIOClient.getSessionId());
                socketIOClient.joinRoom("attente");
                //Renvoie aux clients le nombre de joueurs en attente
                server.getRoomOperations("attente").sendEvent("nombre_en_attente", session_connectee.size());
            }

            // Demarre le jeu quand le nombre de joueurs en attente est satisfaisant
            if (session_connectee.size() >= 2) {
                //test d'envoi a chaque user de son id server
                int i = 1;
                for (UUID user : session_connectee) {
                    server.getClient(user).sendEvent("envoi_username", i);
                    i++;
                }

                //lancement réel du jeu

                while (true){
                    Deroulement.setCandidats();
                    Ecran.creerEcran();
                    RoueJeu.creerRoueJeu();
                    Ecran.selectionnePhrase();
                    Ecran.inscriptionCacheALEcran();
                    Ecran.afficherEcran();


                    while (Ecran.listeIndices.size() > 0) {
                        if (!buzze) {
                            Ecran.afficherUneLettre();
                            server.getRoomOperations("attente").sendEvent("phrase", (Object) Ecran.tableauEcran);
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                        else {
                            TimeUnit.MILLISECONDS.sleep(50);
                            if (valide) {
                                try {
                                    if (reponseBuzze.equalsIgnoreCase(Ecran.phraseReponse())) {
                                        System.out.println("Bonne réponse");
                                        while (Ecran.listeIndices.size() > 0) {
                                            Ecran.afficherUneLettre();
                                        }
                                        server.getRoomOperations("attente").sendEvent("phrase", (Object) Ecran.tableauEcran);
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
                                    System.out.println("Mauvaise réponse");
                                }
                            }
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(5000);
                    Ecran.vide();
                    server.getRoomOperations("attente").sendEvent("phrase", (Object) Ecran.tableauEcran);
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Ecran.prochaineManche();
                    server.getRoomOperations("attente").sendEvent("phrase", (Object) Ecran.tableauEcran);
                    TimeUnit.MILLISECONDS.sleep(3000);
                }

            }
        });

        server.addEventListener("action_buzz", String.class, (socketIOClient, s, ackRequest) -> {
            server.getRoomOperations("attente").sendEvent("buzzGris", "buzzGris");
            buzze = true;
            System.out.println("BUZZ");
        });

        server.addEventListener("retourNombre", String.class, (socketIOClient, s, ackRequest) ->
                System.out.println(s));

        server.addEventListener("demande_categorie", String.class, (socketIOClient, s, ackRequest) ->
                socketIOClient.sendEvent("categorie", Ecran.categorie));

        server.addEventListener("envoie_phrase_buzz", String.class, (socketIOClient, s, ackRequest) -> {
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
    public static void main(String [] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("0.0.0.0"); //134.59.2.116
        config.setPort(49999);

        config.setHttpCompression(false);
        config.setWebsocketCompression(false);

        Serveur serveur = new Serveur(config);
    }
}