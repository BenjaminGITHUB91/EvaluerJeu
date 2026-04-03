public class Support {
    protected String nom;
    protected String anneeSortie;
    protected String developpeur;
    protected int nbVentesMonde;
    protected int nbCritiqueTesteur;
    protected float scoreMoyenTesteur;
    protected int nbCritiqueJoueur;
    protected float scoreMoyenJoueur;

    public Support(String nom, String anneeSortie, String developpeur, int nbVentesMonde, int nbCritiqueTesteur, float scoreMoyenTesteur, int nbCritiqueJoueur, float scoreMoyenJoueur) {
        this.nom = nom;
        this.anneeSortie = anneeSortie;
        this.developpeur = developpeur;
        this.nbVentesMonde = nbVentesMonde;
        this.nbCritiqueTesteur = nbCritiqueTesteur;
        this.scoreMoyenTesteur = scoreMoyenTesteur;
        this.nbCritiqueJoueur = nbCritiqueJoueur;
        this.scoreMoyenJoueur = scoreMoyenJoueur;
    }
}