import java.util.ArrayList;

public class Jeu {

    protected String nom;
    protected String categorie;
    protected String editeur;
    protected String classement;

    protected ArrayList<Support> supports;

    protected int jetons;

    public Jeu(String nom , String categorie , String editeur , String classement){
        this.nom = nom;
        this.categorie = categorie;
        this.editeur = editeur;
        this.classement = classement;

        supports = new ArrayList<>();

        jetons = 0;
    }

    public void Afficher(){
        System.out.println(this);
        int cpt = 1;
        for (Support unSupport : supports){
            System.out.println( cpt + " - " + unSupport);
        }
    }

    public void AfficherEvalTesteur(){
        for (Support unSupport : supports){
            if (unSupport.evaluationTesteurs.size() == 0){
                System.out.println("Le support '" + unSupport.nom + "' n'a pas ete evalue par des testeurs.");
            }else{
                for (Evaluation evaluationTesteur : unSupport.evaluationTesteurs){
                    evaluationTesteur.Afficher();
                }
            }
        }
    }

    @Override
    public String toString(){
        String str = "";

        return nom + " : " + categorie + ", " + editeur + ", " + classement;
    }
}
