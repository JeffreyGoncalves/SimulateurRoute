package jonction;
import route.Ligne;

public class Carrefour extends Jonction {

	public Carrefour(String nom) {
		super(nom);
		lignes = new Ligne[3];
	}
	
	@Override
	public String toString() {
		return nom + "(Carrefour)";
	}
	
}
