package clueGame;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {

	BadConfigFormatException(){
		super("Bad Config File Format");
		
		try{
			FileWriter fw = new FileWriter("errorLog.txt", true);
			fw.append("Bad Config File Format\n");
			fw.close();
		}
		catch(IOException e2){
			System.out.println("Could not write error to log file");
			System.out.println(e2);
		}
	}
	
	BadConfigFormatException(String message){
		super(message);
		
		try{
			FileWriter fw = new FileWriter("errorLog.txt", true);
			fw.append(message+"\n");
			fw.close();
		}
		catch(IOException e2){
			System.out.println("Could not write error to log file");
			System.out.println(e2);
		}
	}
}
