package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.Card;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public class CheckCardStatus implements Command {
    public CheckCardStatus() {
    }
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        ArrayList<User> users, final CommandInput commandInput) {
        for (User user : users) {
            if (user.getAccounts().size() == 0) {
                break;
            }
            for (int i = 0; i < user.getAccounts().get(0).getCards().size(); i++) {
                for (Account account : user.getAccounts()) {
                    Card card = account.getCards().get(i);
                    if (account.getCards().size() == 0) {
                        break;
                    }
                }
//                Card card = user.getAccounts().get(0).getCards().get(i);
//                if (user.getAccounts().get(0).getCards().size() == 0) {
//                    break;
//                }
//                if (card.getCardNumber().equals(commandInput.getCardNumber())) {
//                }
            }
        }
    }
}
