

public class JoncSimple extends Jonction {

	public JoncSimple(String nom) {
		super(nom);
		lignes = new Ligne[2];
	}
	
	@Override
	public String toString() {
		return nom + "(JoncSimple)";
	}
	
}
