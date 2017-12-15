package regulation;
import jonction.Carrefour;
import route.Reseau;

public abstract class ElementRegulation {
	
	protected Carrefour carrefour;
	protected static Reseau sonReseau;
	
	public ElementRegulation(Carrefour carrefour) {
		this.carrefour = carrefour;
	}
	
	public abstract void donnerOrdres();
	
	public static void setReseau(Reseau reseau) {
		sonReseau = reseau;
	}
	
}
