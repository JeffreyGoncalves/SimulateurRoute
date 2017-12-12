
public class Feu extends Semaphore {

	protected CouleurFeu current;
	
	
	public Feu(Ligne toBeAdded) {
		super(toBeAdded);
		this.current = CouleurFeu.Vert;
	}
	
	@Override
	public String toString() {
		String toReturn;
		toReturn = "Le Feu est au " + this.current +"\n";
		
		return toReturn;
	}
	
	public CouleurFeu getCurrent(){
		return this.current;
	}
	
	@Override
	public void switchCurrent(int currentTurn, int inter) {
		if((currentTurn % inter)== 0) {
			this.current = (this.current == CouleurFeu.Vert) ? CouleurFeu.Rouge : CouleurFeu.Vert ;
		}
	}

	public static void main (String[] args) {
		
		int i=1;
		Ligne route = new Ligne(new Barriere(),new Barriere(), 10);
		Feu sabrule = new Feu(route);
		
		while(i < 20) {
			
			sabrule.switchCurrent(i,4);
			System.out.println(sabrule.toString());
			i++;
		}
		
	}
	
	@Override
	public  Action GiveInfo(){
		if(this.current == CouleurFeu.Vert) 
		{ 
			return Action.LetItGo;
		}
		else {return Action.Stop;}
	}
}
