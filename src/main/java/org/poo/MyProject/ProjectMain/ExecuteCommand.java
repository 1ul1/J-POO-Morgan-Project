package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.ArrayList;

public class ExecuteCommand{
    private ExecuteCommand(){
    }

    private static ExecuteCommand instance;

    public static ExecuteCommand getInstance(){
        if(instance == null){
            instance = new ExecuteCommand();
        }
        return instance;
    }

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute(ObjectInput input, ArrayNode output, ArrayList<User> users, CommandInput commandInput) {
        command.execute(input, output, users, commandInput);
    }
}
