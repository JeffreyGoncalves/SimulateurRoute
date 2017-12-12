package route;
import voiture.Voiture;

public abstract class Segment {
	
	private static Reseau reseau = new Reseau();
	
	public Segment() {
		reseau.ajouterSegment(this);
	}
	
	public static Reseau getReseau() {
		return reseau;
	}
	
	// indique la sortie que doit prendre une voiture donnee, en supposant qu'elle occupe ce segment
	public abstract Segment sortiePour(Voiture occupant);
	
	public abstract void setSegmentArrivee(Segment segment);
	
	public abstract boolean estDirigeVers(Segment segment);

	public abstract int getLong();
	
	public abstract boolean containsSemaphore();
	
	public abstract void finirConstruction();
	
}
