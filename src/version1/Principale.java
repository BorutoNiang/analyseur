/**
 * Classe de test pour la version 1 de l'analyseur syntaxique.
 *
 * @author Groupe DIC1
 * @version 1.0
 * @see Analyseur
 */
public class Principale {

    /** Construit une instance de Principale. */
    public Principale() {}

    /**
     * Point d'entrée du programme. Soumet plusieurs expressions à l'analyseur.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        String[] tests = {
            "2+3;",       // valide
            "2*3+4;",     // valide
            "(2+3)*4;",   // valide
            "2+;",        // invalide
            "2+3"         // invalide — provoque un débordement (corrigé en v2)
        };

        for (String test : tests) {
            System.out.print("Test \"" + test + "\" -> ");
            Analyseur analyseur = new Analyseur(test);
            analyseur.analyseur();
        }
    }
}
