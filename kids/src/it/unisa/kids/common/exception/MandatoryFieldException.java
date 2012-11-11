package it.unisa.kids.common.exception;

public class MandatoryFieldException extends Exception {

	public MandatoryFieldException() {
		super();
	}
	
	public MandatoryFieldException(String msg) {
		super(msg);
	}
}
