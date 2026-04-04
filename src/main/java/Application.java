import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Application {
    private final static String CHEMIN_CSV = "./src/donnees.csv";

    private ArrayList<Jeu> jeux;
    private ArrayList<String> noms_jeux;

    public Application(){
        RecupJeux(CHEMIN_CSV);
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

                if (noms_jeux.contains(nom)){ //jeu déjà enregistré ⇒ nouveau support
                    jeux.get(noms_jeux.indexOf((nom))).supports.add(unSupport);

                }else { //nouveau jeu
                    Jeu unJeu = new Jeu( nom , categorie , editeur , classement );
                    unJeu.supports.add(unSupport);
                    jeux.add(unJeu);
                    noms_jeux.add(nom);
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur dans la récupération des données du CSV :");
            e.printStackTrace();
        }

        this.jeux = jeux;
        this.noms_jeux = noms_jeux;
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
     * Affiche les informations d'un jeu à partir de son nom.
     *
     * @param nom le nom du jeu à rechercher et afficher
     * @return true si le jeu a été trouvé et affiché,
     *         false si le jeu n'existe pas dans l'application
     */
    private boolean AfficherJeu(String nom){
        int indice = noms_jeux.indexOf(nom);
        if (indice == -1){
            System.out.println("Le jeu '"+nom+"' n'est pas present dans l'application.");
            return false;
        }else {
            jeux.get(indice).Afficher();
            return true;
        }
    }

    public static void main(String[] args) {

        Application monAppli = new Application();

        monAppli.AfficherJeu("Grand Theft Auto V");
        monAppli.AfficherJeu("Grand Theft Auto VI");
    }
}
