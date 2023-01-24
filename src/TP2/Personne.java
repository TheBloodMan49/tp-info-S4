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

	/** Constructeur */
	public Personne(String prenom, String nom) {
		this.nom = new Mot(nom);
		this.prenom = new Mot(prenom);
	}

	/** Associe le numero de telephone. */
	public void setTel(int tel) {
		this.tel = tel;
	}

	/** Fournit le numero de telephone. */
	public int getTel() {
		return this.tel;
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