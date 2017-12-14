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


	public Tricolore(Ligne toBeAdded, boolean b, int periode, int tVert, int tOrange, int tRouge) throws SemaphoreException {

		super(toBeAdded, b, periode, tVert, tRouge);
		this.tOrange = tOrange;
	}

	public CouleurFeu getCurrent() {
		return this.current;
	}

	@Override
	public void switchCurrent(int currentTurn) {
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

			sabrule.switchCurrent(i);
			System.out.println(sabrule.toString());
			i++;
		}

	}

}
