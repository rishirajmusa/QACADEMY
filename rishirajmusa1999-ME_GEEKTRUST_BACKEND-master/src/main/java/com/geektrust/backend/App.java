package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;

public class App {
	//To run the application commandLineArgs =sample_input/input1.txt;
	public static void main(String[] args) {
		//Feeding the data from file to commandLineArgs
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
		run(commandLineArgs);
	}

	public static void run(List<String> commandLineArgs) {
		//Starting the configuration file which has all the objects
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		BufferedReader reader;
		//Feeding the name to inputFile
		String inputFile = commandLineArgs.get(0);
		try {
			//reading the file
			reader = new BufferedReader(new FileReader(inputFile));
			String line;
			while((line =reader.readLine()) != null ){
				List<String> token = Arrays.asList(line.split(" "));
				commandInvoker.executeCommand(token.get(0), token);
			}
		} catch (IOException e) {
			System.err.println(e);

		}
		
		

	}

}
