package capteur;

import java.util.ArrayList;

import route.Segment;
import voiture.Voiture;

public class Capteur {
	protected Segment itsRoad;
	protected int position;

	protected ArrayList<Voiture> detectees = new ArrayList<Voiture>();

	public Capteur(Segment itsRoad, int position) {
		this.itsRoad = itsRoad;
		this.position = position;
		itsRoad.addCapteur(this, position);
	}

	public void transmettreInfo() {
		// donner l'info
		detectees.clear();
	}
	
	public void detecter(Voiture voiture) {
		if (!detectees.contains(voiture)) {
			detectees.add(voiture);
			System.out.println("voiture detectee en " + position + "/" + itsRoad.getLong() + " de " + itsRoad);
		}
	}
	
}
