package capteur;

import route.Segment;
import voiture.Voiture;

public class CaptPresence extends Capteur {

	public CaptPresence(Segment segment, int position) {
		super(segment, position);
	}
	
	@Override
	public  void detecter(Voiture voiture) {
		
	}
}
