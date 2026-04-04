public class Support {
    protected String nom;
    protected String anneeSortie;
    protected String developpeur;
    protected float nbVentesMonde;
    protected int nbCritiqueTesteur;
    protected float scoreMoyenTesteur;
    protected int nbCritiqueJoueur;
    protected float scoreMoyenJoueur;

    public Support(String nom, String anneeSortie, String developpeur, float nbVentesMonde, int nbCritiqueTesteur, float scoreMoyenTesteur, int nbCritiqueJoueur, float scoreMoyenJoueur) {
        this.nom = nom;
        this.anneeSortie = anneeSortie;
        this.developpeur = developpeur;
        this.nbVentesMonde = nbVentesMonde;
        this.nbCritiqueTesteur = nbCritiqueTesteur;
        this.scoreMoyenTesteur = scoreMoyenTesteur;
        this.nbCritiqueJoueur = nbCritiqueJoueur;
        this.scoreMoyenJoueur = scoreMoyenJoueur;
    }

    @Override
    public String toString(){
        String str = "";
        return nom + " : annee " + anneeSortie + ", developpeur : " + developpeur + ", nombre de ventes mondiales : " + nbVentesMonde + ", nombre de critique de testeur : " + nbCritiqueTesteur + ", score moyen des testeurs : " + scoreMoyenTesteur + ", nombre de critique de joueur : " + nbCritiqueJoueur + ", score moyen des joueurs : " + scoreMoyenJoueur;
    }
}