package org.ucema.sgsp.exception;

/**
 * The exception is thrown when the email given during the registration
 * phase is already found from the database.
 */
public class DuplicateEmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String message) {
        super(message);
    }
}
