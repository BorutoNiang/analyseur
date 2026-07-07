/**
 * Interpréteur d'expressions arithmétiques par descente récursive.
 * <p>
 * Étend l'analyseur version 2 : chaque méthode empile sur {@link Pile}
 * la valeur qu'elle calcule. À la fin, le résultat se trouve au sommet.
 * </p>
 * <pre>
 * E -&gt; S ;
 * S -&gt; P | P + S
 * P -&gt; T | T * P
 * T -&gt; C | ( S )
 * C -&gt; 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * </pre>
 *
 * @author Groupe DIC1
 * @version 1.0
 * @see Source
 * @see Pile
 */
public class Analyseur {

    /** Source de caractères à analyser. */
    private Source source;

    /** Pile utilisée pour le calcul des valeurs. */
    private Pile pile;

    /**
     * Construit un interpréteur pour la chaîne donnée.
     *
     * @param chaine l'expression arithmétique à interpréter
     */
    public Analyseur(String chaine) {
        this.source = new Source(chaine);
        this.pile = new Pile();
    }

    /**
     * Point d'entrée de l'interpréteur.
     * Affiche le résultat au sommet de la pile ou un message d'erreur.
     */
    public void interpreteur() {
        if (expression()) {
            System.out.println("Résultat = " + pile.sommet());
        } else {
            System.out.println("Erreur de syntaxe : expression invalide.");
        }
    }

    /**
     * Analyse la règle {@code E -> S ;}.
     *
     * @return {@code true} si l'expression est syntaxiquement correcte
     */
    private boolean expression() {
        if (!somme()) return false;
        if (source.premier() != ';') return false;
        source.suivant();
        return true;
    }

    /**
     * Analyse la règle {@code S -> P | P + S}.
     * Empile {@code a + b} si un opérateur {@code +} est présent.
     *
     * @return {@code true} si la somme est syntaxiquement correcte
     */
    private boolean somme() {
        if (!produit()) return false;
        if (source.premier() == '+') {
            if (!source.suivant()) return false;
            if (!somme()) return false;
            int b = pile.depiler();
            int a = pile.depiler();
            pile.empiler(a + b);
        }
        return true;
    }

    /**
     * Analyse la règle {@code P -> T | T * P}.
     * Empile {@code a * b} si un opérateur {@code *} est présent.
     *
     * @return {@code true} si le produit est syntaxiquement correct
     */
    private boolean produit() {
        if (!terme()) return false;
        if (source.premier() == '*') {
            if (!source.suivant()) return false;
            if (!produit()) return false;
            int b = pile.depiler();
            int a = pile.depiler();
            pile.empiler(a * b);
        }
        return true;
    }

    /**
     * Analyse la règle {@code T -> C | ( S )}.
     *
     * @return {@code true} si le terme est syntaxiquement correct
     */
    private boolean terme() {
        if (source.premier() == '(') {
            if (!source.suivant()) return false;
            if (!somme()) return false;
            if (source.premier() != ')') return false;
            source.suivant();
            return true;
        }
        return chiffre();
    }

    /**
     * Analyse la règle {@code C -> 0 | 1 | ... | 9}.
     * Empile la valeur numérique du chiffre reconnu.
     *
     * @return {@code true} si le caractère courant est un chiffre
     */
    private boolean chiffre() {
        char c = source.premier();
        if (c >= '0' && c <= '9') {
            pile.empiler(c - '0');
            source.suivant();
            return true;
        }
        return false;
    }
}
