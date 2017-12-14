package semaphore;

import route.Ligne;
import status.Action;

public abstract class Semaphore {

	protected Ligne itsRoad;
	protected boolean sens;

	public Semaphore(Ligne toBeAdded, boolean b) {
		if (toBeAdded != null) {
			itsRoad = toBeAdded;
			toBeAdded.addSemaphore(this, b);
		}
	}

	public Ligne getItsRoad() {
		return itsRoad;
	}

	public abstract Action GiveInfo();

	public abstract void switchCurrent(int currentTurn);

	public boolean isSens() {
		return sens;
	}

	public void setSens(boolean b) {
		this.sens = b;
	}
}
