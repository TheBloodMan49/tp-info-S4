package TP3.tp3;

import TP3.piles.*; // importe toutes les definitions du package piles

/**
 * Plonge.java
 *
 * Classe representant quelqu'un qui fait "la plonge",
 * utilisant une pile de vaisselle sale et une pile de vaisselle propre.
 *
 * @author Departement informatique
 * @author Paul Gasnier
 * @version 16.0202
 */
public class Plonge  {
	/* REMARQUE : attributs de type general Pile (!!!) */
	protected Pile sale;   // les ustensiles sales
	protected Pile propre; // les ustensiles propres

	public Plonge(Pile a, Pile b) {
		// on se met a la plonge sur les piles fournies
		sale=a;
		propre=b;
	}

	public void ajouterUnUstensile(Object u) {
		sale.empiler(u); // empile un ustensile a laver
	}

	public void laverUnSale() {
		Object u = sale.depiler();
		System.out.println("Lave ustensile "+u);
		
		// pour la question 4.
		// (en principe il faudrait plutôt modifier Pile/MaPile/SimplePile
		//  pour imposer Ustensile au lieu de Object; le code suivant
		//  est proposé pour simplifier le TP)
		if(u instanceof Ustensile)
		{
			((Ustensile)u).laver();
		} else {
			throw new ClassCastException();
		}
		
		propre.empiler(u);
	}

	public Object retirerUnPropre() {
		Object u = propre.depiler(); // sort un ustensile
		return u;
	}

	public void toutVider() {
		sale.viderPile();
		propre.viderPile();
	}

	public String toString() {
		return "Situation de la plonge :\n"+sale+propre;
	}

	public static void main(String[] args) {
		Plonge p = new Plonge( new MaPile(), new MaPile() );

		p.ajouterUnUstensile(new Assiette());
		p.ajouterUnUstensile(new Verre());
		p.ajouterUnUstensile(new Assiette());
		p.laverUnSale();
		p.laverUnSale();
		p.ajouterUnUstensile(new Assiette());
		Object u = p.retirerUnPropre();
		System.out.println(p + "\nUstensile retiré propre : "+u);
	}
}
