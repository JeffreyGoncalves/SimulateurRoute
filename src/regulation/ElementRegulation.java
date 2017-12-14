package regulation;
import java.util.ArrayList;

import capteur.Capteur;
import jonction.Carrefour;
import semaphore.Feu;
import status.CouleurFeu;

public abstract class ElementRegulation {
	protected Carrefour carrefour;
	
	
	public ElementRegulation(Carrefour carrefour) {
		this.carrefour = carrefour;
	}
	
	public abstract void traiterInfo();
	
}
