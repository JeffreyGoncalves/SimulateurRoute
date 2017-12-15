package route;

import java.util.ArrayList;

import exception.JonctionException;
import exception.SegmentException;
import exception.VoitureException;
import jonction.Jonction;
import semaphore.Feu;
import semaphore.Limitation;
import semaphore.Semaphore;
import semaphore.T_Semaphore;
import voiture.Voiture;

public class Ligne extends Segment {
	private int longueur;
	private Jonction debut;
	private Jonction fin;
	private ArrayList<Semaphore> semaphores = new ArrayList<Semaphore>();

	public Ligne(Jonction debut, Jonction fin, int longueur) throws SegmentException {
		super();
		this.longueur = longueur;
		this.debut = debut;
		this.fin = fin;
		debut.ajouterLigne(this);
		fin.ajouterLigne(this);
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
		return "ligne(" + debut + "->" + fin + ")";
	}
	
	@Override
	public String descrLongue() {
		return "Route_de_" + debut.descrLongue() + "_vers_" + fin.descrLongue();
	}

	@Override
	public void finirConstruction() {
		// Rien a faire pour une Ligne
	}

	public void addSemaphore(Semaphore semaphore) throws SegmentException{
		for (Semaphore semAct : semaphores) {
			if (semAct.getSens() == semaphore.getSens() && semAct.getType() == semaphore.getType()) {
				System.out.println("Il y a deja un semaphore de ce type ici");
				throw new SegmentException("Il y a deja un semaphore de ce type ici");
			}
		}
		semaphores.add(semaphore);
	}

	@Override
	public void activerCapteurs(Voiture voiture, int pos1, int pos2) {
		if (capteurs == null)
			return;
		int posAbs1 = voiture.getSens() ? pos1 : (longueur - 1 - pos2);
		int posAbs2 = voiture.getSens() ? pos2 : (longueur - 1 - pos1);
		//System.out.println(posAbs1 + "  ;  " + posAbs2);
		for (int i = 0; i < capteurs.size(); ++i) {
			if (capteurs.get(i).getPos() >= posAbs1 && capteurs.get(i).getPos() <= posAbs2)
				capteurs.get(i).detecter(voiture);
		}
	}

	@Override
	public void activerCapteurs(Voiture voiture) {
		if (capteurs == null)
			return;
		for (int i = 0; i < capteurs.size(); ++i) {
			if ((voiture.getSens() && capteurs.get(i).getPos() == 0)
					|| (!voiture.getSens() && capteurs.get(i).getPos() == longueur - 1))
				capteurs.get(i).detecter(voiture);
		}
	}

	@Override
	public boolean containsSemaphore() {
		return (semaphores.size() > 0);
	}
	
	@Override
	public ArrayList<Semaphore> getSemaphores() {
		return semaphores;
	}

}
