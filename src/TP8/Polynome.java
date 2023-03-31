package TP8;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Polynome {
    private SortedMap<Integer, Double> coeffs;

    public Polynome() {
        this.coeffs = new TreeMap<Integer, Double>(Collections.reverseOrder());
    }

    public Polynome(Polynome p) {
        this();
        this.coeffs.putAll(p.coeffs);
    }

    public double get(int i) {
        if (!this.coeffs.containsKey(i))
            return 0.0;
        else
            return this.coeffs.get(i);
    }

    public void set(int i, double value) {
        this.coeffs.put(i, value);
    }

    public int degree() {
        return this.coeffs.lastKey();
    }

    public Polynome add(Polynome p) {
        Polynome result = new Polynome(this);
        for (Entry<Integer, Double> entry
                : p.coeffs.entrySet()) {
            result.inc(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public Polynome sub(Polynome p) {
        Polynome result = new Polynome(this);
        for (Entry<Integer, Double> entry : p.coeffs.entrySet()) {
            result.inc(entry.getKey(), -entry.getValue());
        }
        return result;
    }

    public void inc(int i, double value) {
        this.coeffs.put(i, this.get(i) + value);
    }

    public Polynome mul(Polynome p) {
        Polynome result = new Polynome();
        for (Entry<Integer, Double> entry1 : this.coeffs.entrySet()) {
            for (Entry<Integer, Double> entry2 : p.coeffs.entrySet()) {
                result.inc(entry1.getKey() + entry2.getKey(), entry1.getValue() * entry2.getValue());
            }
        }
        return result;
    }

    public Polynome extract(int start, int length) {
        Polynome result = new Polynome();
        for (int k = start; k < start+length; k++) {
            if (this.get(k) != 0) result.coeffs.put(k-start, this.get(k));
        }
        return result;
    }

    public Polynome leftShift(int n) {
        Polynome result = new Polynome();
        for (Entry<Integer, Double> entry : this.coeffs.entrySet()) {
            result.coeffs.put(entry.getKey() + n, entry.getValue());
        }
        return result;
    }

    public Polynome mulK(Polynome p) {
        int n = Math.max(this.degree(), p.degree()) + 1;
        return this.mulKRec(p, n);
    }

    // Recursive method to multiply two polynomials using Divide and Conquer
    public Polynome mulKRec(Polynome p, int n) {
        if (n == 1) {
            return this.mul(p);
        }
        Polynome P0 = this.extract(0, n/2);
        Polynome P1 = this.extract(n/2, n - n/2);

        Polynome Q0 = p.extract(0, n/2);
        Polynome Q1 = p.extract(n/2, n - n/2);

        Polynome P0Q0 = P0.mulKRec(Q0, n/2);
        Polynome P1Q1 = P1.mulKRec(Q1, n - n/2);
        Polynome P0sumP1 = P0.add(P1);
        Polynome Q0sumQ1 = Q0.add(Q1);

        Polynome middlebit = P0sumP1.mulKRec(Q0sumQ1, n/2).sub(P0Q0).sub(P1Q1);

        return P0Q0.add(middlebit.leftShift(n/2)).add(P1Q1.leftShift(n));

    }

    public String toString() {
        String result = "";
        for (Entry<Integer, Double> entry : this.coeffs.entrySet()) {
            result += entry.getValue() + "x^" + entry.getKey() + " + ";
        }
        return result;
    }

    public static void main(String[] args) {
        Polynome p1 = new Polynome();
        Polynome p2 = new Polynome();

        p1.set(0,2);
        p1.set(2,1);

        p2.set(0,2);
        p2.set(1,3);
        p2.set(2,2);

        System.out.println(p1.degree());
        System.out.println(p1);
        System.out.println(p1.get(1));
        Polynome p3 = p1.add(p2);
        System.out.println(p3);
        System.out.println(p1.mulK(p3));
        System.out.println(p1.mul(p3));
    }
}
