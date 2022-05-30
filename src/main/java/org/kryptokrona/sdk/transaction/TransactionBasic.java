package org.kryptokrona.sdk.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction.java
 *
 * Represent a basic transaction of amount to the address destination,
 * using the given payment ID, if specified.
 * Network fee is set to default when using this object, mixin is set to default,
 * all subwallets are taken from, primary address is used as change address.
 *
 * If we need more control we use TransactionAdvanced.
 */
@Getter
@Setter
@NoArgsConstructor
public class TransactionBasic implements Transaction {
    private String              destination;
    private volatile double     amount;
    private String              paymentID;

    public TransactionBasic(String destination, double amount, String paymentID) {
        this.destination = destination;
        this.amount = amount;
        this.paymentID = paymentID;
    }
}
