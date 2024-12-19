package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class PrintTransactions implements Command {
    private PrintTransactions() {}
    @Override
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        
    }
}

