package semaphore;

import jonction.Barriere;
import route.Ligne;
import status.Action;
import status.CouleurFeu;

public class Feu extends Semaphore {

	protected CouleurFeu current;
	protected int tVert;
	protected int tRouge;
	protected int periode;

	public Feu(Ligne toBeAdded, boolean b, int periode, int tVert, int tRouge) {
		super(toBeAdded, b);
		this.current = CouleurFeu.Rouge;
		this.periode = periode;
		this.tVert = tVert;
		this.tRouge = tRouge;
	}
	

	@Override
	public String toString() {
		return "Le Feu est au " + this.current;
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
	}

	public static void main(String[] args) {

		Ligne route = new Ligne(new Barriere(), new Barriere(), 10);
		Feu sabrule = new Feu(route, true, 8, 0, 4);
		Feu sabrule2 = new Feu(route, true, 8, 4, 0);

		int turn = 0;
		while (turn < 20) {

			sabrule.switchCurrent(turn);
			sabrule2.switchCurrent(turn);
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
