import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {
    private static final int NBJETONSINITIAL = 3;

    protected String pseudo;
    protected HashMap<Jeu , Float> jeuxPossedes;
    protected ArrayList<Evaluation> evaluations;
    protected int nbJeton;

    public Joueur(String pseudo){
        this.pseudo = pseudo;
        jeuxPossedes = new HashMap<>();
        evaluations = new ArrayList<>();
        nbJeton = NBJETONSINITIAL;
    }

    public void AddJeu(Jeu jeu , Float temp){
        jeuxPossedes.put(jeu , temp);
    }

    /**
     * Affiche toutes les informations du joueur : pseudo, nombre de jetons,
     * jeux possédés et évaluations réalisées.
     */
    public void Afficher() {
        System.out.println("Pseudo : " + pseudo);
        System.out.println("Nombre de jetons : " + nbJeton);

        System.out.println("Jeux possedes :");
        if (jeuxPossedes.isEmpty()) {
            System.out.println("  Aucun jeu possede");
        } else {
            for (Jeu jeu : jeuxPossedes.keySet()) {
                float temps = jeuxPossedes.get(jeu);
                System.out.println("  - " + jeu.nom + " (" + temps + " heures)");
            }
        }

        System.out.println(evaluations.size() + " evaluation.s realisee.s :");
    }

}
