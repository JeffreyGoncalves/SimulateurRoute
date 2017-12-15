package jonction;
import route.Ligne;

public class JoncSimple extends Jonction {

	public JoncSimple(String nom) {
		super(nom);
		branches = new Ligne[2];
	}
	
	@Override
	public String descrLongue() {
		return nom + "(JoncSimple)";
	}
	
}
