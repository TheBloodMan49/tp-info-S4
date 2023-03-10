package TP2;
/**
 Classe representant un mot.
 
 @author Departement informatique
 @author Paul Gasnier
 @version TP-2, 24/01/2023
 */
class Mot {
	/** Le texte du mot */
	protected String texte;

	/** Constructeur */
	public Mot(String texte){
		this.texte = new String(texte);
	}

	/**
	 * Retourne vrai si le mot est avant le mot m dans l'ordre alphabetique.
	 * @param m Le mot a comparer
	 * @return true si le mot est avant m dans l'ordre alphabetique, false sinon
	 */
	public boolean estAvant(Mot m) {
		return this.texte.compareToIgnoreCase(m.texte) < 0;
	}

	/** Teste l'egalite, insensible a la casse */
	public boolean equals(Object o) {
		Mot m = (Mot)o;
		return this.texte.equalsIgnoreCase(m.texte);
	}

	/** Retourne une version String du mot */
	public String toString() {
        return this.texte;
	}

	/**
    Tests.
	 */
	public static void main(String[] args) {
		Mot y = new Mot("Yann");
		
		System.out.println("egal a YANN ? " + y.equals(new Mot("YANN")));
        System.out.println(y);
	}
}
