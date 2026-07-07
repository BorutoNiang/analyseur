/**
 * Classe de test pour le compilateur.
 *
 * @author Groupe DIC1
 * @version 1.0
 * @see Analyseur
 */
public class Principale {

    /** Construit une instance de Principale. */
    public Principale() {}

    /**
     * Point d'entrée du programme.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        String[] tests = {
            "2+3;",
            "2*3+4;",
            "(2+3)*4;"
        };

        for (String test : tests) {
            System.out.println("=== Compilation de \"" + test + "\" ===");
            Analyseur analyseur = new Analyseur(test);
            analyseur.compilateur();
            System.out.println();
        }
    }
}
