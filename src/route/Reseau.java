package route;
import java.util.ArrayList;

import exception.JonctionException;
import exception.SegmentException;
import exception.SemaphoreException;
import exception.VoitureException;
import jonction.Barriere;
import jonction.Carrefour;
import jonction.Jonction;
import semaphore.Feu;
import voiture.Voiture;

public class Reseau {

	private ArrayList<Segment> routes = new ArrayList<Segment>();
	private ArrayList<Voiture> usagers = new ArrayList<Voiture>();

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

	public void faireAvancer() throws VoitureException, SegmentException, SemaphoreException {
		System.out.println("- Nouveau tour -");
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
					System.out.println(((Ligne) s).getSdebut());
				} else {
					((Ligne) s).getSfin().switchCurrent(currentTurn);
					System.out.println(((Ligne) s).getSfin());
				}
			}

		}
	}
	

	public static void main(String[] args) throws SegmentException, JonctionException, VoitureException, SemaphoreException {

		Reseau reseau = Segment.getReseau();

		Jonction lieu1 = new Barriere("PUIO");
		Jonction lieu2 = new Carrefour("MDI");
		Jonction lieu3 = new Barriere("EDC");

		reseau.relier(lieu1, lieu2, 10); // route de 10 reliant le PUIO a la MDI
		reseau.relier(lieu2, lieu3, 5); // route de 5 reliant la MDI a EDC

		// voiture demarrant au PUIO se deplacant a une vitesse de 3
		Voiture voit1 = new Voiture(0, 1, 5, true, lieu1);
		reseau.ajouterVoiture(voit1);
		
		Feu sabrule = new Feu((Ligne)reseau.routes.get(3), true, 20, 0, 4);

		reseau.finirConstruction();

		// On deplace toutes les voitures, ici il n'y en a qu'une
		int nbTours = 0;
		while (nbTours < 40) {
			reseau.RunSemaphores(nbTours);
			reseau.faireAvancer();
			/*
			 * try { Thread.sleep(500); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
			++nbTours;
		}

		// Juste pour tester la methode "sortiePour"
		while (nbTours < 60) {
			System.out.println(lieu2.sortiePour(voit1));
			++nbTours;
		}
	}
}
