import java.util.ArrayList;
import java.util.Arrays;

public class EvaluationTesteur extends Evaluation {

    protected ArrayList<String> categories;
    protected ArrayList<Float> notesCategories; //notes sur 10 total ramene sur 100
    protected Float noteGenre;

    protected ArrayList<String> ptsForts;
    protected ArrayList<String> ptsFaibles;

    protected String conditionsTest;

    protected ArrayList<Jeu> recommendations;

    public EvaluationTesteur(String date, String evaluation, Support version, Joueur auteur, Float noteGenre) {
        super(date, evaluation, version, auteur);
        this.noteGenre = noteGenre;

        categories = new ArrayList<>(Arrays.asList("Interface", "Gameplay" , "Optimisation" , "Graphismes" , "Histoire"));
        notesCategories = new ArrayList<>(Arrays.asList(0f, 0f ,0f , 0f , 0f));
        ptsForts = new ArrayList<>();
        ptsFaibles = new ArrayList<>();
        recommendations = new ArrayList<>();

    }

    public float NoteTotale(){
        float nT = 0f;
        for (Float uneNote : notesCategories){
            nT += uneNote;
        }
        nT = nT / notesCategories.size() * 100;
        return nT;
    }

    @Override
    public void Afficher(){
        System.out.println("Date du test : " + date + ", auteur : " + auteur.pseudo);
        System.out.println("Test de la version : " + version.toString());
        System.out.println(evaluation);
        System.out.println("Categorie   -   Note");
        for (int i = 0 ; i < categories.size() ; i++){
            System.out.println(categories.get(i) + ' ' + notesCategories.get(i).toString());
        }
        System.out.println("Points forts");
        if (ptsForts.size() == 0){
            System.out.println("Aucuns points forts renseignes.");
        }else {
            for (int i = 0; i < ptsForts.size(); i++) {
                System.out.println(" - " + ptsForts.get(i));
            }
        }
        System.out.println("Points faibles");
        if (ptsFaibles.size() == 0){
            System.out.println("Aucuns points faibles renseignes.");
        }else {
            for (int i = 0; i < ptsFaibles.size(); i++) {
                System.out.println(" - " + ptsFaibles.get(i));
            }
        }
        System.out.printf("Recommendations");
        if (recommendations.size() == 0){
            System.out.println("Aucunes recommendations renseignees.");
        }else {
            for (int i = 0; i < recommendations.size(); i++) {
                System.out.println(" - " + recommendations.get(i).nom);
            }
        }
        System.out.printf("---Fin du test---");
    }

    public void Noter(float v, float v1, float v2, float v3, float v4) {
        notesCategories.set(0,v);
        notesCategories.set(1,v1);
        notesCategories.set(2,v2);
        notesCategories.set(3,v3);
        notesCategories.set(4,v4);
    }
}
