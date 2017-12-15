package regulation;

import capteur.Capteur;
import exception.SegmentException;
import exception.SemaphoreException;
import jonction.Carrefour;
import route.Ligne;
import semaphore.Tricolore;
import voiture.Voiture;

public class DonneurDePriorite extends ElementRegulation {

	private Capteur capteurExt;
	private Capteur capteurInt;
	private Tricolore[] feux;
	
	private Voiture prioritaire;
	private boolean prioVeutPasser;
	private boolean prioPasse;
	

	public DonneurDePriorite(Carrefour carrefour, Ligne lignePrio) throws SegmentException, SemaphoreException {
		super(carrefour);

		capteurInt = new Capteur(carrefour, 0);
		
		int distanceCapt = (lignePrio.getLong() <= 5 ? lignePrio.getLong() - 1 : 5);
		if (lignePrio.estDirigeVers(carrefour))
			capteurExt = new Capteur(lignePrio, lignePrio.getLong() - distanceCapt);
		else
			capteurExt = new Capteur(lignePrio, distanceCapt - 1);
		
		sonReseau.addCapteur(capteurExt);

		Ligne[] branches = carrefour.getBranches();

		feux = new Tricolore[branches.length - 1];
		int j = 0;
		for (int i = 0; i < branches.length; ++i) {
			if (branches[i] != lignePrio) {
				feux[j] = new Tricolore(branches[i], branches[i].estDirigeVers(carrefour), 15, 0, 4, 5);
				++j;
			}
		}
	}

	@Override
	public void donnerOrdres() {
		// TODO Auto-generated method stub

	}

}
