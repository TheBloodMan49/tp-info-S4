package TP7;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Shape : classe implémentant la notion de forme une shape est principalement
 * un ensemble de facettes
 */
public class Shape {
	/**
	 * Nombre Maximal de facettes
	 */
	private static final int MAX_FAC = 2000;
	/**
	 * Table contenant des facettes
	 */
	private Facette[] tab;
	/**
	 * Current number of facettes in the table
	 */
	private int nbfac;

	// Constructeurs

	/**
	 * creates a table of at most MAX_FAC facettes The table is originally empty
	 */
	public Shape() {
		tab = new Facette[MAX_FAC];
		nbfac = 0;
	}

	/**
	 * ree une table de MAX_FAC facettes remplie de nbf facettes aléatoires
	 */
	public Shape(int nbf) {
		// TODO
		// Q9
	}

	/**
	 * renvoie la facette no i
	 */
	public Facette getFacette(int i) {
		return tab[i];
	}

	/**
	 * @return  renvoie le nombre de facettes
	 */
	public int nbfac() {
		return nbfac;

	}

	/**
	 * Ajoute une facette à la forme
	 * @param fac est la facette ajoutée
	 */
	public void add(Facette fac) {
		tab[nbfac] = fac;
		nbfac++;
	}

	/**
	 * Genere nb facettes dans la forme de manière aléatoire
	 * @param nb est le nombre de facettes générées
	 */
	public void random(int nb) {

		// TODO
		// Q9
	}

	/**
	 * charge une description donnée comme une liste de facette dans un fichier
	 * dont le nom est donné en paramètre
	 */
	public void load(String filename) {

		try {
			FileReader ir = new FileReader(filename);
			System.out.println("File opened");

			BufferedReader br = new BufferedReader(ir);

			while (br.ready()) {

				Facette fac = new Facette(br.readLine());
				this.add(fac);
				System.out.println("facette created");

			}
			br.close();
		} catch (IOException e) {
			System.out.println("error when parsing " + filename);
		}
	}

	/**
	 * sauve une forme dans le fichier dont le nom est passé en paramètre
	 * @param filename : nom du fichier
	 */
	public void save(String filename) {
		// sauve une description donnée comme une liste de facette
		try {
			FileWriter wr = new FileWriter(filename);
			System.out.println("File opened");

			for (int i = 0; i < nbfac; i++) {

				Facette fac = tab[i];
				String s = "" + (int) fac.getP1().x() + " " + (int) fac.getP1().y()
						+ " " + (int) fac.getP1().z() + " " + (int) fac.getP1().x() + " "
						+ (int) fac.getP2().y() + " " + (int) fac.getP2().z() + " "
						+ (int) fac.getP3().x() + " " + (int) fac.getP3().y() + " "
						+ (int) fac.getP3().z() + "\n";

				wr.write(s);

			}
			wr.close();

		} catch (IOException e) {
			System.out.println("error when parsing " + filename);
		}
	}

	/**
	 * calcule un barycentre pour toutes les facettes
	 */
	public void computeBarycentres() {
		for (int i = 0; i < nbfac; i++) {
			tab[i].barycentre();
		}
	}

	/**
	 * calcule la distance de chaque facette au point origin
	 */
	public void computeDistances(Point origin) {
		for (int i = 0; i < nbfac; i++) {
			tab[i].distance(origin);
		}
	}

	/**
	 * renvoie la distance maximale trouvée dans la liste de facettes attention,
	 * les distances doivent avoir été caclulées
	 */
	public double findMaxDistance() {
		if (nbfac == 0)
			return 0;

		double max = tab[0].getDistance();
		for (int i = 1; i < nbfac; i++) {
			if (tab[i].getDistance() > max)
				max = tab[i].getDistance();
		}
		return max;
	}

	/**
	 * renvoie la dstance minimale trouvée dans la liste de facettes attention,
	 * les distances doivent avoir été caclulées
	 */
	public double findMinDistance() {
		if (nbfac == 0)
			return 0;

		double min = tab[0].getDistance();
		for (int i = 1; i < nbfac; i++) {
			if (tab[i].getDistance() < min)
				min = tab[i].getDistance();
		}
		return min;
	}

	/**
	 * donne un niveau de gris entre 50 (sombre) et 220 (blanc) a toute facette.
	 * Les facettes les plus proches du point origine qui a servi a calculer les
	 * distances doivent être claires, les plus éloignées sombres attention, les
	 * distances doivent avoir été caclulées
	 */
	public void setGreyLevels() {
		double max = findMaxDistance();
		double min = findMinDistance();
		double delta = max - min;
		for (int i = 0; i < nbfac; i++) {
			double d = tab[i].getDistance();
			int grey = (int) (50 + (d - min) * 170 / delta);
			tab[i].setColor(grey);
		}
	}

	/**
	 * trie les facettes de la forme coutrante en fonction de la distance à
	 * l'origine on utilisera un des algorithmes vus en cours
	 */
	public void trieFacettes() {

	}

	/**
	 * rotation d'une forme en XY Se fait en faisant tourner tous les points de
	 * la forme autour d'un point donné comme centre de la rotation attention,
	 * chaque point ne doit tourner qu'une fois, et des points peuvent être
	 * partagés entre plusieurs facettes
	 * 
	 * @param p
	 *            : Centre de la rotation
	 * @param angle
	 *            : angle de rotation en degrés
	 */
	public void rotation(Point p, double angle) {

		/*
		 * Liste de point apparaissant dans la forme Utilisé pour éviter de
		 * faire tourner plusieurs fois le même point
		 */
		Set<Point> pointList = new HashSet<Point>();

		Facette f;

		for (int i = 0; i < this.nbfac; i++) {
			f = this.tab[i];

			if (!pointList.contains(f.getP1()) ) {
				f.getP1().rotation(p, angle);
				pointList.add(f.getP1());
			}
			if (!pointList.contains(f.getP2())) {
				f.getP2().rotation(p, angle);
				pointList.add(f.getP2());
			}
			if (!pointList.contains(f.getP3())) {
				f.getP3().rotation(p, angle);
				pointList.add(f.getP3());
			}
		}
	}

	/**
	 * rotation d'une forme en XZ Se fait en faisant tourner tous les points de
	 * la forme autour d'un point donné comme centre de la rotation attention,
	 * chaque point ne doit tourner qu'une fois, et des points peuvent être
	 * partagés entre plusieurs facettes
	 * 
	 * @param p
	 *            : Centre de la rotation
	 * @param angle
	 *            : angle de rotation en degrés
	 */
	public void rotationXZ(Point p, double angle) {

		Set<Point> pointList = new HashSet<Point>();
	
		for (int i = 0; i < this.nbfac; i++) {
			Facette f = this.tab[i];

			if (!pointList.contains(f.getP1())) {
				f.getP1().rotationXZ(p, angle);
				pointList.add(f.getP1());
			}
			if (!pointList.contains(f.getP2())) {
				f.getP2().rotationXZ(p, angle);
				pointList.add(f.getP2());
			}
			if (!pointList.contains(f.getP3())) {
				f.getP3().rotationXZ(p, angle);
				pointList.add(f.getP3());
			}
		}
	}

	/**
	 * change de manière aléatoire l'ordre des facettes de la forme
	 */
	public void shuffle() {
		int i;
		ArrayList<Facette> l = new ArrayList<Facette>();
		for (i = 0; i < nbfac; i++) {
			l.add(tab[i]);
		}
		Collections.shuffle(l);
		i = 0;
		for (Facette f : l) {
			tab[i] = f;
			i++;

		}

	}

}// Fin de la classe
