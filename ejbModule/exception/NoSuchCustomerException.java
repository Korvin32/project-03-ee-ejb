package exception;

public class NoSuchCustomerException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoSuchCustomerException(Throwable cause) {
        super(cause);
    }

    public NoSuchCustomerException(String message, Throwable cause) {
        super(message, cause);
    }

	public NoSuchCustomerException(String message) {
		super(message);
	}
}
