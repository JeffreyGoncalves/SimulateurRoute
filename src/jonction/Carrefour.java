package jonction;
import route.Ligne;

public class Carrefour extends Jonction {

	public Carrefour(String nom, int nbBranches) {
		super(nom);
		branches = new Ligne[nbBranches];
	}
	
	public Carrefour(int nbBranches) {
		super();
		branches = new Ligne[nbBranches];
	}
	
	@Override
	public String descrLongue() {
		return nom + "(Carrefour)";
	}
	
}
