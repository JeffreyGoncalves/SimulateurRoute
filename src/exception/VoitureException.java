package exception;

@SuppressWarnings("serial")
public class VoitureException extends Exception {
		
	public VoitureException() {
		System.err.println("Erreur Voiture !! \n");
	}
	
	public VoitureException(String msg) {
		super(msg);
	}
}
