package model.exception;

public class PasswordException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public PasswordException(String message) {
	super();
	this.message = message;
    }

    @Override
    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "PasswordException: " + message + ".";
    }

}
