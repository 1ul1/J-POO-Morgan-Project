package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public final class ExecuteCommand {
    private ExecuteCommand() {
    }

    private static ExecuteCommand instance;

    /**
     * Implementarea pentru instanta de singletone
     */
    public static ExecuteCommand getInstance() {
        if (instance == null) {
            instance = new ExecuteCommand();
        }
        return instance;
    }

    private Command command;

    public void setCommand(final Command command) {
        this.command = command;
    }

    /**
     * pentru implementarea Design Patern-ului Strategy
     */
    public void execute(final ObjectInput input, final ArrayNode output,
                        final ArrayList<User> users, final CommandInput commandInput) {
        command.execute(input, output, users, commandInput);
    }
}
