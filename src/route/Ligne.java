package route;

import exception.JonctionException;
import exception.SegmentException;
import exception.VoitureException;
import jonction.Jonction;
import semaphore.Semaphore;
import voiture.Voiture;

public class Ligne extends Segment {
	private int longueur;
	private Jonction debut;
	private Jonction fin;
	private Semaphore sfin;
	private Semaphore sdebut;

	public Ligne(Jonction debut, Jonction fin, int longueur) throws SegmentException, JonctionException {
		super();
		if (debut == null || fin == null) {
			throw new JonctionException("Jonction non definie !!\n");
		} else {
			this.longueur = longueur;
			this.debut = debut;
			this.fin = fin;
			debut.ajouterLigne(this);
			fin.ajouterLigne(this);
			this.sfin = null;
			this.sdebut = null;
		}

	}

	public Jonction getDebut() {
		return debut;
	}

	public Jonction getFin() {
		return fin;
	}

	public Jonction getBout(boolean lequel) {
		return lequel ? debut : fin;
	}

	public void setBout(boolean lequel, Jonction bout) throws JonctionException {
		if (bout == null) {
			throw new JonctionException("Jonction non definie dans setBout");
		} else {
			if (lequel)
				debut = bout;
			else
				fin = bout;
		}
	}

	public int getLong() {
		return longueur;
	}

	@Override
	public Segment sortiePour(Voiture occupant) throws VoitureException {
		if (occupant == null) {
			throw new VoitureException("Cette voiture n'existe pas dans sortiePour !!\n");
		} else {
			return occupant.getSens() ? fin : debut;
		}

	}

	@Override
	public void setSegmentArrivee(Segment segment) {

	}

	@Override
	public boolean estDirigeVers(Segment segment) throws SegmentException {
		if (segment == null) {
			throw new SegmentException("Segment non defini dans estDirigevers !!\n");
		} else {
			return (segment == fin);
		}
	}

	@Override
	public String toString() {
		return "Route_de_" + debut + "_vers_" + fin;
	}

	@Override
	public void finirConstruction() {
		// Rien a faire pour une Ligne
	}

	// NEW fonction pour add un semaphore
	public void addSemaphore(Semaphore s, boolean b) {
		if (b) {
			setSfin(s);
		} else {
			setSdebut(s);
		}
	}

	public Semaphore getSfin() {
		return sfin;
	}

	public void setSfin(Semaphore sfin) {
		this.sfin = sfin;
		this.sfin.setSens(true);
	}

	public Semaphore getSdebut() {
		return sdebut;
	}

	public void setSdebut(Semaphore sdebut) {
		this.sdebut = sdebut;
		this.sdebut.setSens(false);
	}

	@Override
	public boolean containsSemaphore() {
		if (this.sdebut != null || this.sfin != null) {
			return true;
		} else {
			return false;
		}
	}
}
