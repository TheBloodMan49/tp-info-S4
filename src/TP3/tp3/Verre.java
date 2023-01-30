package TP3.tp3;

public class Verre extends Ustensile {
    private static final int MAX_LAVAGES = 5;

    public Verre() { super(); }

    public void laver() {
        if(this.nbLavages > MAX_LAVAGES) System.out.println("Verre cassé");
        else {
            System.out.println("Bruits de verre en cours de lavage");
            this.nbLavages++;
        }
    }

    public String toString() {
        return "Verre";
    }
}
