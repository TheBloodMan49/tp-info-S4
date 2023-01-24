package TP2;

/**
 * Classe representant un ensemble de personnes trie par nom puis prenom.
 * @author Paul Gasnier
 * @version TP-2, 24/01/2023
 */
public class EnsembleTrieP {
    /**
     * Capacité initiale de l'ensemble
     */
    protected static final int CAPACITE_INITIALE = 10;

    /**
     * Cardinal de l'ensemble
     */
    protected int cardinal;

    /**
     * Tableau contenant les elements de l'ensemble
     */
    protected Personne[] elements;

    /**
     * Constructeur
     */
    public EnsembleTrieP() {
        this.elements = new Personne[CAPACITE_INITIALE];
        this.cardinal = 0;
    }

    /**
     * Renvoie le cardinal de l'ensemble
     * @return le cardinal de l'ensemble
     */
    public int card() {
        return this.cardinal;
    }

    /**
     * Ajoute une personne a l'ensemble
     * @param p la personne a ajouter
     */
    public void ajouter(Personne p) {
        if (this.cardinal < EnsembleTrieP.CAPACITE_INITIALE) {
            int i = 0;
            while (i < this.cardinal && this.elements[i].estAvant(p)) {
                i++;
            }
            for (int j = this.cardinal; j > i; j--) {
                this.elements[j] = this.elements[j - 1];
            }
            this.elements[i] = p;
            this.cardinal++;
        }
    }

    /**
     * Retire une personne de l'ensemble
     * @param p la personne a retirer
     */
    public void oter(Personne p) {
        int i = 0;
        while (i < this.cardinal && !this.elements[i].equals(p)) {
            i++;
        }
        if (i < this.cardinal) {
            for (int j = i; j < this.cardinal - 1; j++) {
                this.elements[j] = this.elements[j + 1];
            }
            this.cardinal--;
        }
    }

    /**
     * Renvoie la personne a la position i de l'ensemble
     * @param i la position de la personne a renvoyer
     * @return la personne a la position i de l'ensemble
     */
    public Personne getPersonne(int i) {
        return this.elements[i];
    }

    /**
     * Renvoie une chaine de caracteres representant l'ensemble
     * @return une chaine de caracteres representant l'ensemble
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < this.cardinal; i++) {
            s += this.elements[i] + ": " + this.elements[i].tel + ", ";
        }
        return s;
    }

    /**
     * Donne l'index d'une personne dans l'ensemble par recherche dichotomique
     * @param p la personne dont on cherche l'index
     * @return l'index de la personne dans l'ensemble ou -1 si la personne n'est pas dans l'ensemble
     */
    public int index(Personne p) {
        int i = 0;
        int j = this.cardinal - 1;
        int m = (i + j) / 2;
        while (i <= j && !this.elements[m].equals(p)) {
            if (this.elements[m].estAvant(p)) i = m + 1;
            else j = m - 1;

            m = (i + j) / 2;
        }
        if (i <= j) return m;
        else return -1;
    }

    /**
     * Renvoie l'intersection de deux ensembles
     * @param e l'ensemble avec lequel on veut l'intersection
     * @return l'intersection de deux ensembles
     */
    public EnsembleTrieP intersection(EnsembleTrieP e) {
        EnsembleTrieP inter = new EnsembleTrieP();
        for (int i = 0; i < this.cardinal; i++) {
            if (e.index(this.elements[i]) != -1) {
                inter.ajouter(this.elements[i]);
            }
        }
        return inter;
    }

    /**
     * Renvoie la reunion de deux ensembles
     * @param e l'ensemble avec lequel on veut la reunion
     * @return la reunion de deux ensembles ou null si la reunion est plus grande que la capacite initiale
     */
    public EnsembleTrieP union(EnsembleTrieP e) {
        if(this.cardinal + e.cardinal > EnsembleTrieP.CAPACITE_INITIALE) return null;

        EnsembleTrieP reu = new EnsembleTrieP();
        for (int i = 0; i < this.cardinal; i++) {
            reu.ajouter(this.elements[i]);
        }
        for (int i = 0; i < e.cardinal; i++) {
            reu.ajouter(e.elements[i]);
        }
        return reu;
    }

    /**
     * Teste si une personne est dans l'ensemble
     */
    public boolean contient(Personne p) {
        return this.index(p) != -1;
    }

    /**
     * Tests
     */
    public static void main(String[] args) {
        EnsembleTrieP e1 = new EnsembleTrieP();
        EnsembleTrieP e2 = new EnsembleTrieP();

        e1.ajouter(new Personne("Jean-Claude", "Dusse", 123456));
        e2.ajouter(new Personne("Victor", "Hugo", 654321));
        e1.ajouter(new Personne("George", "Sand"));
        e2.ajouter(new Personne("Victor", "Hugo"));
        e1.ajouter(new Personne("Victor", "Hugo"));

        System.out.println("e1 = " + e1);
        System.out.println("e2 = " + e2);
        System.out.println("e1 contient Victor Hugo ? " + e1.contient(new Personne("Victor", "Hugo")));

        EnsembleTrieP e3 = e1.union(e2);
        System.out.println("e3 = " + e3);
        EnsembleTrieP e4 = e1.intersection(e2);
        System.out.println("e4 = " + e4);

    }

}
