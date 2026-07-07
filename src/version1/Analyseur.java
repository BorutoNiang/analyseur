/**
 * Analyseur syntaxique d'expressions arithmétiques par descente récursive.
 * <p>
 * Chaque méthode correspond à un symbole non-terminal de la grammaire :
 * </p>
 * <pre>
 * E -&gt; S ;
 * S -&gt; P | P + S
 * P -&gt; T | T * P
 * T -&gt; C | ( S )
 * C -&gt; 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * </pre>
 * <p>
 * Version 1 : retour booléen pour la gestion des erreurs, sans gestion du débordement.
 * </p>
 *
 * @author Groupe DIC1
 * @version 1.0
 * @see Source
 */
public class Analyseur {

    /** Source de caractères à analyser. */
    private Source source;

    /**
     * Construit un analyseur pour la chaîne donnée.
     *
     * @param chaine l'expression arithmétique à analyser
     */
    public Analyseur(String chaine) {
        this.source = new Source(chaine);
    }

    /**
     * Point d'entrée de l'analyse. Appelle {@link #expression()} et affiche
     * un message selon le résultat.
     */
    public void analyseur() {
        if (expression()) {
            System.out.println("Analyse réussie : expression valide.");
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
     *
     * @return {@code true} si la somme est syntaxiquement correcte
     */
    private boolean somme() {
        if (!produit()) return false;
        if (source.premier() == '+') {
            source.suivant();
            return somme();
        }
        return true;
    }

    /**
     * Analyse la règle {@code P -> T | T * P}.
     *
     * @return {@code true} si le produit est syntaxiquement correct
     */
    private boolean produit() {
        if (!terme()) return false;
        if (source.premier() == '*') {
            source.suivant();
            return produit();
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
            source.suivant();
            if (!somme()) return false;
            if (source.premier() != ')') return false;
            source.suivant();
            return true;
        }
        return chiffre();
    }

    /**
     * Analyse la règle {@code C -> 0 | 1 | ... | 9}.
     *
     * @return {@code true} si le caractère courant est un chiffre
     */
    private boolean chiffre() {
        char c = source.premier();
        if (c >= '0' && c <= '9') {
            source.suivant();
            return true;
        }
        return false;
    }
}
