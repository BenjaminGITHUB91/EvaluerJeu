public abstract class Evaluation {
    protected String date;
    protected String evaluation;
    protected Support version;
    protected Joueur auteur;

    public Evaluation (String date , String evaluation, Support version, Joueur auteur){
        this.date = date;
        this.evaluation = evaluation;
        this.version = version;
        this.auteur = auteur;
    }

    abstract void Afficher();
}
