package org.poo.MyProject.Classes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.poo.fileio.CommandInput;
import org.poo.utils.Utils;

import java.util.ArrayList;

@Data
@JsonPropertyOrder({ "IBAN", "balance", "currency", "type", "cards" })
public class Account {
    @JsonProperty("IBAN")
    private String iban;
    private double balance;
    @JsonIgnore
    private String email;
    private String currency;
    private String type;
    @JsonIgnore
    private int timestamp;
    @JsonIgnore
    private double interestRate;
    private ArrayList<Card> cards = new ArrayList<>();
    @JsonIgnore
    private double minBalance;

    public Account(final CommandInput commandInput) {
        email = commandInput.getEmail();
        currency = commandInput.getCurrency();
        type = commandInput.getAccountType();
        timestamp = commandInput.getTimestamp();
        interestRate = commandInput.getInterestRate();
        balance = 0;
        iban = Utils.generateIBAN();
    }
    public Account(final Account account) {
        if (account == null) {
            return;
        }
        email = account.getEmail();
        currency = account.getCurrency();
        type = account.getType();
        timestamp = account.getTimestamp();
        interestRate = account.getInterestRate();
        balance = account.getBalance();
        iban = account.getIban();
        cards = new ArrayList<>();
        for (Card card : account.getCards()) {
            cards.add(new Card(card));
        }
    }
    /**
     * @param amount poate sa ia orice valoare
     */
    public void addBalance(final double amount) {
        balance += amount;
    }
    /**
     * Initializam un nou card
     */
    public void newCard() {
        cards.add(new Card());
    }
    /**
     * Initializam un nou card de unica folosinta
     */
    public void newOneTimeCard() {
        cards.add(new Card(true));
    }
}
