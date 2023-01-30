package TP3.piles;

/**
 * Implémentation de l'interface Pile
 * @author Paul Gasnier
 */
public class MaPile implements Pile {
    private Object[] stack;
    private int pointer;

    public MaPile() {
        this(100);
    }
    public MaPile(int size) {
        this.stack = new Object[size];
        this.pointer = 0;
    }

    public Object depiler() {
        if(this.pointer >= 0) {
            this.pointer--;
            return this.stack[this.pointer];
        }
        else return null;
    }

    public void empiler(Object item) {
        if(this.pointer <= this.stack.length) {
            this.stack[this.pointer] = item;
            this.pointer++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean pileVide() {
        return this.pointer == 0;
    }

    public Object sommetPile() {
        if(this.pointer > 0) return this.stack[this.pointer-1];
        else return null;
    }

    public void viderPile() {
        this.pointer = 0;
    }

    public String toString() {
        String res = "[";
        for(int i = 0; i < this.pointer; i++) {
            if(i != 0) res+=", ";
            res+= this.stack[i].toString();
        }
        res+="]";
        return res;
    }
}
