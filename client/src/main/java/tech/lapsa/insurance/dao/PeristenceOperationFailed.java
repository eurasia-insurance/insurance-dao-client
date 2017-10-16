package tech.lapsa.insurance.dao;

public class PeristenceOperationFailed extends RuntimeException {

    private static final long serialVersionUID = 1912901337056261895L;

    public PeristenceOperationFailed() {
	super();
    }

    public PeristenceOperationFailed(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public PeristenceOperationFailed(String message, Throwable cause) {
	super(message, cause);
    }

    public PeristenceOperationFailed(String message) {
	super(message);
    }

    public PeristenceOperationFailed(Throwable cause) {
	super(cause);
    }
}
