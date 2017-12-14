package capteur;

import route.Segment;

public class CaptVitesse extends Capteur {
	
	private int vDetect;

	public CaptVitesse(Segment segment, int position, int vDetect) {
		super(segment, position);
	}
}
