package exception;

@SuppressWarnings("serial")
public class SemaphoreException extends Exception {

	public SemaphoreException() {
		System.err.println("Erreur Semaphore !! \n");
	}
	
	public SemaphoreException(String msg) {
		super(msg);
	}
}
