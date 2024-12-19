package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class PrintUsers implements Command {
    public PrintUsers() {
    }
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("command", "printUsers");
        ArrayList<User> usersCopy = new ArrayList<>();
        for (User user : users) {
            usersCopy.add(new User(user));
        }
        jsonNode.putPOJO("output", usersCopy);
        jsonNode.put("timestamp", commandInput.getTimestamp());
        jsonNode.putPOJO("output", usersCopy);
        output.add(jsonNode);
    }
}
