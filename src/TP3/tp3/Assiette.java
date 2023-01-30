package TP3.tp3;

public class Assiette extends Ustensile {
    private static final int MAX_LAVAGES = 3; // Très fragiles ces assiettes

    public Assiette() {
        super();
    }

    public void laver() {
        if(this.nbLavages > MAX_LAVAGES) System.out.println("Assiette cassée");
        else {
            System.out.println("Lave une assiette");
            this.nbLavages++;
        }
    }

    public String toString() {
        return "Assiette";
    }
}
