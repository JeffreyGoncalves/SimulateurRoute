package semaphore;
import exception.JonctionException;
import exception.SegmentException;
import exception.SemaphoreException;
import jonction.Barriere;
import route.Ligne;
import status.Action;
import status.CouleurFeu;

public class Tricolore extends Feu {

	private int tOrange;


	public Tricolore(Ligne toBeAdded, boolean sens, int periode, int tVert, int tOrange, int tRouge) throws SemaphoreException, SegmentException {

		super(toBeAdded, sens, periode, tVert, tRouge);
		this.tOrange = tOrange;
		sonType = T_Semaphore.t_feu;
	}

	
	public Tricolore(Ligne toBeAdded, boolean sens) throws SegmentException {
		super(toBeAdded, sens);
		sonType = T_Semaphore.t_feu;
	}
	
	public CouleurFeu getCurrent() {
		return this.current;
	}

	@Override
	public void changementAuto(int currentTurn) {
		int t = currentTurn % periode;
		//System.out.println("t = " + t);
		if (t == tVert)
			current = CouleurFeu.Vert;
		else if (t == tRouge)
			current = CouleurFeu.Rouge;
		else if (t == tOrange)
			current = CouleurFeu.Orange;
	}

	@Override
	public Action GiveInfo() {
		
		if(this.current == CouleurFeu.Vert) {
			return Action.LetItGo;
		}
		else if(this.current == CouleurFeu.Orange) {
			return Action.SlowDown;
		}
		else {
			return Action.Stop;
		}
	}
	
	public static void main(String[] args) throws SegmentException, JonctionException, SemaphoreException {

		int i = 0;
		Ligne route = new Ligne(new Barriere(), new Barriere(), 10);
		Tricolore sabrule = new Tricolore(route, true, 8, 0, 3, 4);

		while (i < 20) {

			sabrule.changementAuto(i);
			System.out.println(sabrule.toString());
			i++;
		}

	}

}
