/**
 * Compilateur d'expressions arithmétiques par descente récursive.
 * <p>
 * Au lieu d'exécuter les calculs, chaque opération génère une instruction
 * en pseudo-code pile ({@code PUSH}, {@code ADD}, {@code MUL}) affichée sur la sortie standard.
 * </p>
 * <pre>
 * E -&gt; S ;
 * S -&gt; P | P + S
 * P -&gt; T | T * P
 * T -&gt; C | ( S )
 * C -&gt; 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * </pre>
 * <p>Exemple — {@code (2+3)*4;} génère :</p>
 * <pre>
 * PUSH 2
 * PUSH 3
 * ADD
 * PUSH 4
 * MUL
 * </pre>
 *
 * @author Groupe DIC1
 * @version 1.0
 * @see Source
 */
public class Analyseur {

    /** Source de caractères à analyser. */
    private Source source;

    /**
     * Construit un compilateur pour la chaîne donnée.
     *
     * @param chaine l'expression arithmétique à compiler
     */
    public Analyseur(String chaine) {
        this.source = new Source(chaine);
    }

    /**
     * Point d'entrée du compilateur.
     * Génère les instructions ou affiche un message d'erreur.
     */
    public void compilateur() {
        if (expression()) {
            System.out.println("// fin du programme");
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
     * Génère l'instruction {@code ADD} si un opérateur {@code +} est présent.
     *
     * @return {@code true} si la somme est syntaxiquement correcte
     */
    private boolean somme() {
        if (!produit()) return false;
        if (source.premier() == '+') {
            if (!source.suivant()) return false;
            if (!somme()) return false;
            System.out.println("ADD");
        }
        return true;
    }

    /**
     * Analyse la règle {@code P -> T | T * P}.
     * Génère l'instruction {@code MUL} si un opérateur {@code *} est présent.
     *
     * @return {@code true} si le produit est syntaxiquement correct
     */
    private boolean produit() {
        if (!terme()) return false;
        if (source.premier() == '*') {
            if (!source.suivant()) return false;
            if (!produit()) return false;
            System.out.println("MUL");
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
     * Génère l'instruction {@code PUSH <valeur>}.
     *
     * @return {@code true} si le caractère courant est un chiffre
     */
    private boolean chiffre() {
        char c = source.premier();
        if (c >= '0' && c <= '9') {
            System.out.println("PUSH " + (c - '0'));
            source.suivant();
            return true;
        }
        return false;
    }
}
