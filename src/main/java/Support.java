import java.util.ArrayList;

public class Support {
    protected String nom;
    protected String anneeSortie;
    protected String developpeur;
    protected float nbVentesMonde;
    protected int nbCritiqueTesteur;
    protected float scoreMoyenTesteur;
    protected int nbCritiqueJoueur;
    protected float scoreMoyenJoueur;

    protected ArrayList<Evaluation> evaluationJoueurs;
    protected ArrayList<Evaluation> evaluationTesteurs;

    public Support(String nom, String anneeSortie, String developpeur, float nbVentesMonde,
                   int nbCritiqueTesteur, float scoreMoyenTesteur, int nbCritiqueJoueur, float scoreMoyenJoueur) {
        this.nom = nom;
        this.anneeSortie = anneeSortie;
        this.developpeur = developpeur;
        this.nbVentesMonde = nbVentesMonde;
        this.nbCritiqueTesteur = nbCritiqueTesteur;
        this.scoreMoyenTesteur = scoreMoyenTesteur;
        this.nbCritiqueJoueur = nbCritiqueJoueur;
        this.scoreMoyenJoueur = scoreMoyenJoueur;

        evaluationJoueurs = new ArrayList<>();
        evaluationTesteurs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format(
                "%s :\n" +
                        "  Annee de sortie : %s\n" +
                        "  Developpeur : %s\n" +
                        "  Ventes mondiales : %.2f\n" +
                        "  Critiques testeurs : %d (score moyen : %.2f)\n" +
                        "  Critiques joueurs : %d (score moyen : %.2f)",
                nom, anneeSortie, developpeur, nbVentesMonde,
                nbCritiqueTesteur, scoreMoyenTesteur,
                nbCritiqueJoueur, scoreMoyenJoueur
        );
    }

    public void AfficherEvalJoueur(boolean admin){
        if (evaluationJoueurs.size() == 0) System.out.println("Aucune evaluation pour le moment sur ce support");
        else{
            for (Evaluation eval : evaluationJoueurs){
                eval.Afficher(admin);
                System.out.println("");
            }
        }

    }
}