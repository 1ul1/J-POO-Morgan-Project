package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public class AddAccount implements Command{
    public AddAccount(){
    }
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        ArrayList<User> users, final CommandInput commandInput) {
        for (User user : users) {
            if (user.getEmail().equals(commandInput.getEmail())) {
                user.setAccount(commandInput);
            }
        }
    }
}
