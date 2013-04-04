package exception;

public class WronPasswordException extends Exception {


    private static final long serialVersionUID = 1L;

    public WronPasswordException(Throwable cause) {
        super(cause);
    }

    public WronPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

	public WronPasswordException(String message) {
		super(message);
	}

}
