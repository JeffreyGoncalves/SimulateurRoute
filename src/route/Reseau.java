package route;
import java.util.ArrayList;

import capteur.Capteur;
import exception.SegmentException;
import jonction.Barriere;
import jonction.Carrefour;
import jonction.Jonction;
import semaphore.Limitation;
import semaphore.Tricolore;
import voiture.Voiture;

public class Reseau {

	private ArrayList<Segment> routes = new ArrayList<Segment>();
	private ArrayList<Voiture> usagers = new ArrayList<Voiture>();

	private boolean constructionFinie = false;

	public Reseau() {

	}

	public void relier(Jonction lieu1, Jonction lieu2, int distance) {
		new Ligne(lieu1, lieu2, distance);
	}

	public void finirConstruction() {

		for (int i = 0; i < routes.size(); ++i) {
			routes.get(i).finirConstruction();
			System.out.println(routes.get(i));
		}
		constructionFinie = true;
	}

	public void ajouterSegment(Segment lieu) {
		routes.add(lieu);
	}

	public void ajouterVoiture(Voiture voiture) {
		usagers.add(voiture);
	}

	public void faireAvancer() throws SegmentException {
		for (Voiture usager : usagers) {
			System.out.println(usager.toString());
			usager.avancer();
		}
	}

	public void RunSemaphores(int currentTurn) {
		for (Segment s : routes) {
			if (s.containsSemaphore()) {
				if (((Ligne) s).getSdebut() != null) {
					((Ligne) s).getSdebut().switchCurrent(currentTurn);
					//System.out.println(((Ligne) s).getSdebut());
				} else {
					((Ligne) s).getSfin().switchCurrent(currentTurn);
					//System.out.println(((Ligne) s).getSfin());
				}
			}

		}
	}
	
	public static void main(String[] args) throws SegmentException {

		Reseau reseau = Segment.getReseau();

		Jonction lieu1 = new Barriere("PUIO");
		Jonction lieu2 = new Carrefour("MDI");
		Jonction lieu3 = new Barriere("EDC");

		reseau.relier(lieu1, lieu2, 10);
		reseau.relier(lieu2, lieu3, 5);

		
		Voiture voit1 = new Voiture(0, 2, true, lieu1);
		reseau.ajouterVoiture(voit1);
		
		Tricolore sabrule = new Tricolore((Ligne)reseau.routes.get(3), true, 20, 0, 4, 5);
		Limitation limite = new Limitation((Ligne)reseau.routes.get(4), 1, true);
		Capteur capteur = new Capteur(reseau.routes.get(3), 4);

		reseau.finirConstruction();

		int nbTours = 0;
		while (nbTours < 60) {
			System.out.println("- Nouveau tour -");
			System.out.println(sabrule);
			reseau.RunSemaphores(nbTours);
			reseau.faireAvancer();
			++nbTours;
		}

	}
}
