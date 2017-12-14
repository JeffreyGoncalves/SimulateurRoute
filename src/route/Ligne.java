package route;

import jonction.Jonction;
import semaphore.Semaphore;
import voiture.Voiture;

public class Ligne extends Segment {
	private int longueur;
	private Jonction debut;
	private Jonction fin;
	private Semaphore sFin;
	private Semaphore sDebut;

	public Ligne( Jonction debut, Jonction fin, int longueur) {
		super();
		this.longueur = longueur;
		this.debut = debut;
		this.fin = fin;
		debut.ajouterLigne(this);
		fin.ajouterLigne(this);
		this.sFin = null;
		this.sDebut = null;
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

	public void setBout(boolean lequel, Jonction bout) {
		if (lequel)
			debut = bout;
		else
			fin = bout;
	}

	public int getLong() {
		return longueur;
	}

	@Override
	public Segment sortiePour(Voiture occupant) {
		return occupant.getSens() ? fin : debut;
	}

	@Override
	public void setSegmentArrivee(Segment segment) {

	}

	@Override
	public boolean estDirigeVers(Segment segment) {
		return (segment == fin);
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
		return sFin;
	}

	public void setSfin(Semaphore sfin) {
		this.sFin = sfin;
		this.sFin.setSens(true);
	}

	public Semaphore getSdebut() {
		return sDebut;
	}

	public void setSdebut(Semaphore sdebut) {
		this.sDebut = sdebut;
		this.sDebut.setSens(false);
	}

	@Override
	public void activerCapteurs(Voiture voiture, int pos1, int pos2) {
		if (posCapteurs == null)
			return;
		int pos1Retour = longueur - 1 - pos1;
		int pos2Retour = longueur - 1 - pos2;
		for (int i = 0; i < posCapteurs.size(); ++i) {
			if ((voiture.getSens() && posCapteurs.get(i) >= pos1 && posCapteurs.get(i) <= pos2)
					|| (!voiture.getSens() && posCapteurs.get(i) >= pos2Retour && posCapteurs.get(i) <= pos1Retour))
				capteurs.get(i).detecter(voiture);
		}
	}

	@Override
	public void activerCapteurs(Voiture voiture) {
		if (posCapteurs == null)
			return;
		for (int i = 0; i < posCapteurs.size(); ++i) {
			if ((voiture.getSens() && posCapteurs.get(i) == 0)
					|| (!voiture.getSens() && posCapteurs.get(i) == longueur - 1))
				capteurs.get(i).detecter(voiture);
		}
	}

	@Override
	public boolean containsSemaphore() {
		return (sDebut != null || sFin != null);
	}

}
