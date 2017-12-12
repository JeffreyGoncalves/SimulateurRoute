
public abstract class Semaphore {

	private Ligne itsRoad;
	private int position;
	
	public Semaphore(Ligne toBeAdded) {
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
}
