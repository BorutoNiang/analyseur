/**
 * Contient la chaîne de caractères à analyser et l'indice du caractère courant.
 * <p>
 * Version 2 : gestion du débordement ajoutée. {@link #premier()} retourne
 * {@code '\0'} et {@link #suivant()} retourne {@code false} en fin de chaîne.
 * </p>
 *
 * @author Groupe DIC1
 * @version 2.0
 */
public class Source {

    /** La chaîne de caractères à analyser. */
    private String chaine;

    /** L'indice du caractère courant. */
    private int indice;

    /**
     * Construit un objet Source à partir d'une chaîne à analyser.
     *
     * @param chaine la chaîne d'entrée à analyser
     */
    public Source(String chaine) {
        this.chaine = chaine;
        this.indice = 0;
    }

    /**
     * Vérifie que l'indice courant est dans les bornes de la chaîne.
     *
     * @return {@code true} si l'indice est valide
     */
    public boolean estValide() {
        return indice < chaine.length();
    }

    /**
     * Retourne le caractère courant sans l'extraire.
     * Retourne {@code '\0'} si la fin de la chaîne est atteinte.
     *
     * @return le caractère courant, ou {@code '\0'} en cas de débordement
     */
    public char premier() {
        if (!estValide()) return '\0';
        return chaine.charAt(indice);
    }

    /**
     * Avance l'indice d'une position.
     *
     * @return {@code true} si l'avance a réussi, {@code false} en cas de débordement
     */
    public boolean suivant() {
        if (!estValide()) return false;
        indice++;
        return true;
    }
}
