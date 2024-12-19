package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.MyProject.Classes.Account;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class DeleteAccount implements Command {
    public DeleteAccount() {
    }
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("command", "deleteAccount");
        for (User user : users) {
            if (user.getAccounts().size() == 0) {
                break;
            }
            for (int i = 0; i < user.getAccounts().size(); i++) {
                Account account = user.getAccounts().get(i);
                if (account.getIban().equals(commandInput.getAccount())
                        && account.getBalance() == 0) {
                    user.getAccounts().remove(i);
                    ObjectNode json = objectMapper.createObjectNode();
                    json.put("success", "Account deleted");
                    json.put("timestamp", commandInput.getTimestamp());
                    jsonNode.putPOJO("output", json);
                }
            }
        }
        jsonNode.put("timestamp", commandInput.getTimestamp());
        output.add(jsonNode);
    }
}
