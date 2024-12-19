package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class SendMoney implements Command {
    public SendMoney() {}
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        Account receiver = null;
        Account sender = null;
        for (User user : users) {
            for (Account account : user.getAccounts()) {
                if (account.getIban().equals(commandInput.getAccount())) {
                    sender = account;
                }
                if (account.getIban().equals(commandInput.getReceiver())) {
                    receiver = account;
                }
            }
        }
        double amount = commandInput.getAmount();
        if (receiver == null || sender == null || sender.getBalance() < amount) {
            return;
        }
        sender.addBalance(-amount);
        // Convertim in EURO toate sumele
        double rate;
        if (sender.getCurrency().equals(receiver.getCurrency())) {
            receiver.addBalance(amount);
            return;
        }
        for (ExchangeInput exchangeInput : input.getExchangeRates()) {
            if (exchangeInput.getFrom().equals("EUR")
                    && exchangeInput.getTo().equals(sender.getCurrency())) {
                rate = exchangeInput.getRate();
                amount = amount / rate;
            }
            if (exchangeInput.getFrom().equals(sender.getCurrency())
                    && exchangeInput.getTo().equals("EUR")) {
                rate = 1 / exchangeInput.getRate();
                amount = amount / rate;
            }
        }
        for (ExchangeInput exchangeInput : input.getExchangeRates()) {
            if (exchangeInput.getFrom().equals("EUR")
                    && exchangeInput.getTo().equals(receiver.getCurrency())) {
                rate = exchangeInput.getRate();
                amount = amount * rate;
            }
            if (exchangeInput.getFrom().equals(receiver.getCurrency())
                    && exchangeInput.getTo().equals("EUR")) {
                rate = 1 / exchangeInput.getRate();
                amount = amount * rate;
            }
        }
        receiver.addBalance(amount);
    }
}
