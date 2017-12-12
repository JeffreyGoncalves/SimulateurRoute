package jonction;
import route.Ligne;
import route.Segment;
import voiture.Voiture;

public class Barriere extends Jonction {
	
	public Barriere(String nom) {
		super(nom);
		lignes = new Ligne[1];
	}
	
	public Barriere() {
		super();
		lignes = new Ligne[1];
	}

	@Override
	public Segment sortiePour(Voiture occupant) {
		return lignes[0]; // segments est toujours de longueur 1 pour une barriere
	}

	
	@Override
	public String toString() {
		return nom + "(Barriere)";
	}
}
