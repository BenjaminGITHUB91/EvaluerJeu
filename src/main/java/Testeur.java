import java.util.ArrayList;

public class Testeur extends Joueur{

    protected ArrayList<EvaluationTesteur> tests;

    public Testeur(String pseudo) {
        super(pseudo);
        tests = new ArrayList<>();
    }
}
