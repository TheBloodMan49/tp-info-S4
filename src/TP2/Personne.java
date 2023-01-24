package TP2;
/**
 Classe representant une personne associee a son numero de telephone.

 @author Departement informatique
 @author Paul Gasnier
 @version TP-2, 24/01/2023
 */
public class Personne {

	/** Le numero de telephone */
	protected int tel;
	/** Le prenom */
	protected Mot prenom;
	/** Le nom */
	protected Mot nom;

	/** Constructeur à partir du prénom et du nom*/
	public Personne(String prenom, String nom) {
		this.nom = new Mot(nom);
		this.prenom = new Mot(prenom);
	}

	/**
	 * Constructeur à partir du prénom, du nom et du numéro de téléphone
	 * Utile pour les tests
	 */
	public Personne(String prenom, String nom, int tel) {
		this.nom = new Mot(nom);
		this.prenom = new Mot(prenom);
		this.tel = tel;
	}

	/** Associe le numero de telephone. */
	public void setTel(int tel) {
		this.tel = tel;
	}

	/** Fournit le numero de telephone. */
	public int getTel() {
		return this.tel;
	}

	/** Verifie si la personne est avant la personne p dans l'ordre alphabetique.
	 * @param p La personne a comparer
	 * @return true si la personne est avant p dans l'ordre alphabetique, false sinon
	 */
	public boolean estAvant(Personne p) {
		if (this.nom.estAvant(p.nom)) {
			return true;
		} else if (this.nom.equals(p.nom)) {
			return this.prenom.estAvant(p.prenom);
		} else {
			return false;
		}
	}
	
	/** Teste l'égalite du nom et du prenom */
	public boolean equals(Object o) {
		Personne p = (Personne) o;
		return this.nom.equals(p.nom) && this.prenom.equals(p.prenom);
	}

	/**
     Produit une chaine indiquant la personne. 
     (Par exemple Jules CESAR)
	 */
	public String toString() {
		return this.prenom.toString()+" "+this.nom.toString().toUpperCase();
	}

	/**
     Tests.
	 */
	public static void main (String[] args) {

		Personne toto = new Personne("Jean-Claude","Dusse");

		System.out.println(toto);
		System.out.println(toto.toString());

		toto.setTel(4321);
		System.out.println(toto.getTel());

		System.out.println(toto.equals(new Personne("Jean-Claude","DUSSE")));
		System.out.println(toto.equals(new Personne("Jean-Claude","Musse")));
		//System.out.println(toto.estAvant(new Personne("Jean-Claude","Van Damme")));
	}
}