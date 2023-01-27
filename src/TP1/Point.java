package TP1;

/**
   Classe représentant un point du plan par ses deux coordonnées réelles.
   
   @author Paul Gasnier
   @version TP-1, 24/01/2023
*/
public class Point {

  /**
   * Abscisse du point
   */
  private double x;
  /**
   * Ordonnée du point
   */
  private double y;

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

  /**
   * Constructeur par recopie
   * @param p Point à recopier
   */
  public Point(Point p) {
    this.x = p.x;
    this.y = p.y;
  }

  /**
   * Constructeur par défaut (1,1)
   */
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

  /**
   * Renvoie le barycentre entre le point courant et le point spécifié,
   * pondéré par les paramètres a et b.
   * @param a Coefficient pour le point courant
   * @param b Coefficient pour le point spécifié
   * @param y Le point spécifié
   * @return Point barycentre
   */
  public Point barycentre(double a, double b, Point y) {
    double mx = (this.x + y.x)*b/(a+b);
    double my = (this.y + y.y)*b/(a+b);
    return new Point(mx,my);
  }

  /**
   * Renvoie le milieu du point courant et du point spécifié
   * @param y Second point
   * @return Point milieu
   */
  public Point milieu(Point y) {
    return barycentre(1,1,y);
  }

  /**
   * Formate le point en chaîne de caractères
   * @return Chaîne de caractères
   */
  @Override
  public String toString() {
    return "x: "+this.x+", y: "+this.y ;
  }

  /**
   * Teste l'égalité entre le point courant et le point spécifié
   * @param o Point à comparer
   * @return true si les points sont égaux, false sinon
   */
  public boolean equals(Object o) {
    Point p = (Point) o;
    return (this.x == p.x) && (this.y == p.y);
  }

  /**
   * Tests.
   */
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
