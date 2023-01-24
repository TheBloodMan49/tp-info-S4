package TP2;
/**
 Classe representant un ensemble de personnes.
 
 @author Departement informatique
 @author Paul Gasnier
 @version TP-1, 24/01/2023
 */
public class EnsembleP {
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
    public EnsembleP() {
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
        if (this.cardinal < this.CAPACITE_INITIALE) {
            this.elements[this.cardinal] = p;
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
            this.elements[i] = this.elements[this.cardinal - 1];
            this.cardinal--;
        }
    }

    /**
     * Teste si une personne appartient a l'ensemble
     * @param p la personne a tester
     * @return true si la personne appartient a l'ensemble, false sinon
     */
    public boolean contient(Personne p) {
        int i = 0;
        while (i < this.cardinal && !this.elements[i].equals(p)) {
            i++;
        }
        return i < this.cardinal;
    }

    /**
     * Fait l'union de deux ensembles
     * @param e l'ensemble a ajouter
     * @return l'union des deux ensembles ou null si la capacite est depassee
     */
    public EnsembleP union(EnsembleP e) {
        if(this.cardinal + e.cardinal > this.CAPACITE_INITIALE) {
            return null;
        }
        EnsembleP res = new EnsembleP();
        for (int i = 0; i < this.cardinal; i++) {
            res.ajouter(this.elements[i]);
        }
        for (int i = 0; i < e.cardinal; i++) {
            res.ajouter(e.elements[i]);
        }
        return res;
    }

    /**
     * Fait l'intersection de deux ensembles
     * @param e l'ensemble a ajouter
     * @return l'intersection des deux ensembles
     */
    public EnsembleP intersection(EnsembleP e) {
        EnsembleP res = new EnsembleP();
        for (int i = 0; i < this.cardinal; i++) {
            if (e.contient(this.elements[i])) {
                res.ajouter(this.elements[i]);
            }
        }
        return res;
    }

    /**
     * Renvoie une representation String de l'ensemble
     */
    public String toString() {
        String res = "{";
        for (int i = 0; i < this.cardinal; i++) {
            res += this.elements[i].toString();
            if (i < this.cardinal - 1) {
                res += ", ";
            }
        }
        res += "}";
        return res;
    }

    /**
     * Tests.
     */
    public static void main(String[] args) {
        EnsembleP e1 = new EnsembleP();
        EnsembleP e2 = new EnsembleP();

        e1.ajouter(new Personne("Michel", "Drucker"));
        e1.ajouter(new Personne("Victor", "Hugo"));
        e2.ajouter(new Personne("George", "Sand"));
        e2.ajouter(new Personne("Victor", "Hugo"));
        // Ajouter des numéros de téléphone
        e1.elements[0].setTel(01234567);

        System.out.println("e1 = " + e1);
        System.out.println("e2 = " + e2);
        System.out.println("e1 contient Victor Hugo ? " + e1.contient(new Personne("Victor", "Hugo")));

        EnsembleP e3 = e1.union(e2);
        System.out.println("e3 = " + e3);
        EnsembleP e4 = e1.intersection(e2);
        System.out.println("e4 = " + e4);

    }
}
