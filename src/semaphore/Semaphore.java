package semaphore;

import exception.SegmentException;
import route.Ligne;
import status.Action;

public abstract class Semaphore {

	protected Ligne itsRoad;
	protected boolean sens;
	protected T_Semaphore sonType;

	public Semaphore(Ligne toBeAdded, boolean sens) throws SegmentException{
		if (toBeAdded != null) {
			itsRoad = toBeAdded;
			this.sens = sens;
			toBeAdded.addSemaphore(this);
		}
	}

	public Ligne getItsRoad() {
		return itsRoad;
	}

	public abstract Action GiveInfo();

	public abstract void switchCurrent(int currentTurn);

	public boolean getSens() {
		return sens;
	}
	
	public T_Semaphore getType() {
		return sonType;
	}
	
	public void setSens(boolean b) {
		this.sens = b;
	}
}
