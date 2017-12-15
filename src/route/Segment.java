package route;

import java.util.ArrayList;

import capteur.Capteur;
import exception.JonctionException;
import exception.SegmentException;
import exception.VoitureException;
import semaphore.Semaphore;
import voiture.Voiture;

public abstract class Segment {
	
	protected static Reseau reseau = new Reseau();
	protected ArrayList<Capteur> capteurs;
	
	public Segment() {
		reseau.ajouterSegment(this);
	}
	
	public static Reseau getReseau() {
		return reseau;
	}
	
	public void addCapteur(Capteur capteur) {
		if (capteurs == null) {
			capteurs = new ArrayList<Capteur>();
		}
		capteurs.add(capteur);
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
	
	public abstract void finirConstruction() throws SegmentException, JonctionException;
	
	public abstract String descrLongue();

	public abstract boolean containsSemaphore();
	
	public abstract ArrayList<Semaphore> getSemaphores();
	
}
