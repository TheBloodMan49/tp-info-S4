package TP2;

/**
 * Classe representant un annuaire.
 * @author Paul Gasnier
 * @version TP-2, 24/01/2023
 */
public class Annuaire extends EnsembleP {
    /**
     *  Constructeur
     */
    public Annuaire() {
        super();
    }

    /**
     * Renvoie une representation String de l'annuaire
     * @return Une chaine de caractères
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < this.cardinal; i++) {
            s += this.elements[i] + ": " + this.elements[i].tel + ", ";
        }
        return s;
    }

    /**
     * Renvoie le numéro de téléphone d'une personne
     * @param prenom Le prénom de la personne
     * @param nom Le nom de la personne
     * @return Le numéro de téléphone de la personne ou -1 si la personne n'est pas dans l'annuaire
     */
    public int tel(String prenom, String nom) {
        Personne p = new Personne(prenom, nom);
        int i = 0;
        while (i < this.cardinal && !this.elements[i].equals(p)) {
            i++;
        }
        if (i < this.cardinal) {
            return this.elements[i].tel;
        } else {
            return -1;
        }
    }

    /**
     * Renvoie le nom et prénom d'une personne à partir de son numéro de téléphone
     * @param tel Le numéro de téléphone de la personne
     * @return Le nom et prénom de la personne ou "Inconnu" si la personne n'est pas dans l'annuaire
     */
    public Personne telInverse(int tel) {
        int i = 0;
        while (i < this.cardinal && this.elements[i].tel != tel) {
            i++;
        }
        if (i < this.cardinal) {
            return this.elements[i];
        } else {
            return new Personne("Inconnu", "");
        }
    }

    /**
     * Tests.
     */
    public static void main(String[] args) {
        // Creation d'un annuaire
        Annuaire annuaire = new Annuaire();
        // Ajout de personnes
        annuaire.ajouter(new Personne("Jean-Claude", "Dusse", 123456));
        annuaire.ajouter(new Personne("Victor", "Hugo", 654321));

        // Affichage de l'annuaire
        System.out.println(annuaire);

        // Recherche du numéro de téléphone de Jean-Claude Dusse
        System.out.println(annuaire.tel("Jean-Claude", "Dusse"));
        // Recherche du numéro de téléphone 654321
        System.out.println(annuaire.telInverse(654321));
        // Recherche du numéro de téléphone de Jean-Claude Hugo
        System.out.println(annuaire.tel("Jean-Claude", "Hugo"));
    }
}
