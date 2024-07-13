package com.geektrust.backend.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private static final Map<String, Icommand> commandMap = new HashMap<>();

    //Register the Command into hashmap commandMap
    public void register(String commandName, Icommand command){
        commandMap.put(commandName,command);
    }

    //Get the necessary command from command name
    private Icommand get(String commandName){
        return commandMap.get(commandName);
    }

    //Execute the registered command
    public void executeCommand(String commandName, List<String> tokens) {
        Icommand command = get(commandName);
        
        if(command == null){
            //return back if no command found 
            //Here I could have thrown a custom exception like NoCommandFoundException
            //But that could disrupt the program flow so i am just returning back with error message if command not present
            System.err.println("Invalid Command : "+commandName);
           return;
         }
        command.execute(tokens);
    }

}

