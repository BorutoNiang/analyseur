/**
 * Contient la chaîne de caractères à analyser et l'indice du caractère courant.
 * <p>
 * Version 1 : aucune gestion du débordement.
 * </p>
 *
 * @author Groupe DIC1
 * @version 1.0
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
     * Retourne le caractère courant sans l'extraire (sans avancer l'indice).
     *
     * @return le caractère courant dans la chaîne
     */
    public char premier() {
        return chaine.charAt(indice);
    }

    /**
     * Avance l'indice d'une position (consomme le caractère courant).
     */
    public void suivant() {
        indice++;
    }
}
