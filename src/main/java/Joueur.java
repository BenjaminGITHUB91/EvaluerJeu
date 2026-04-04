import java.util.ArrayList;

public class Joueur {
    private static final int NBJETONSINITIAL = 20;

    protected String pseudo;
    protected ArrayList<Jeu> jeuxPossedes; //TODO FAIRE UN DICTIONNAIRE HASHMAP ? POUR AVOIR JEU & DUREE DE JEU en heures
//    protected ArrayList<Evaluation> evaluations;
    protected int nbJeton;
}
