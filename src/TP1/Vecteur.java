package TP1;
/**
 * Classe représentant un vecteur à 2 dimensions
 * @author Paul Gasnier
 * @version TP-1, 24/01/2023
 */

public class Vecteur {
    /**
     * Composante suivant x
     */
    private double dx;
    /**
     * Composante suivant y
     */
    private double dy;

    /**
     * Constructeur à partir des coordonnées
     * @param dx Composante suivant x
     * @param dy Composante suivant y
     */
    public Vecteur(double dx,double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructeur à partir de deux points
     * @param a Point de départ
     * @param b Point d'arrivée
     */
    public Vecteur(Point a, Point b) {
        this.dx = b.getX()-a.getX();
        this.dy = b.getY()-a.getY();
    }

    /**
     * Renvoie le produit scalaire de deux vecteurs
     * @param v Vecteur avec lequel on calcule le produit scalaire
     * @return Le produit scalaire
     */
    public double produitScalaire(Vecteur v) {
        return this.dx*v.dx + this.dy*v.dy;
    }

    /**
     * Renvoie le déterminant de deux vecteurs
     * @param v Vecteur avec lequel on calcule le déterminant
     * @return Le déterminant
     */
    public double determinant(Vecteur v) {
        return this.dx*v.dy - this.dy*v.dx;
    }

    /**
     * Teste si deux vecteurs sont parallèles
     * @param v Vecteur avec lequel on teste le parallélisme
     * @return true si les vecteurs sont parallèles, false sinon
     */
    public boolean parallele(Vecteur v) {
        return this.determinant(v) == 0;
    }

    /**
     * Teste si deux vecteurs sont orthogonaux
     * @param v Vecteur avec lequel on teste l'orthogonalité
     * @return true si les vecteurs sont orthogonaux, false sinon
     */
    public boolean orthogonal(Vecteur v) {
        return this.produitScalaire(v) == 0;
    }

    /**
     * Renvoie une chaîne de caractères représentant le vecteur
     * @return Une chaîne de caractères
     */
    @Override
    public String toString() {
        return "dx: "+this.dx+", dy: "+this.dy;
    }

    /**
     * Tests
     */
    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point(0,0);
        Vecteur v1 = new Vecteur(p1,p2);
        Vecteur v2 = new Vecteur(5,5);
        System.out.println(v2);
        if(v1.parallele(v2)) {
            System.out.println("v1 et v2 parallèles");
        } else if (v1.orthogonal(v2)) {
            System.out.println("v1 et v2 orthogonaux");
        } else {
            System.out.println("v1 et v2 ni parallèles ni orthogonaux");
        }
    }
}
