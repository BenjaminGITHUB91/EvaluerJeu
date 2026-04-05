public class EvaluationJoueur extends Evaluation{
    protected Float note;
    protected int utiliteP;
    protected int utiliteM;
    protected int signalement;

    public EvaluationJoueur(String date, String evaluation, Support version, Joueur auteur) {
        super(date, evaluation, version, auteur);
        utiliteP = 0 ;
        utiliteM = 0;
        signalement = 0;
    }



    public EvaluationJoueur(String date, String evaluation, Support version, Joueur auteur,  Float note) {
        super(date, evaluation, version, auteur);
        this.note = note;
        utiliteP = 0 ;
        utiliteM = 0;
        signalement = 0;
    }


    @Override
    void Afficher() {
        Afficher(false);
    }

    /**
     * Affiche toutes les informations de l'évaluation.
     * Les attributs utiliteP et utiliteM ne sont affichés que pour les administrateurs.
     *
     * @param estAdmin boolean indiquant si l'utilisateur courant est un administrateur
     *                 (true = affichage complet avec les utilités, false = affichage restreint sans utilités)
     */
    public void Afficher(boolean estAdmin) {
        System.out.println("Date : " + date);
        System.out.println("Evaluation : " + evaluation);
        System.out.println("Version (support) : " + (version != null ? version.toString() : "Non spécifié"));
        System.out.println("Auteur : " + (auteur != null ? auteur.toString() : "Non spécifié"));
        System.out.println("Note : " + note);

        if (estAdmin) {
            System.out.println("UtiliteP : " + utiliteP);
            System.out.println("UtiliteM : " + utiliteM);
        }
    }

}
