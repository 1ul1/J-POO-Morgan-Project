package org.poo.MyProject.Classes;

public class TransactionBuilder {
    private int timestamp;
    private String description;
    private Double amount;
    private String receiverIBAN;
    private String senderIBAN;
    private String currency;
    private String transferType;

    /**
     * Builder Design pattern
     */
    public TransactionBuilder timestamp(final int timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public TransactionBuilder description(final String description) {
        this.description = description;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public TransactionBuilder amount(final Double amount) {
        this.amount = amount;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public TransactionBuilder receiverIBAN(final String receiverIBAN) {
        this.receiverIBAN = receiverIBAN;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public TransactionBuilder senderIBAN(final String senderIBAN) {
        this.senderIBAN = senderIBAN;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public TransactionBuilder currency(final String currency) {
        this.currency = currency;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public TransactionBuilder transferType(final String transferType) {
        this.transferType = transferType;
        return this;
    }
    /**
     * Builder Design pattern
     */
    public Transaction build() {
        return new Transaction(timestamp, description, amount,
                receiverIBAN, senderIBAN, currency, transferType);
    }
}
