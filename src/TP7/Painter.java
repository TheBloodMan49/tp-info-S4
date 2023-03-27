package TP7;

import java.io.*;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * 
 * Classe impl�mentant l'algorithme du peintre pour une forme d�finie comme un
 * ensemble de facettes
 *
 */
public class Painter {
	/**
	 * forme � repr�senter par l'algo du peintre
	 */
	private Shape forme;

	/**
	 * Point � partir duquel la forme est observ�e
	 */
	private Point origin;

	/**
	 * Constructeur qui cr�e une forme vide Le point d'observation est fix� par
	 * d�faut en 200,200,0
	 */
	public Painter() {

		forme = new Shape();
		origin = new Point(200, 200, 0);
	}

	/**
	 * Constructeur qui prend un poitn d'observation en param�tre
	 * 
	 * @param p
	 */
	public Painter(Point p) {

		forme = new Shape();
		origin = p;
	}

	/**
	 * Affichage de la forme sur l'afficheur - recup�ration de la liste de
	 * facette - iteration sur chaque facette pour la faire afficher sur -
	 * l'afficheur. Attention, le r�sultat affich� ne sera conforme � ce que
	 * l'on attend que si les facettes sont d�j� tri�es en fonction de leur
	 * distance
	 */
	public void drawShape(ShapeDisplayer jap) {
		for (int i = forme.nbfac()-1; i >= 0; i--) {
			jap.displayFacette(forme.getFacette(i));
		}
	}

	public static void main(String args[]) {

		// Partie du main qui initialise l'afficheur
		// Ne pas modifier ces lignes

		JFrame f = new JFrame("Sorting and painting");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ShapeDisplayer applet = new ShapeDisplayer();

		f.getContentPane().add("Center", applet);

		applet.init();

		f.pack();
		f.setSize(new Dimension(600, 600));
		f.setVisible(true);
		// fin de l'initialisation de l'afficheur

		Point org = new Point(200, 200, 0);
		Painter p = new Painter(org);
		// Nouvelle instance de painter

		/*
		p.forme.load("assets/TP7/tore-unsorted.txt");// lecture d'une forme dans un fichier
		// p.forme.random(120);
		p.forme.computeBarycentres();
		p.forme.computeDistances(p.origin);
		p.forme.setGreyLevels();
		// D�commenter ce qui suit pour trier les facettes
		p.forme.trieFacettes();

		applet.clear();// efface la fenetre
		p.drawShape(applet); // dessine la forme
		*/

		// d�commenter ce qui suit pour sauver la forme dans un ficheir
		// p.forme.save("random.txt");

		// Q9
		// d�commenter ce qui suit pour faire une forme al�atoire
		p.forme.random(120);
		p.forme.computeBarycentres();
		p.forme.computeDistances(p.origin);
		p.forme.setGreyLevels();
		// D�commenter ce qui sui tpour trier les facettes
		p.forme.trieFacettes();

		applet.clear();// efface la fenetre
		p.drawShape(applet); // dessine la forme

	}

	/**
	 * Permet d'attendre une saisie clavier entre deux affichages d'images
	 */
	public void pause() {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			br.readLine();
		} catch (Exception e) {
		}
	}

}
