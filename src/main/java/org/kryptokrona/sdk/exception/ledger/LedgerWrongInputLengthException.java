package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerWrongInputLengthException.java
 *
 * The input data supplied is of an incorrect length required.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerWrongInputLengthException extends LedgerException {
	public LedgerWrongInputLengthException() {
		super("The input data supplied is of an incorrect length required.");
	}
}

