package TP1;

public class Droite {
    private Point p1;
    private Point p2;

    public Droite(Point p1, Point p2) {
        this.p1 = new Point(p1);
        this.p2 = new Point(p2);
    }

    /**
     * Pas dans le diagramme UML mais c'est plus pratique pour tester
     */
    public Droite(double x1,double y1,double x2,double y2) {
        this.p1 = new Point(x1,y1);
        this.p2 = new Point(x2,y2);
    }

    public boolean parallele(Droite d) {
        return (new Vecteur(d.p1, d.p2)).parallele(new Vecteur(this.p1,this.p2));
    }

    public boolean orthogonale(Droite d) {
        return (new Vecteur(d.p1,d.p2)).orthogonal(new Vecteur(this.p1,this.p2));
    }

    public boolean contient(Point p) {
        return (new Vecteur(this.p1,p)).parallele(new Vecteur(this.p1,this.p2));
    }

    public static void main(String[] args) {
        Droite d1 = new Droite(1,1,5,5);
        Point p = new Point(2,2);

        System.out.println(d1.contient(p));

        Droite d2 = new Droite(0,5,5,0);
        System.out.println(d2.orthogonale(d1));

        System.out.println(d2.contient(p));
    }
}
