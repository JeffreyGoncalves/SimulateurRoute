package voiture;

import exception.SegmentException;
import exception.SemaphoreException;
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
	private int vitesseAutorisee; // vitesse maximale selon les panneaux de limitation de vitesse
	private int distRestante;
	private boolean sens;
	private Segment segAct;
	private Segment segPrec;
	private boolean peutSortir = true;

	public Voiture(int position, int vitesseAct, int vitesseMax, boolean sens, Segment segAct) throws VoitureException, SegmentException {
		id = prochainID++;
		this.position = position;
		if (vitesseAct < 0 || vitesseMax < 0) {
			throw new VoitureException("Vitesse negative !!\n");
		} else {
			this.vitesseAct = vitesseAct;
			this.vitesseMax = vitesseMax;
		}
		if(segAct == null) {
			throw new SegmentException("Ce segment n'existe pas !! \n");
		}
		else {
			this.segAct = segAct;
		}
		
	}

	public void avancer() throws VoitureException, SegmentException, SemaphoreException {

		distRestante = vitesseAct;
		do {
			if (segAct.containsSemaphore()) {
				if (sens && ((Ligne) segAct).getSfin() != null)
					reactSignal(((Ligne) segAct).getSfin());
				else if (!sens && ((Ligne) segAct).getSdebut() != null)
					reactSignal(((Ligne) segAct).getSdebut());
			}
			if (position + vitesseAct < segAct.getLong()) {
				position += distRestante;
				distRestante = 0;
			} else {
				if (peutSortir) {
					distRestante -= segAct.getLong() - position;
					segPrec = segAct;
					segAct = segAct.sortiePour(this);
					sens = segPrec.estDirigeVers(segAct);
					position = 0;
					segAct.setSegmentArrivee(segPrec);
				} else {
					position = segAct.getLong() - 1;
					distRestante = 0;
				}
			}

		} while (distRestante > 0);

	}

	public void reactSignal(Semaphore s) throws SemaphoreException {
		if (s == null) {
			throw new SemaphoreException("Semaphore non defini dans reactSignal !!\n");
		} else {
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
						vitesseAutorisee = ((Limitation) s).getVitesseMax();
					else
						vitesseAutorisee = vitesseMax;
					break;
				}
			}
			if (vitesseAct < distRestante)
				distRestante = vitesseAct;
		}
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
