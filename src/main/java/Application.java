import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private final static String CHEMIN_CSV = "./src/donnees.csv";

    private Scanner scanner;

    private ArrayList<Jeu> jeux;
    private ArrayList<String> nomsJeux;        //contient les noms des jeux stockes dans l'application, en minuscule pour ne pas etre sensible a la casse

    private ArrayList<Joueur> joueurs;
    private ArrayList<String> nomJoueurs; //sensible a la casse

    public Application(){
        joueurs = new ArrayList<>();
        nomJoueurs = new ArrayList<>();

        RecupJeux(CHEMIN_CSV);
        Remplissage();

        scanner = new Scanner(System.in);
    }

    private void Remplissage(){
        Joueur admin = new Administrateur("admin");
        Joueur testeur1 = new Testeur("testeur1");
        Joueur testeur2 = new Testeur("testeur2");
        Joueur joueur1 = new Joueur("Benjamin");

        AddJeu(admin , "Duck Hunt" , 5f);
        AddJeu(admin , "Nintendogs" , 63f);
        AddJeu(admin , "Wii fit" , 5094387.345f);

        AddJeu(testeur1 , "Grand theft auto v" , 777.1f);

        EvaluationTesteur evalT1 = new EvaluationTesteur("04-05-2026" , "GTA V s'inscrit dans la liste tres reduite des meilleurs au monde...",
                RecupererUnJeu("grand theft auto V").supports.get(0) , testeur1 , 19.99f);
        evalT1.recommendations.add(RecupererUnJeu("Grand Theft Auto: Vice City"));
        evalT1.Noter(18.5f, 17f, 20f, 16f, 19.9f);
        evalT1.ptsFaibles.add("Un mode online un peu restraint.");
        evalT1.ptsFaibles.add("Pas assez de vehicule en mode histoire.");
        evalT1.ptsForts.add("Critique hilarante des Etats Unis.");
        evalT1.ptsForts.add("Une immersion que tres rerement egalee.");

        testeur1.evaluations.add(evalT1);
        RecupererUnJeu("grand theft auto V").supports.get(0).evaluationTesteurs.add(evalT1);


        joueurs.add(admin);
        joueurs.add(testeur1);
        joueurs.add(testeur2);
        joueurs.add(joueur1);

        nomJoueurs.add(admin.pseudo);
        nomJoueurs.add(testeur1.pseudo);
        nomJoueurs.add(testeur2.pseudo);
        nomJoueurs.add(joueur1.pseudo);
    }

    private boolean AddJeu(Joueur joueur , String nomJeu, Float temps){
        Jeu jeu = RecupererUnJeu(nomJeu);
        if (jeu == null) return false;
        joueur.AddJeu(jeu , temps);
        return true;
    }

    /**
     * Parse une ligne CSV en gérant correctement les champs entre guillemets
     * qui peuvent contenir des virgules ou des guillemets échappés.
     *
     * @param ligne la ligne CSV à parser (ne doit pas être null)
     * @return un tableau de String contenant chaque champ de la ligne CSV,
     *         les guillemets encadrants sont retirés, les guillemets doubles
     *         échappés sont convertis en guillemet simple
     */
    private static String[] parseCSVLigne(String ligne) {
        ArrayList<String> cellules = new ArrayList<>();
        StringBuilder cellEnCours = new StringBuilder();
        boolean enGuillemets = false;

        for (int i = 0; i < ligne.length(); i++) {
            char c = ligne.charAt(i);

            if (c == '"') {
                if (i + 1 < ligne.length() && ligne.charAt(i + 1) == '"') {
                    cellEnCours.append('"');
                    i++;
                } else {
                    enGuillemets = !enGuillemets;
                }
            } else if (c == ',' && !enGuillemets) {
                cellules.add(cellEnCours.toString().trim());
                cellEnCours.setLength(0);
            } else {
                cellEnCours.append(c);
            }
        }
        cellules.add(cellEnCours.toString().trim());
        return cellules.toArray(new String[0]);
    }

    /**
     * Récupère et parse les données d'un fichier CSV contenant des informations sur les jeux vidéo.
     * Cette méthode lit le fichier ligne par ligne, extrait les informations de chaque jeu
     * et construit une liste d'objets Jeu avec leurs supports respectifs. Cette liste d'objets est
     * ensuite attribuée à l'attribut de classe jeux.
     *
     * @param chemin le chemin d'accès absolu ou relatif vers le fichier CSV à parser
     * @throws IOException peut être lancée en interne si le fichier n'existe pas ou
     *         ne peut pas être lu, mais elle est gerée dans la méthode
     */
    private void RecupJeux(String chemin){
        ArrayList<Jeu> jeux = new ArrayList<>(); //liste des jeux
        ArrayList<String> noms_jeux = new ArrayList<>(); //liste des noms des jeux déjà récupérés pour pouvoir différencier un nouveau jeu d'un nouveau support
        String ligne;

        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {

            if ((ligne = br.readLine()) != null) {
                String[] enTete = parseCSVLigne(ligne);
            }

            // ligne
            int cptJeu = 0;
            while ((ligne = br.readLine()) != null) {
                String[] cellules = parseCSVLigne(ligne);

                String nom = cellules[1];

                String nomSupp = cellules[2];
                String anneeSortie = cellules[3];

                String categorie = cellules[4];
                String editeur = cellules[5];

                float nbVentesMonde = Float.parseFloat(cellules[10]);

                String classement = "";
                int nbCritiqueTesteur = 0;
                float scoreMoyenTesteur = 0f;
                int nbCritiqueJoueur = 0;
                float scoreMoyenJoueur = 0f;
                String developpeur = "";

                if (cellules.length > 12  && ! cellules[11].isEmpty() && !cellules[11].equals("tbd"))
                    scoreMoyenTesteur = Float.parseFloat(cellules[11]);

                if (cellules.length > 13 && ! cellules[12].isEmpty() && !cellules[12].equals("tbd"))
                    nbCritiqueTesteur = Integer.parseInt(cellules[12]);

                if(cellules.length > 14 && ! cellules[13].isEmpty() && !cellules[13].equals("tbd"))
                    scoreMoyenJoueur = Float.parseFloat(cellules[13]);

                if (cellules.length > 15 && ! cellules[14].isEmpty() && !cellules[14].equals("tbd"))
                    nbCritiqueJoueur = Integer.parseInt(cellules[14]);

                if (cellules.length > 16 && ! cellules[15].isEmpty() && !cellules[15].equals("tbd"))
                    developpeur = cellules[15];

                if (cellules.length > 17 && ! cellules[16].isEmpty() && !cellules[16].equals("tbd"))
                    classement = cellules[16];

                Support unSupport = new Support(nomSupp , anneeSortie , developpeur ,nbVentesMonde, nbCritiqueTesteur, scoreMoyenTesteur, nbCritiqueJoueur, scoreMoyenJoueur);

                if (noms_jeux.contains(nom.toLowerCase())){ //jeu déjà enregistré ⇒ nouveau support
                    jeux.get(noms_jeux.indexOf((nom.toLowerCase()))).supports.add(unSupport);

                }else { //nouveau jeu
                    Jeu unJeu = new Jeu( nom , categorie , editeur , classement );
                    unJeu.supports.add(unSupport);
                    jeux.add(unJeu);
                    noms_jeux.add(nom.toLowerCase());
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur dans la récupération des données du CSV :");
            e.printStackTrace();
        }

        this.jeux = jeux;
        this.nomsJeux = noms_jeux;
    }

    /**
     * Affiche les informations d'un jeu à partir de son index dans la liste.
     *
     * @param indice la position du jeu dans la liste (0-indexé)
     * @return true si l'index est valide et l'affichage a été effectué,
     *         false sinon (index hors limites)
     */
    private boolean AfficherJeu(int indice){
        if (indice < this.jeux.size()){
            jeux.get(indice).Afficher();
            return true;
        }else {
            System.out.println("Indice trop eleve (max = " + this.jeux.size() + ").");
            return false;
        }
    }

    /**
     * Affiche les informations d'un jeu à partir de son nom. Afin de ne pas etre sensible a la casse,
     * le parametre est passe en miniscule afin de pouvoir etre compare a la liste des noms de jeux
     * enregistres qui sont en minuscule egalement.
     *
     * @param nom le nom du jeu à rechercher et afficher
     * @return true si le jeu a été trouvé et affiché,
     *         false si le jeu n'existe pas dans l'application
     */
    private boolean AfficherJeu(String nom){
        int indice = nomsJeux.indexOf(nom.toLowerCase());
        if (indice == -1){
            System.out.println("Le jeu '"+nom+"' n'est pas present dans l'application.");
            return false;
        }else {
            jeux.get(indice).Afficher();
            return true;
        }
    }

    private Jeu RecupererUnJeu(String nom){
        int indice = nomsJeux.indexOf(nom.toLowerCase());
        if (indice == -1){
            return null;
        }else {
            return jeux.get(indice);
        }
    }

    private Jeu RecupererJeuCommande(){
        System.out.println("Nom du jeu cherche (nom en entier, pas sensible a la casse) :");
        String nom = scanner.nextLine();
        return RecupererUnJeu(nom);
    }

    private int verifChoix(String [] choixAcceptable , String input){
        Scanner scanner = new Scanner(System.in);
        // si l'user input ne correspond pas a un des choix propose on boucle
        while (! Arrays.asList(choixAcceptable).contains(input)){
            System.out.println("Choix non reconnu, veuillez recommencer s'il vous plait :");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * Propose un menu de choix à l'utilisateur et valide sa réponse.
     * Affiche le message suivi des options numérotées (1, 2, 3, ...).
     *
     * @param msg le message à afficher avant la liste des choix
     * @param choix le tableau des libellés des options (ex: {"Fermer", "Ouvrir"})
     * @return l'entier correspondant au choix validé (1 pour le premier choix, 2 pour le second, etc.)
     */
    public int ProposerChoix(String msg, String[] choix) {
        System.out.println(msg);

        for (int i = 0; i < choix.length; i++) {
            System.out.println(" " + (i + 1) + " - " + choix[i]);
        }

        String input = scanner.nextLine();

        String[] choixAcceptable = new String[choix.length];
        for (int i = 0; i < choix.length; i++) {
            choixAcceptable[i] = String.valueOf(i + 1);
        }

        return verifChoix(choixAcceptable, input);
    }

    private int Lancement(){
        String[] choix = {
                "Rester en mode anonyme",
                "Se connecter",
                "Creer un compte",
                "Fermer l'application"
        };
        return ProposerChoix("Veuillez saisir le numero de la commande que vous souhaitez realiser :", choix);
    }

    private Joueur Connexion(){
        Joueur joueur = null;
        boolean continuer = true;

        System.out.println("---Connexion---");
        System.out.println("Veuillez vous connecter en saisissant votre pseudo (attention les pseudos sont sensibles a la casse !) :");
        String pseudo = scanner.nextLine();

        if (nomJoueurs.contains(pseudo)){
            continuer = false;
            joueur = joueurs.get(nomJoueurs.indexOf(pseudo));
        }

        while (continuer){
            System.out.println("Le compte '" + pseudo + "' n'existe pas dans l'application.");

            int choix = ProposerChoix("Voulez-vous :", new String[]{"Reessayer avec un autre pseudo", "Annuler"});

            switch (choix){
                case 1:
                    System.out.println("Reessayez :");
                    pseudo = scanner.nextLine();

                    if (nomJoueurs.contains(pseudo)){
                        continuer = false;
                        joueur = joueurs.get(nomJoueurs.indexOf(pseudo));
                    }
                    break;
                case 2:
                    continuer = false;
                    break;
            }
        }
        return joueur;
    }

    private Joueur Inscription(){
        System.out.println("---Inscription---");
        System.out.println("Veuillez saisir votre pseudo (attention, ce dernier est sensible a la casse !) :");
        String pseudo = scanner.nextLine();

        while (nomJoueurs.contains(pseudo)){
            System.out.println("Le pseudo '" + pseudo + "' est malheureusement deja pris par un autre utilisateur.\n(vous pouvez rajouter des caracteres speciaux ou des chiffres si vous souhaitez garder ce pseudo).");

            int choix = ProposerChoix("Voulez-vous :", new String[]{"Reessayer avec un autre pseudo", "Annuler"});

            switch (choix){
                case 1:
                    System.out.println("Reessayez :");
                    pseudo = scanner.nextLine();
                    break;
                case 2:
                    return null;
            }
        }

        Joueur joueur = new Joueur(pseudo);
        joueurs.add(joueur);
        nomJoueurs.add(joueur.pseudo);
        return joueur;
    }

    private void AfficherEvaluationTesteur(String nomJeu){
        Jeu jeu = RecupererUnJeu(nomJeu);
        if (jeu != null){
            jeu.AfficherEvalTesteur();
        }
    }

    /**
     * Vérifie le rôle d'un joueur connecté et retourne son rôle sous forme de chaîne.
     *
     * @param joueur l'objet Joueur dont on veut vérifier le rôle (peut être null)
     * @return un tableau d'objets contenant les informations du rôle :
     *         [0] - boolean estConnecte (true si joueur non null)
     *         [1] - String pseudo (le pseudo du joueur ou "Anonyme" si non connecte)
     *         [2] - String role ("Invite", "Joueur", "Testeur" ou "Administrateur")
     *         [3] - boolean estJoueur (true si joueur est un joueur)
     *         [4] - boolean estTesteur (true si joueur est un testeur)
     *         [5] - boolean estAdmin (true si joueur est un administrateur)
     */
    private static Object[] VerifRole(Joueur joueur) {
        boolean estConnecte = joueur != null;
        String pseudo = "Anonyme";
        String role = "Invite";
        Boolean estJoueur = false;
        Boolean estTesteur = false;
        Boolean estAdmin = false;
        if (estConnecte) {
            pseudo = joueur.pseudo;

            if (joueur instanceof Administrateur) {
                role = "Administrateur";
                estAdmin = true;
            } else if (joueur instanceof Testeur) {
                role = "Testeur";
                estTesteur = true;
            } else if (joueur instanceof Joueur) {
                role = "Joueur";
                estJoueur = true;
            }
        }

        return new Object[]{estConnecte, pseudo, role, estJoueur, estTesteur , estAdmin};
    }

    private void AfficherMembres(){
        System.out.println("\nListe des membres de l'applications :");
        for (Joueur unJoueur : joueurs){
            System.out.println(unJoueur.pseudo);
        }
    }

    public static void main(String[] args) {

        Application monAppli = new Application();
        boolean fermer = false;

        System.out.println("Bienvenue sur GameBox, la plateforme vous permettant de noter vos jeux et de voir les evaluations des autres utilisateurs !");
        System.out.println("Vous etes actuellement deconnecte, vous pouvez uniquement chercher et lire des evaluations, mais rien de plus.");
        System.out.println("Pour pouvoir ajouter vos jeux et les evaluer, veuillez-vous connecter, si vous n'avez pas de compte, vous pouvez vous en creer un gratuitement !");

        int choix = monAppli.Lancement();
        System.out.println("");

        Joueur joueur = null;
        boolean estConnecte = false;
        boolean estJoueur = false;
        boolean estTesteur = false;
        boolean estAdmin = false;
        String role = "Invite";
        String pseudo = "Anonyme";

        switch (choix){
            case 1:
                break;

            case 2:
                joueur = monAppli.Connexion();
                break;

            case 3:
                joueur = monAppli.Inscription();
                break;

            case 4:
                System.out.println("---Fermeture de l'application---");
                fermer = true;
                break;
        }

        // verification du role
        Object[] infosRole = VerifRole(joueur);
        estConnecte = (boolean) infosRole[0];

        pseudo = (String) infosRole[1];
        role = (String) infosRole[2];

        estJoueur = (boolean) infosRole[3];
        estTesteur = (boolean) infosRole[4];
        estAdmin = (boolean) infosRole[5];
        while (! fermer) {

            System.out.println("\n----Accueil - Compte : " + pseudo + " - Role : " + role + "----");
            String[] choixPossibles = new String[]{};
            if (!estConnecte) {
                choixPossibles = new String[]{
                        "Chercher un jeu",
                        "Se connecter",
                        "S'inscrire",
                        "Fermer l'application"
                };
            } else if (estJoueur) { //a ce niveau les joueurs et les testeurs ont les memes permissions
                choixPossibles = new String[]{
                        "Afficher mon profil",
                        "Chercher un jeu",
                        "Se desinscrire",
                        "Se deconnecter",
                        "Fermer l'application"
                };
            } else if (estAdmin) {
                choixPossibles = new String[]{
                        "Afficher mon profil",
                        "Chercher un jeu",
                        "Se desinscrire",
                        "Se deconnecter",
                        "Fermer l'application",
                        "Afficher tous les comptes",
                        "Chercher un compte specifique"
                };
            }

            choix = monAppli.ProposerChoix("Veuillez saisir le numero de la commande que vous souhaitez realiser :", choixPossibles);

            boolean chercherJeu = false;
            if (!estConnecte){
                switch (choix){
                    case 1 :    //chercher jeu
                        chercherJeu = true;
                        break;
                    case 2,3 :    //connexion et
                        if (choix == 2) joueur = monAppli.Connexion();
                        else joueur = monAppli.Inscription();

                        infosRole = VerifRole(joueur);
                        estConnecte = (boolean) infosRole[0];
                        pseudo = (String) infosRole[1];
                        role = (String) infosRole[2];
                        break;
                    case 4 :    //fermeture
                        fermer = true;
                    break;
                }
            }else {
                switch (choix){
                    case 1:
                        joueur.Afficher();
                        break;
                    case 2:
                        chercherJeu = true;
                        break;
                    case 3:
                        choix = monAppli.ProposerChoix("Cette action est permanente, etes vous sur de vouloir supprimer votre compte ?" , new String[]{"oui" , "non"});
                        if (choix == 1){
                            monAppli.joueurs.remove(joueur);
                            monAppli.nomJoueurs.remove(monAppli.nomJoueurs.indexOf(pseudo));

                            joueur = null;
                            estConnecte = false;
                            pseudo = "Anonyme";
                            role = "Invite";
                        }
                        break;
                    case 4:
                        joueur = null;
                        estConnecte = false;
                        pseudo = "Anonyme";
                        role = "Invite";
                        break;
                    case 5:
                        fermer = true;
                        break;
                    case 6:
                        monAppli.AfficherMembres();
                        break;
                    case 7:
                        break;

                }
            }


            if (chercherJeu){
                Jeu jeu = monAppli.RecupererJeuCommande();
                if (jeu == null) System.out.println("Le jeu n'existe pas dans l'application.");
                else jeu.Afficher();
            }

            if (fermer){
                System.out.println("---Fermeture de l'application---");
            }

        }


    monAppli.scanner.close();
    }
}