import java.util.Stack;

/**
 * Pile d'entiers utilisée par l'interpréteur pour évaluer les expressions.
 * <p>
 * Chaque valeur calculée (chiffre, somme, produit) est empilée.
 * À la fin de l'analyse, le résultat se trouve seul au sommet.
 * </p>
 *
 * @author Groupe DIC1
 * @version 1.0
 */
public class Pile {

    /** Structure interne de la pile. */
    private Stack<Integer> stack = new Stack<>();

    /**
     * Construit une pile vide.
     */
    public Pile() {}

    /**
     * Empile une valeur entière.
     *
     * @param valeur la valeur à empiler
     */
    public void empiler(int valeur) {
        stack.push(valeur);
    }

    /**
     * Dépile et retourne la valeur au sommet de la pile.
     *
     * @return la valeur au sommet
     */
    public int depiler() {
        return stack.pop();
    }

    /**
     * Retourne la valeur au sommet sans la dépiler.
     *
     * @return la valeur au sommet
     */
    public int sommet() {
        return stack.peek();
    }

    /**
     * Vérifie si la pile est vide.
     *
     * @return {@code true} si la pile ne contient aucun élément
     */
    public boolean estVide() {
        return stack.isEmpty();
    }
}
