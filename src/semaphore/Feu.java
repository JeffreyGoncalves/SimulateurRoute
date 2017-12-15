package semaphore;

import exception.JonctionException;
import exception.SegmentException;
import exception.SemaphoreException;
import jonction.Barriere;
import regulation.ElementRegulation;
import route.Ligne;
import status.Action;
import status.CouleurFeu;

public class Feu extends Semaphore {

	protected CouleurFeu current;
	protected int tVert;
	protected int tRouge;
	protected int periode;
	protected ElementRegulation chef = null;

	public Feu(Ligne toBeAdded, boolean sens, int periode, int tVert, int tRouge) throws SemaphoreException, SegmentException {
		super(toBeAdded, sens, T_Semaphore.t_feu);
		sonType = T_Semaphore.t_feu;
		this.current = CouleurFeu.Rouge;
		if(periode < 0 || tVert < 0 || tRouge < 0) {
			throw new SemaphoreException("Duree(s) negative(s) ! \n");
		}
		else {
			this.periode = periode;
			this.tVert = tVert;
			this.tRouge = tRouge;
		}
		
	}
	
	public Feu(Ligne toBeAdded, boolean sens) throws SegmentException {
		super(toBeAdded, sens, T_Semaphore.t_feu);
	}

	@Override
	public String toString() {
		return "Feu " + this.current + " en " + (sens ? "fin" : "debut") + " de " + itsRoad;
	}

	public CouleurFeu getCurrent() {
		return this.current;
	}

	@Override
	public void changementAuto(int currentTurn){
		int t = currentTurn % periode;
		if (t == tVert)
			current = CouleurFeu.Vert;
		else if (t == tRouge)
			current = CouleurFeu.Rouge;
	}

	public static void main(String[] args) throws SemaphoreException, SegmentException, JonctionException {

		Ligne route = new Ligne(new Barriere(), new Barriere(), 10);
		Feu sabrule = new Feu(route, true, 8, 0, 4);
		Feu sabrule2 = new Feu(route, true, 8, 4, 0);

		int turn = 0;
		while (turn < 20) {

			sabrule.changementAuto(turn);
			sabrule2.changementAuto(turn);
			System.out.println(sabrule);
			System.out.println(sabrule2);
			System.out.println();
			turn++;
		}

	}

	@Override
	public Action GiveInfo() {
		return (this.current == CouleurFeu.Vert) ? Action.LetItGo : Action.Stop;
	}

}
