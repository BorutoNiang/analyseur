import java.util.Scanner;

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
     * Point d'entrée du programme. Lit une expression saisie par l'utilisateur
     * et la soumet à l'analyseur.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez une expression (ex: 2+3;) : ");
        String expression = scanner.nextLine().trim();
        scanner.close();

        Analyseur analyseur = new Analyseur(expression);
        analyseur.analyseur();
    }
}
