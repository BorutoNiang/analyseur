/**
 * Classe de test pour la version 2 de l'analyseur syntaxique.
 * <p>
 * Inclut les cas problématiques mentionnés dans le sujet :
 * débordement et caractère invalide.
 * </p>
 *
 * @author Groupe DIC1
 * @version 2.0
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
            "2+3;",       // valide
            "2*3+4;",     // valide
            "(2+3)*4;",   // valide
            "2+3$4;",     // invalide : $ à la place de *
            "2+3",        // invalide : pas de point-virgule (débordement géré)
            "2+;"         // invalide : opérande manquant
        };

        for (String test : tests) {
            System.out.print("Test \"" + test + "\" -> ");
            Analyseur analyseur = new Analyseur(test);
            analyseur.analyseur();
        }
    }
}
