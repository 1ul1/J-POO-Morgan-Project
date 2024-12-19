package org.poo.MyProject.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.fileio.CommandInput;
import org.poo.fileio.UserInput;
import lombok.Data;

import java.util.ArrayList;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Account> accounts = new ArrayList<>();
    @JsonIgnore
    private ArrayList<Transaction> transactions = new ArrayList<>();
    public User(final UserInput userInput) {
        this.firstName = userInput.getFirstName();
        this.lastName = userInput.getLastName();
        this.email = userInput.getEmail();
    }
    public User(final User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.accounts = new ArrayList<>();
        for (Account account : user.getAccounts()) {
            this.accounts.add(new Account(account));
        }
    }

    /**
     * Initializam un nou cont unui user
     */
    public void setAccount(final CommandInput commandInput) {
        accounts.add(new Account(commandInput));
    }
    /**
     * Adaugam o tranzactie
     */
    public void addTransaction(final Transaction transaction) {
        transactions.add(transaction);
    }
}
