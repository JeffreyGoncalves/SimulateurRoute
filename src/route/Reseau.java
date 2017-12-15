package route;
import java.util.ArrayList;

import capteur.Capteur;
import exception.JonctionException;
import exception.SegmentException;
import exception.SemaphoreException;
import jonction.Barriere;
import jonction.Carrefour;
import jonction.Jonction;
import regulation.DonneurDePriorite;
import regulation.ElementRegulation;
import semaphore.Feu;
import semaphore.Limitation;
import semaphore.Tricolore;
import voiture.Voiture;

public class Reseau {

	private ArrayList<Segment> routes = new ArrayList<Segment>();
	private ArrayList<Voiture> usagers = new ArrayList<Voiture>();
	private ArrayList<Feu> feuxIndependants = new ArrayList<Feu>();
	private ArrayList<Capteur> capteurs = new ArrayList<Capteur>();

	private boolean constructionFinie = false;

	public Reseau() {

	}

	public void relier(Jonction lieu1, Jonction lieu2, int distance) throws SegmentException, JonctionException {
		new Ligne(lieu1, lieu2, distance);
	}

	public void finirConstruction() throws SegmentException, JonctionException {

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
	
	public void addCapteur(Capteur capteur) {
		capteurs.add(capteur);
	}

	public void faireAvancer() throws SegmentException {
		for (Voiture usager : usagers) {
			System.out.println(usager.descrLongue());
			usager.avancer();
		}
	}
	
	public void transmettreInfosCapteurs() {
		for (Capteur capteur : capteurs) {
			capteur.transmettreInfo();
		}
	}

	public void runFeux(int currentTurn) {
		for (Feu feu : feuxIndependants) {
			feu.changementAuto(currentTurn);
			System.out.println(feu);
		}
	}
	
	
	public static void main(String[] args) throws SegmentException, JonctionException, SemaphoreException {

		Reseau reseau = Segment.getReseau();
		
		
		// Exemple 1
		Jonction lieu1 = new Barriere("PUIO");
		Jonction lieu2 = new Carrefour("MDI", 3);
		Jonction lieu3 = new Barriere("EDC");
		reseau.relier(lieu1, lieu2, 10);
		reseau.relier(lieu2, lieu3, 5);
		
		Voiture voit1 = new Voiture(0, 2, true, lieu1);
		reseau.ajouterVoiture(voit1);
		Voiture voit2 = new Voiture(0, 2, true, lieu3);
		reseau.ajouterVoiture(voit2);
		
		Tricolore feu = new Tricolore((Ligne)reseau.routes.get(3), true, 10, 0, 3, 4);
		reseau.feuxIndependants.add(feu);
		Limitation limite = new Limitation((Ligne)reseau.routes.get(3), true, 1);
		Capteur capteur1 = new Capteur(reseau.routes.get(3), 4);
		reseau.capteurs.add(capteur1);
		
		reseau.finirConstruction(); // finit la construction des routes
		
		ElementRegulation.setReseau(reseau);
		DonneurDePriorite regulateur = new DonneurDePriorite((Carrefour) lieu2, (Ligne) reseau.routes.get(3));

		int nbTours = 0;
		while (nbTours < 60) {
			System.out.println("\n- Nouveau tour -");
			reseau.faireAvancer();
			reseau.transmettreInfosCapteurs();
			reseau.runFeux(nbTours);
			++nbTours;
		}

	}
}
