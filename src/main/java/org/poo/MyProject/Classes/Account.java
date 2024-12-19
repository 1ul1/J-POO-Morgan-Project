package org.poo.MyProject.Classes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String IBAN;
    private double balance;
    private String email;
    private String currency;
    private String type;
    private int timestamp;
    private double interestRate;
    private ArrayList<Card> cards = new ArrayList<>();
    @JsonIgnore
    private double minBalance = -100000;

    public Account(CommandInput commandInput) {
        email = commandInput.getEmail();
        currency = commandInput.getCurrency();
        type = commandInput.getAccountType();
        timestamp = commandInput.getTimestamp();
        interestRate = commandInput.getInterestRate();
        balance = 0;
        IBAN = Utils.generateIBAN();
    }
    @JsonIgnore
    public String getEmail() {
        return email;
    }
    @JsonIgnore
    public int getTimestamp() {
        return timestamp;
    }
    @JsonIgnore
    public double getInterestRate() {
        return interestRate;
    }
    public Account(Account account) {
        if (account == null) {
            return;
        }
        email = account.getEmail();
        currency = account.getCurrency();
        type = account.getType();
        timestamp = account.getTimestamp();
        interestRate = account.getInterestRate();
        balance = account.getBalance();
        IBAN = account.getIBAN();
        cards = new ArrayList<>();
        for (Card card : account.getCards()) {
            cards.add(new Card(card));
        }
    }
    public void addBalance(double amount) {
        balance += amount;
    }
    public void newCard() {
        cards.add(new Card());
    }
    public void newOneTimeCard() {
        cards.add(new Card(true));
    }
    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }
}
