package TP8;

import java.util.*;

public class Polynome {
    private List<Double> coeffs;

    public Polynome() {
        coeffs = new ArrayList<Double>();
    }

    public Polynome(List coeffs) {
        this.coeffs = new ArrayList<Double>(coeffs);
    }

    public Polynome(Polynome p) {
        this.coeffs = new ArrayList<Double>(p.coeffs);
    }

    public Polynome(double[] coeffs) {
        this();
        for (int i = 0; i < coeffs.length; i++) {
            this.coeffs.add(coeffs[i]);
        }
    }

    public double get(int i) {
        return this.coeffs.get(i);
    }

    public void set(int i, double value) {
        this.coeffs.set(i, value);
    }

    public int degree() {
        return this.coeffs.size() - 1;
    }

    public Polynome add(Polynome p) {
        Polynome result = new Polynome();
        int i = 0;
        while (i <= this.degree() && i <= p.degree()) {
            result.coeffs.add(this.get(i) + p.get(i));
            i++;
        }
        while (i <= this.degree()) {
            result.coeffs.add(this.get(i));
            i++;
        }
        while (i <= p.degree()) {
            result.coeffs.add(p.get(i));
            i++;
        }
        return result;
    }

    public void inc(int i, double value) {
        this.coeffs.set(i, this.coeffs.get(i) + value);
    }

    public Polynome mul(Polynome p) {
        Polynome result = new Polynome();
        for (int i = 0; i <= this.degree() + p.degree(); i++) {
            result.coeffs.add(0.0);
        }
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= p.degree(); j++) {
                result.inc(i + j, this.get(i) * p.get(j));
            }
        }
        return result;
    }

    public Polynome extract(int i, int j) {
        Polynome result = new Polynome();
        for (int k = i; k <= j; k++) {
            result.coeffs.add(this.get(k));
        }
        return result;
    }

    public Polynome mulK(Polynome p) {
        // TODO
        return new Polynome();
    }

    public String toString() {
        String result = "";
        for (int i = this.degree(); i >= 0; i--) {
            result += this.get(i) + (i > 0 ? "x^" + i + " + " : "");
        }
        return result;
    }

    public static void main(String[] args) {
        Polynome p1 = new Polynome(new double[] {1, 2, 3});
        Polynome p2 = new Polynome(new double[] {1, 2, 3, 4});
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.add(p2));
        System.out.println(p1.mul(p2));
    }
}
