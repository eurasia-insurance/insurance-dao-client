package tech.lapsa.insurance.dao;

public class NotPersistedException extends Exception {
    private static final long serialVersionUID = 2017753590608651407L;

    public NotPersistedException() {
	super();
    }

    public NotPersistedException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotPersistedException(String message, Throwable cause) {
	super(message, cause);
    }

    public NotPersistedException(String message) {
	super(message);
    }

    public NotPersistedException(Throwable cause) {
	super(cause);
    }

}
