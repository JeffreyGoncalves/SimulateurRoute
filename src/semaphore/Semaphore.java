package semaphore;
import route.Ligne;
import status.Action;

public abstract class Semaphore {

	private Ligne itsRoad;
	private int position;
	private boolean sens;
	
	public Semaphore(Ligne toBeAdded,boolean b) {
		if(toBeAdded!=null) {
			itsRoad = toBeAdded;
			
		}
	}
	
	public Ligne getItsRoad() {
		return itsRoad;
	}



	public int getPosition() {
		return position;
	}
	
	public abstract Action GiveInfo();

	public abstract void switchCurrent(int currentTurn, int inter);

	public void setPosition(int p) {
		if(p==0 || p== this.itsRoad.getLong()) {
			this.position = p;
		}
		else {
			System.out.println("Erreur de position\n");
		}
	}

	public boolean isSens() {
		return sens;
	}
	
	public void setSens(boolean b) {
		this.sens = b;
	}
}
