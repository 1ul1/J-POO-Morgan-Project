package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class SetMinBalance implements Command {
    public SetMinBalance() {
    }
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        for (User user : users) {
            if (user.getAccounts().size() == 0) {
                break;
            }
            for (Account account : user.getAccounts()) {
                if (account.getIban().equals(commandInput.getAccount())) {
                    account.setMinBalance(commandInput.getMinBalance());
                }
            }
        }
    }
}
