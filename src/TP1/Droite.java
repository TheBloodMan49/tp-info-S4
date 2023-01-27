package TP1;

public class Droite {
    /**
     * Premier point de la droite
     */
    private Point p1;
    /**
     * Second point de la droite
     */
    private Point p2;

    /**
     * Constructeur à partir de deux points
     * @param p1 Premier point de la droite
     * @param p2 Second point de la droite
     */
    public Droite(Point p1, Point p2) {
        this.p1 = new Point(p1);
        if(p1.equals(p2))
            this.p2 = new Point(p1.getX()+1,p1.getY()+1);
        else
            this.p2 = new Point(p2);
    }

    /**
     * Contructeur à partir des coordonnées
     * Pas dans le diagramme UML mais cela facilite le test
     * @param x1 Abscisse du premier point
     * @param y1 Ordonnée du premier point
     * @param x2 Abscisse du second point
     * @param y2 Ordonnée du second point
     */
    public Droite(double x1,double y1,double x2,double y2) {
        this.p1 = new Point(x1,y1);
        this.p2 = new Point(x2,y2);
    }

    /**
     * Teste si deux droites sont parallèles
     * @param d Droite avec laquelle on teste le parallélisme
     * @return true si les droites sont parallèles, false sinon
     */
    public boolean parallele(Droite d) {
        return (new Vecteur(d.p1, d.p2)).parallele(new Vecteur(this.p1,this.p2));
    }

    /**
     * Teste si deux droites sont orthogonales
     * @param d Droite avec laquelle on teste l'orthogonalité
     * @return true si les droites sont orthogonales, false sinon
     */
    public boolean orthogonale(Droite d) {
        return (new Vecteur(d.p1,d.p2)).orthogonal(new Vecteur(this.p1,this.p2));
    }

    /**
     * Teste si un point appartient à la droite
     * @param p Point à tester
     * @return true si le point appartient à la droite, false sinon
     */
    public boolean contient(Point p) {
        return (new Vecteur(this.p1,p)).parallele(new Vecteur(this.p1,this.p2));
    }

    /**
     * Tests
     */
    public static void main(String[] args) {
        Droite d1 = new Droite(1,1,5,5);
        Point p = new Point(2,2);

        System.out.println(d1.contient(p));

        Droite d2 = new Droite(0,5,5,0);
        System.out.println(d2.orthogonale(d1));

        System.out.println(d2.contient(p));
    }
}
