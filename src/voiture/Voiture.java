package voiture;

import exception.SegmentException;
import exception.VoitureException;
import route.Ligne;
import route.Segment;
import semaphore.Limitation;
import semaphore.Semaphore;
import status.Action;

public class Voiture {

	private static int prochainID = 0;
	private int id;
	private int position;
	private int vitesseAct;
	private int vitesseMax; // vitesse physiquement atteignable par la voiture
	private int vitesseAutorisee; // vitesse maximale selon les panneaux de  limitation de vitesse
	private int distRestante;
	private boolean sens;
	private Segment segAct;
	private Segment segPrec;
	private boolean peutSortir = true;

	public Voiture(int position, int vitesseMax, boolean sens, Segment segAct) {
		id = prochainID++;
		this.position = position;
		this.vitesseMax = vitesseMax;
		this.segAct = segAct;
	}

	public void avancer() throws SegmentException {
		
		distRestante = vitesseAct = vitesseAutorisee = vitesseMax;
		do {
			if (segAct.containsSemaphore()) {
				if (sens && ((Ligne)segAct).getSfin() != null)
					reactSignal(((Ligne)segAct).getSfin());
				else if (!sens && ((Ligne)segAct).getSdebut() != null)
					reactSignal(((Ligne)segAct).getSdebut());
			}
			if (position + vitesseAct < segAct.getLong()) {
				setPosition(position + distRestante);
				distRestante = 0;
			} else {
				if (peutSortir) {
					distRestante -= segAct.getLong() - position;
					try {
						setSegment(segAct.sortiePour(this));
					} catch (VoitureException e) {
						// Impossible qu'il y ait une exception puisque this ne peut pas etre null
					}
				} else {
					setPosition(segAct.getLong() - 1);
					distRestante = 0;
				}
			}

		} while (distRestante > 0);

	}
	
	public void setPosition(int nouvellePos) {
		int posPrec = position;
		position = nouvellePos;
		segAct.activerCapteurs(this, posPrec,  position);
	}

	public void setSegment(Segment nouveauSeg) throws SegmentException {
		segAct.activerCapteurs(this, position,  segAct.getLong()-1);
		position = 0;
		segPrec = segAct;
		segAct = nouveauSeg;
		sens = segPrec.estDirigeVers(segAct);
		segAct.setSegmentArrivee(segPrec);
		segAct.activerCapteurs(this);
	}

	public void reactSignal(Semaphore s) {
		vitesseAct = vitesseAutorisee = vitesseMax;
		if (this.sens == s.isSens() && s.getItsRoad() == this.segAct) {
			Action info = s.GiveInfo();
			switch (info) {
			case Stop:
				peutSortir = false;
				break;
			case LetItGo:
				peutSortir = true;
				break;
			case SlowDown:
				peutSortir = true;
				vitesseAct = vitesseAutorisee / 2;
				break;
			case MaxSpeed:
				if (vitesseMax > ((Limitation) s).getVitesseMax())
					vitesseAct = vitesseAutorisee = ((Limitation) s).getVitesseMax();
				break;
			}
		}
		if (vitesseAct < distRestante)
			distRestante = vitesseAct;
	}

	public boolean getSens() {
		return sens;
	}

	public void setSens(boolean sens) {
		this.sens = sens;
	}

	public Segment getSegPrec() {
		return segPrec;
	}

	@Override
	public String toString() {
		return "voiture " + id + " : " + (sens ? "aller " : "retour ") + position + "/" + segAct.getLong() + " sur "
				+ segAct;
	}

}
