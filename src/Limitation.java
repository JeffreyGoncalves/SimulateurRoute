
public class Limitation extends Semaphore {
	
	private int vitesseMax;
	
	public Limitation(Ligne toBeAdded,int vmax) {
		super(toBeAdded);
		vitesseMax = vmax;
	}
	
	public int getVitesseMax() {
		return this.vitesseMax;
	}

	@Override
	public Action GiveInfo() {
		return Action.LetItGo;
	}
	
	@Override
	public void switchCurrent(int currentTurn, int inter){
		
	}
}
