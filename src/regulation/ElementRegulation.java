package regulation;
import java.util.ArrayList;

import capteur.Capteur;
import jonction.Carrefour;
import semaphore.Feu;
import status.CouleurFeu;

public class ElementRegulation {
	protected Carrefour carrefour;
	protected ArrayList<Feu> Feux;
	protected ArrayList<Capteur> capteurs;
	
	public ElementRegulation(Carrefour carrefour) {
		this.carrefour = carrefour;
	}
	
}
