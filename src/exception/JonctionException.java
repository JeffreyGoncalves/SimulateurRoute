package exception;

@SuppressWarnings("serial")
public class JonctionException extends Exception {

	public JonctionException() {
		System.err.println("Erreur Semaphore !! \n");
	}
	
	public JonctionException(String msg) {
		super(msg);
	}
}
