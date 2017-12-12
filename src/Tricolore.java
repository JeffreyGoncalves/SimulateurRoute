
public class Tricolore extends Feu {

	private int decalage;

	public Tricolore(Ligne toBeAdded, int dec) {
		super(toBeAdded);
		decalage = dec;
	}

	@Override
	public String toString() {
		String toReturn;
		toReturn = "Le Feu est au " + this.current + "\n";

		return toReturn;
	}

	public CouleurFeu getCurrent() {
		return this.current;
	}

	public void switchCurrent(int currentTurn, int inter) {
		if ((currentTurn % inter) == 0) {
			if (this.current == CouleurFeu.Vert) {
				this.current = CouleurFeu.Orange;
			} else if (this.current == CouleurFeu.Rouge) {
				this.current = CouleurFeu.Vert;
			}
		} else if ((currentTurn % inter) == this.decalage) {
			if (this.current == CouleurFeu.Orange) {
				this.current = CouleurFeu.Rouge;
			}
		}

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
	
	public static void main(String[] args) {

		int i = 0;
		Ligne route = new Ligne(new Barriere(), new Barriere(), 10);
		Tricolore sabrule = new Tricolore(route, 1);

		while (i < 20) {

			sabrule.switchCurrent(i, 4);
			System.out.println(sabrule.toString());
			i++;
		}

	}

}
