package capteur;

import java.util.ArrayList;

import regulation.ElementRegulation;
import route.Segment;
import voiture.Voiture;

public class Capteur {
	protected Segment itsRoad;
	protected int position;
	protected ElementRegulation regulateur;

	protected ArrayList<Voiture> detectees = new ArrayList<Voiture>();

	public Capteur(Segment itsRoad, int position) {
		this.itsRoad = itsRoad;
		this.position = position;
		itsRoad.addCapteur(this);
	}

	public void transmettreInfo() {
		if (regulateur != null)
			;
		// donner l'info
		detectees.clear();
	}
	
	public void detecter(Voiture voiture) {
		if (!detectees.contains(voiture)) {
			detectees.add(voiture);
			System.out.println(voiture + " detectee en " + position + "/" + itsRoad.getLong() + " (position absolue) de " + itsRoad);
		}
		if (detectees.size() > 1)
			System.out.println("/!\\ Risque de collision en " + position + "/" + itsRoad.getLong() + " de " + itsRoad);
	}
	
	public int getPos() {
		return position;
	}
	
}
