package org.poo.MyProject.Classes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    private final int timestamp;
    private final String description;
    private final Double amount;
    private final String receiverIBAN;
    private final String senderIBAN;
    private final String currency;
    private final String transferType;
    public Transaction(int timestamp, String description,
                       Double amount, String receiverIBAN, String senderIBAN,
                       String currency, String transferType) {
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
        this.receiverIBAN = receiverIBAN;
        this.senderIBAN = senderIBAN;
        this.currency = currency;
        this.transferType = transferType;
    }
}