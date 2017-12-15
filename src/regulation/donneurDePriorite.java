package regulation;

import capteur.Capteur;
import jonction.Carrefour;
import route.Ligne;
import semaphore.Tricolore;

public class donneurDePriorite extends ElementRegulation{
	
	private Capteur capteurExt;
	private Tricolore[] feux;
	
	public donneurDePriorite(Carrefour carrefour, Ligne ligne) {
		super(carrefour);
		
	}
	
	

	@Override
	public void donnerOrdres() {
		// TODO Auto-generated method stub
		
	}
	
}
