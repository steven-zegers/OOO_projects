package model.domain;

public class DomainException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public DomainException() {
		super();
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public DomainException(Throwable cause) {
		super(cause);
	}

	protected DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}