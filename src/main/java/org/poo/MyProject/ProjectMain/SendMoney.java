package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.Card;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public class SendMoney implements Command {
    public SendMoney() {}
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        ArrayList<User> users, final CommandInput commandInput) {
        Account receiver = null;
        Account sender = null;
        for (User user : users) {
            if (user.getEmail().equals(commandInput.getEmail())) {
                for (Account account : user.getAccounts()) {
                    if (account.getIBAN().equals(commandInput.getAccount())) {
                        sender = account;
                    }
                    if (account.getIBAN().equals(commandInput.getReceiver())) {
                        receiver = account;
                    }
                }
            }
        }
        if (receiver == null || sender == null) {
            return;
        }
        double amount = commandInput.getAmount();
        double rate = 1;
        for (ExchangeInput exchangeInput : input.getExchangeRates()) {
            if (exchangeInput.getFrom().equals(sender.getCurrency())
                    && exchangeInput.getTo().equals(commandInput.getCurrency())) {
                rate = exchangeInput.getRate();
            }
        }
        amount = amount / rate;
        if (sender.getBalance() >= amount) {
            sender.addBalance(-amount);
        }
        amount = amount * rate * rate;
        receiver.addBalance(amount);
    }
}
