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
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Entrez une expression (ex: 2+3;) : ");
        String expression = scanner.nextLine().trim();
        scanner.close();

        Analyseur analyseur = new Analyseur(expression);
        analyseur.interpreteur();
    }
}
