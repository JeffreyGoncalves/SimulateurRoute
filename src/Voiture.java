
public class Voiture {

	private static int prochainID = 0;
	private int id;
	private int position;
	private int vitesseAct;
	private int vitesseMax;
	private boolean sens;
	private Segment segAct;
	private Segment segPrec;

	public Voiture(int position, int vitesseAct, int vitesseMax, boolean sens, Segment segAct) {
		id = prochainID++;
		this.position = position;
		this.vitesseAct = vitesseAct;
		this.vitesseMax = vitesseMax;
		this.segAct = segAct;
	}

	public void avancer() {

		int distRestante = vitesseAct;
		do {
			if (position + vitesseAct < segAct.getLong()) {
				position += distRestante;
				distRestante = 0;
			} else {
				distRestante -= segAct.getLong() - position;
				segPrec = segAct;
				segAct = segAct.sortiePour(this);
				sens = segPrec.estDirigeVers(segAct);
				position = 0;
				segAct.setSegmentArrivee(segPrec);
			}

		} while (distRestante > 0);

	}

	public boolean getSens() {
		return sens;
	}

	public void setSens(boolean sens) {
		this.sens = sens;
	}

	public Segment getSegPrec() {
		return segPrec;
	}

	@Override
	public String toString() {
		return "voiture " + id + " : " + (sens ? "aller" : "retour")+ position + "/" + segAct.getLong() + " sur " + segAct;
	}

}
