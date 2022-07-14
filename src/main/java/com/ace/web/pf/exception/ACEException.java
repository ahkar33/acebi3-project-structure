package com.ace.web.pf.exception;

import com.ace.web.pf.enums.ErrorMessage;

public class ACEException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998349587502748253L;

	private final ErrorMessage code;

	public ACEException(ErrorMessage code) {
		super();
		this.code = code;
	}

	public ACEException(String message, Throwable cause, ErrorMessage code) {
		super(message, cause);
		this.code = code;
	}

	public ACEException(String message, ErrorMessage code) {
		super(message);
		this.code = code;
	}

	public ACEException(Throwable cause, ErrorMessage code) {
		super(cause);
		this.code = code;
	}

	public ErrorMessage getMessageCode() {
		return this.code;
	}

}
