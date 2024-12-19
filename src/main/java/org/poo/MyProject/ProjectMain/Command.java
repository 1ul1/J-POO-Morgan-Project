package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public interface Command {
    void execute(ObjectInput input, ArrayNode output, ArrayList<User> users, CommandInput commandInput);
}
