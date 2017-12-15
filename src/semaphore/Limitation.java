package semaphore;

import exception.SegmentException;
import route.Ligne;
import status.Action;

public class Limitation extends Semaphore {

	private int vitesseMax;

	public Limitation(Ligne toBeAdded, boolean sens, int vmax) throws SegmentException {
		super(toBeAdded, sens);
		sonType = T_Semaphore.t_limite;
		vitesseMax = vmax;
	}

	public int getVitesseMax() {
		return this.vitesseMax;
	}

	@Override
	public Action GiveInfo() {
		return Action.MaxSpeed;
	}

	@Override
	public void switchCurrent(int currentTurn) {

	}
}
