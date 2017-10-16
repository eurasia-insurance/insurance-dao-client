package tech.lapsa.insurance.dao;

public class EntityNotFound extends Exception {
    private static final long serialVersionUID = 4444353365595112283L;

    public EntityNotFound() {
	super();
    }

    public EntityNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public EntityNotFound(String message, Throwable cause) {
	super(message, cause);
    }

    public EntityNotFound(String message) {
	super(message);
    }

    public EntityNotFound(Throwable cause) {
	super(cause);
    }

}
