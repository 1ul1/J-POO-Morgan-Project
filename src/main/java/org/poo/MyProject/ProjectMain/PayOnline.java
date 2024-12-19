package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.Card;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class PayOnline implements Command {
    public PayOnline() {
    }

    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        for (User user : users) {
            if (user.getEmail().equals(commandInput.getEmail())) {
                for (Account account : user.getAccounts()) {
                    for (Card card : account.getCards()) {
                        //System.out.println(card.getCardNumber());
                        if (card.getCardNumber().equals(commandInput.getCardNumber())
                                && card.getStatus().equals("active")) {
                            if (account.getCurrency().contains(commandInput.getCurrency())) {
                                if (account.getBalance() >= commandInput.getAmount()) {
                                    account.addBalance(-commandInput.getAmount());
                                    if (card.isOneTimeCard()) {
                                        card.setStatus("inactive");
                                    }
                                }
                                return;
                            } else {
                                // Convertim in EURO toate sumele
                                // Salvam ratele ca sa putem reveni la
                                // sumele initiale neconvertite (from & to)
                                double rate1 = 0;
                                double rate2 = 0;
                                double from = 0;
                                double to = 0;
                                for (ExchangeInput exchangeInput : input.getExchangeRates()) {
                                    if (exchangeInput.getFrom().equals("EUR")
                                            && exchangeInput.getTo()
                                            .equals(account.getCurrency())) {
                                        rate1 = exchangeInput.getRate();
                                        from = account.getBalance() / rate1;
                                    }
                                    if (exchangeInput.getTo().equals("EUR")
                                            && exchangeInput.getFrom()
                                            .equals(account.getCurrency())) {
                                        rate1 = 1 / exchangeInput.getRate();
                                        from = account.getBalance() / rate1;
                                    }
                                    if (exchangeInput.getFrom().equals("EUR")
                                            && exchangeInput.getTo()
                                            .equals(commandInput.getCurrency())) {
                                        rate2 = exchangeInput.getRate();
                                        to = commandInput.getAmount() / rate2;
                                    }
                                    if (exchangeInput.getTo().equals("EUR")
                                            && exchangeInput.getFrom()
                                            .equals(commandInput.getCurrency())) {
                                        rate2 = 1 / exchangeInput.getRate();
                                        to = commandInput.getAmount() / rate2;
                                    }
                                }
                                from = (from - to) * rate1;
                                if (from >= 0) {
                                    account.setBalance(from);
                                    if (card.isOneTimeCard()) {
                                        card.setStatus("inactive");
                                    }
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("command", "payOnline");
        ObjectNode json = objectMapper.createObjectNode();
        json.put("timestamp", commandInput.getTimestamp());
        json.put("description", "Card not found");
        jsonNode.putPOJO("output", json);
        jsonNode.put("timestamp", commandInput.getTimestamp());
        output.add(jsonNode);
    }
}
