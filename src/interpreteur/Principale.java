/**
 * Classe de test pour l'interpréteur.
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
            "2+3;",        // attendu : 5
            "2*3+4;",      // attendu : 10
            "(2+3)*4;",    // attendu : 20
            "3*3+4*2;",    // attendu : 17
            "2+3$4;",      // invalide
            "2+3"          // invalide (débordement géré)
        };

        for (String test : tests) {
            System.out.print("Test \"" + test + "\" -> ");
            Analyseur analyseur = new Analyseur(test);
            analyseur.interpreteur();
        }
    }
}
