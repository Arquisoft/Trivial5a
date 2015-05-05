package model.exception;

public class IdLoginException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public IdLoginException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "IdLoginException: " + message + ".";
	}

}
