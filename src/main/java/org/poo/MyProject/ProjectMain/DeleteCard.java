package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.Card;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public class DeleteCard implements Command{
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        ArrayList<User> users, final CommandInput commandInput) {
        for (User user : users) {
            if (user.getAccounts().size() == 0) {
                break;
            }
            for (Account account : user.getAccounts()) {
                for (int i = 0; i < user.getAccounts().get(0).getCards().size(); i++) {
                    Card card = user.getAccounts().get(0).getCards().get(i);
                    if (user.getAccounts().get(0).getCards().size() == 0) {
                        break;
                    }
                    if (card.getCardNumber().equals(commandInput.getCardNumber())) {
                        user.getAccounts().get(0).getCards().remove(i);
                    }
                }
            }
        }
    }
}