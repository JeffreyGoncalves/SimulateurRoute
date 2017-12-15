package jonction;
import exception.VoitureException;
import route.Ligne;
import route.Segment;
import voiture.Voiture;

public class Barriere extends Jonction {
	
	public Barriere(String nom) {
		super(nom);
		branches = new Ligne[1];
	}
	
	public Barriere() {
		super();
		branches = new Ligne[1];
	}

	@Override
	public Segment sortiePour(Voiture occupant) throws  VoitureException {
		if (occupant == null)
			throw new VoitureException("Voiture non definie dans SortiePour");
		else
			return branches[0]; // segments est toujours de longueur 1 pour une barriere

	}

	@Override
	public String descrLongue() {
		return nom + "(Barriere)";
	}
	
	

}
