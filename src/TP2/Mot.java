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
