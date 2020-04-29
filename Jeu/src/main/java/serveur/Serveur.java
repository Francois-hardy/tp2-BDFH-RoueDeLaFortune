package serveur;

import architecture.Ecran;
import architecture.RoueJeu;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import deroulement.Deroulement;

import java.io.*;


/**
 * @author Grégory BARTOLO
 */

public class Serveur {

    static SocketIOServer server;


    /**
     * Constructeur qui va permettre d'instancier le serveur pour communiquer
     * @param config Configuration
     */
    public Serveur(Configuration config) {
        //Création du serveur
        server = new SocketIOServer(config);

        //Démarre un thread, le programme ne s'arrêtera pas tant que le serveur n'est pas terminé
        server.start();


        //Deroulement.setCandidatMain(candidat1);
        //Deroulement.lancerManche();

        server.addEventListener("demarrage", String.class, (socketIOClient, s, ackRequest) -> {
            //Buzzer Buzzer = new Buzzer();
            Deroulement.setCandidats();
            Ecran.creerEcran();
            RoueJeu.creerRoueJeu();
            Ecran.choixDeLaPhrase();
            Ecran.inscriptionCacheALEcran();
            Ecran.afficherEcran();
            //Ecran.afficherToutesLesLettresUneParUne();
            socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);




        });

        server.addEventListener("suivant", String.class, (socketIOClient, s, ackRequest) -> {
            if(Ecran.listeIndices.size() > 0){
                Ecran.afficherUneLettre();
                socketIOClient.sendEvent("phrase", (Object) Ecran.tableauEcran);
            }
        });


        //Ecouteur de connexion d'un client au serveur

        /*server.addConnectListener(client -> {
        });*/


        //Ecouteur de la deconnexion d'un client du serveur

        /*server.addDisconnectListener(client -> {
        });*/

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
        config.setHostname("127.0.0.1");
        config.setPort(10101);

        config.setHttpCompression(false);
        config.setWebsocketCompression(false);
        Serveur serveur = new Serveur(config);
    }

    public static void afficherEcran(char[][] tableauEcran) {

    }
}