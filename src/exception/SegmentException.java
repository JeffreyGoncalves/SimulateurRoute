package exception;

@SuppressWarnings("serial")
public class SegmentException extends Exception {

	public SegmentException() {
		System.err.println("Erreur Semaphore !! \n");
	}
	
	public SegmentException(String msg) {
		super(msg);
	}
}
