package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class PrintTransactions implements Command {
    public PrintTransactions() {}
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        for (User user : users){
            if (user.getEmail().equals(commandInput.getEmail())) {
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectNode jsonNode = objectMapper.createObjectNode();
                jsonNode.put("command", "printTransactions");
                jsonNode.putPOJO("output", user.getTransactions());
                jsonNode.put("timestamp", commandInput.getTimestamp());
                output.add(jsonNode);
                break;
            }
        }
    }
}

