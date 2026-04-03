import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static ArrayList<Jeu> RecupJeux(String chemin){
        ArrayList<Jeu> jeux = new ArrayList<>(); //liste des jeux
        ArrayList<String> jeux_recuperes = new ArrayList<>(); //liste des noms des jeux déjà récupérés pour pouvoir différencier un nouveau jeu d'un nouveau support
        String ligne;
        String separateur = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {

            if ((ligne = br.readLine()) != null) {
                String[] enTete = ligne.split(separateur);
                System.out.println("En-têtes: " + Arrays.toString(enTete));
            }

            ArrayList<Integer> tailles = new ArrayList<Integer>();

            // ligne
            while ((ligne = br.readLine()) != null) {
                String[] cellules = ligne.split(separateur);

                //if (!tailles.contains(cellules.length)){tailles.add(cellules.length);}

                String nom = cellules[1];

                String nomSupp = cellules[2];
                String anneeSortie = cellules[3];

                String categorie = cellules[4];
                String editeur = cellules[5];

                String classement = "";
                int nbCritiqueTesteur = 0;
                float scoreMoyenTesteur = 0f;
                int nbCritiqueJoueur = 0;
                float scoreMoyenJoueur = 0f;

                int nbVentesMonde = Integer.parseInt(cellules[10]);

                nbCritiqueTesteur = Integer.parseInt(cellules[12]);
                scoreMoyenTesteur = Float.parseFloat(cellules[11]);

                nbCritiqueJoueur = Integer.parseInt(cellules[14]);
                scoreMoyenJoueur = Float.parseFloat(cellules[13]);

                // certaines entrées n'ont pas toutes les informations, auquel cas on ne mettra rien pour le champ concerné
                if (cellules.length >= 17) { classement = cellules[16];}

                String developpeur = cellules[15];



                Support unSupport = new Support(nomSupp , anneeSortie , developpeur ,nbVentesMonde, nbCritiqueTesteur, scoreMoyenTesteur, nbCritiqueJoueur, scoreMoyenJoueur);

                if (jeux_recuperes.contains(nom)){ //jeu déjà enregistré => nouveau support
                    jeux.get(jeux_recuperes.indexOf((nom))).supports.add(unSupport);

                }else { //nouveau jeu
                    Jeu unJeu = new Jeu( nom , categorie , editeur , classement );
                    unJeu.supports.add(unSupport);
                    jeux.add(unJeu);
                    jeux_recuperes.add(nom);
                    System.out.println(unJeu);
                }
            }


            for (int i = 0 ; i < tailles.size() ; i++){System.out.println((tailles.get(i)));}

        } catch (IOException e) {
            System.out.println("Erreur dans la récupération des données du CSV :");
            e.printStackTrace();
        }

        return jeux;
    }

    public static void main(String[] args) {

        RecupJeux("./src/donnees.csv");

    }
}
