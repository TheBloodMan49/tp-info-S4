package TP3.piles;

public interface Pile {
    Object depiler();
    void empiler(Object item);
    boolean pileVide();
    Object sommetPile();
    void viderPile();
}
