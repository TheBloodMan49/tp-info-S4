package TP1;

public class Vecteur {
    private double dx;
    private double dy;

    public Vecteur(double dx,double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Vecteur(Point a, Point b) {
        this.dx = b.getX()-a.getX();
        this.dy = b.getY()-a.getY();
    }

    public double produitScalaire(Vecteur v) {
        return this.dx*v.dx + this.dy*v.dy;
    }

    public double determinant(Vecteur v) {
        return this.dx*v.dy - this.dy*v.dx;
    }

    public boolean parallele(Vecteur v) {
        return this.determinant(v) == 0;
    }

    public boolean orthogonal(Vecteur v) {
        return this.produitScalaire(v) == 0;
    }

    @Override
    public String toString() {
        return "dx: "+this.dx+", dy: "+this.dy;
    }

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
