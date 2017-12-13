package exception;

@SuppressWarnings("serial")
public class CapteurException extends Exception {
	

	public CapteurException(){
		System.err.println("Erreur Capteur !! \n");
	}
	
	public CapteurException(String msg) {
		super(msg);
	}

}
