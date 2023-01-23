package TP1;

/**
   Classe représentant un point du plan par ses deux coordonnées réelles.
   
   @author et autre indications à intégrer ici pour identifier votre TP (cf. sujet fourni).
*/
public class Point {

  private double x;		// Abscisse du point
  private double y;		// Ordonnée du point

  /** 
      Méthode d'accès à l'abscisse du point.
      @return l'abscisse du point.
  */
  public double getX() { return x; }

  /** 
      Méthode d'accès à l'ordonnée du point.
      #return l'ordonnée du point.
  */
  public double getY() { return y; }

  /** 
      Constructeur de point à partir de coordonnées x et y.
   */
  public Point(double vx, double vy) {
    x = vx;
    y = vy;
  }

  public Point(Point p) {
    this.x = p.x;
    this.y = p.y;
  }

  public Point() {
    this.x = 1;
    this.y = 1;
  }

  /** 
      Déplacement du point.
  */
  public void deplace(double dx, double dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Crée une copie du point p.
   * Le point obtenu est une nouvelle instance de Point et pas une 2ème référence à p.
   */
  public Point copie() {
    return new Point(this);
  }

  public Point barycentre(double a, double b, Point y) {
    double mx = (this.x*a + y.x*b)/(a+b);
    double my = (this.y*a + y.y*b)/(a+b);
    return new Point(mx,my);
  }

  public Point milieu(Point y) {
    return barycentre(1,1,y);
  }

  @Override
  public String toString() {
    return "x: "+this.x+", y: "+this.y ;
  }

  public boolean equals(Object o) {
    Point p = (Point) o;
    return (this.x == p.x) && (this.y == p.y);
  }

  public static void main(String[] args) {
    Point p1 = new Point(10, 10);
    Point p2 = new Point(20, 20);

    System.out.println(p1);
    System.out.println(p2);

    p2.deplace(1,1);
    System.out.println("p2 déplacé");
    System.out.println(p2);

    Point p3 = p1.barycentre(2,3,p2);
    System.out.println(p3);
  }
}
