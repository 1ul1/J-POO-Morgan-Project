package org.poo.MyProject.ProjectMain;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.MyProject.Classes.User;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;
import lombok.Data;
import org.poo.fileio.UserInput;
import org.poo.utils.Utils;

import java.util.ArrayList;

@Data
public final class Start {
    private Start() {
    }
    /**
     * Functia de inceput a codului
     * Aici incepe implementarea propriu-zisa a codului
     */
    public static void start(final ObjectInput input, final ArrayNode output) {
        ArrayList<User> users = new ArrayList<>();
        for (UserInput userInput : input.getUsers()) {
            users.add(new User(userInput));
        }
        ExecuteCommand commandInstance = ExecuteCommand.getInstance();
        for (CommandInput commandInput : input.getCommands()) {
            switch (commandInput.getCommand()) {
                case "addAccount":
                    commandInstance.setCommand(new AddAccount());
                    commandInstance.execute(null, null, users, commandInput);
                    break;
                case "printUsers":
                    commandInstance.setCommand(new PrintUsers());
                    commandInstance.execute(null, output, users, commandInput);
                    break;
                case "addFunds":
                    commandInstance.setCommand(new AddFunds());
                    commandInstance.execute(null, null, users, commandInput);
                    break;
                case "createCard":
                    commandInstance.setCommand(new CreateCard());
                    commandInstance.execute(input, null, users, commandInput);
                    break;
                case "createOneTimeCard":
                    commandInstance.setCommand(new CreateOneTimeCard());
                    commandInstance.execute(null, null, users, commandInput);
                    break;
                case "deleteAccount":
                    commandInstance.setCommand(new DeleteAccount());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                case "deleteCard":
                    commandInstance.setCommand(new DeleteCard());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                case "setMinBalance":
                    commandInstance.setCommand(new SetMinBalance());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                case "checkCardStatus":
                    commandInstance.setCommand(new CheckCardStatus());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                case "payOnline":
                    commandInstance.setCommand(new PayOnline());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                case "sendMoney":
                    commandInstance.setCommand(new SendMoney());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                case "printTransactions":
                    commandInstance.setCommand(new PrintTransactions());
                    commandInstance.execute(input, output, users, commandInput);
                    break;
                default:
                    break;
            }
        }
        Utils.resetRandom();
    }
}
