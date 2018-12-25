package exception;

public class WrongPasswordException extends Exception {


    private static final long serialVersionUID = 1L;

    public WrongPasswordException(Throwable cause) {
        super(cause);
    }

    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

	public WrongPasswordException(String message) {
		super(message);
	}

}
