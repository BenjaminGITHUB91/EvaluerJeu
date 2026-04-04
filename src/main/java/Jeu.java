import java.util.ArrayList;

public class Jeu {

    protected String nom;
    protected String categorie;
    protected String editeur;
    protected String classement;

    protected ArrayList<Support> supports;

    public Jeu(String nom , String categorie , String editeur , String classement){
        this.nom = nom;
        this.categorie = categorie;
        this.editeur = editeur;
        this.classement = classement;

        supports = new ArrayList<>();
    }

    public void Afficher(){
        System.out.println(this);
        for (Support unSupport : supports){
            System.out.println(unSupport);
        }
    }

    @Override
    public String toString(){
        String str = "";

        return nom + " : " + categorie + ", " + editeur + ", " + classement;
    }
}
