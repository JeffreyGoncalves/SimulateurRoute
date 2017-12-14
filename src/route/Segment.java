package route;
import java.util.ArrayList;

import capteur.Capteur;
import exception.SegmentException;
import exception.VoitureException;
import voiture.Voiture;

public abstract class Segment {
	
	protected static Reseau reseau = new Reseau();
	protected ArrayList<Capteur> capteurs;
	protected ArrayList<Integer> posCapteurs;
	
	public Segment() {
		reseau.ajouterSegment(this);
	}
	
	public static Reseau getReseau() {
		return reseau;
	}
	
	public void addCapteur(Capteur capteur, int pos) {
		if (capteurs == null) {
			capteurs = new ArrayList<Capteur>();
			posCapteurs = new ArrayList<Integer>();
		}
		capteurs.add(capteur);
		posCapteurs.add(pos);
	}
	
	public boolean containsCapt() {
		return (capteurs != null);
	}

	//detecte une voiture se deplacant a l'interieur du segment
	public abstract void activerCapteurs(Voiture voiture, int pos1, int pos2);

	// detecte une voiture venant d'entrer dans le segment
	public abstract void activerCapteurs(Voiture voiture);
	
	// indique la sortie que doit prendre une voiture donnee, en supposant qu'elle occupe ce segment
	public abstract Segment sortiePour(Voiture occupant) throws VoitureException;
	
	public abstract void setSegmentArrivee(Segment segment) throws SegmentException;
	
	public abstract boolean estDirigeVers(Segment segment) throws SegmentException;

	public abstract int getLong();
	
	public abstract boolean containsSemaphore();
	
	public abstract void finirConstruction();
	
}
